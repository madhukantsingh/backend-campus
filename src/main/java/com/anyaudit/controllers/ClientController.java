package com.anyaudit.controllers;


import com.anyaudit.models.Client;
import com.anyaudit.service.ClientManager;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URI;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Getter
@Setter
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientManager clientService;

    @GetMapping("/list")
    @PreAuthorize("hasRole('JUNIOR')")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('JUNIOR')")
    public ResponseEntity<Client> getClientById(@PathVariable("id") long clientId) {
        Client client = clientService.getClientById(clientId);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);
    }

    @PostMapping("/save")
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        Client saved = clientService.addClient(client);
        return ResponseEntity.created(URI.create("/clients/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") long clientId, @RequestBody Client client) {
        if (client.getId() != clientId) {
            return ResponseEntity.badRequest().build();
        }
        Client saved = clientService.updateClient(client);
        if (saved == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") long clientId) {
        clientService.deleteClient(clientId);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/names")
//    public List<Object[]> findNameAndId() {
//        return clientService.findNameAndId();
//    }
    @GetMapping("/names")
    public List<Map<String, Object>> findNameAndId() {
        List<Object[]> clients = clientService.findNameAndId();
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Object[] client : clients) {
            Map<String, Object> clientMap = new HashMap<>();
            clientMap.put("client_id", client[0]);
            clientMap.put("name", client[1]);
            resultList.add(clientMap);
        }
        return resultList;

    }


//    @GetMapping("/name/{name}")
//    public List<Client> findByName(@PathVariable String name) {
//        return clientService.findByName(name);
//    }
//    @GetMapping("/phone/{phoneNo}")
//    public List<Client> getClientsByPhoneNo(@PathVariable String phoneNo) {
//        return clientService.getClientsByPhoneNo(phoneNo);
//    }
//
//    @GetMapping("/email/{email}")
//    public List<Client> getClientsByEmail(@PathVariable String email) {
//        return clientService.getClientsByEmail(email);
//    }
//
//    @GetMapping("/file/{fileNo}")
//    public List<Client> getClientsByFileNo(@PathVariable String fileNo) {
//        return clientService.getClientsByFileNo(fileNo);
//    }


    @GetMapping("/find")
    public List<Client> getClientsByFilter(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phoneNo,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String fileNo,
            @RequestParam(required = false) Long frameworkId // Add this parameter
    ) {
        if (name != null) {
            return clientService.findByName(name);
        } else if (phoneNo != null) {
            return clientService.getClientsByPhoneNo(phoneNo);
        } else if (email != null) {
            return clientService.getClientsByEmail(email);
        } else if (fileNo != null) {
            return clientService.getClientsByFileNo(fileNo);
        } else if (frameworkId != null) { // Add this condition
            return clientService.findByFrameworkId(frameworkId);
        } else {
            // Handle case when no filter is provided
            return new ArrayList<>();
        }
    }



    @GetMapping("/search/{keyword}")
    public List<Client> searchClientsByKeyword(@PathVariable String keyword) {
        return clientService.searchClientsByKeyword(keyword);
    }


}