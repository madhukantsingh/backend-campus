package com.anyaudit.controllers;

import com.anyaudit.models.StandardText;
import com.anyaudit.models.WorkingPaper;
import com.anyaudit.service.StandardTextManager;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Getter
@Setter
@RequestMapping("/api/standardtext")
public class StandardTextController {

    @Autowired
    private StandardTextManager standardTextManager;

    @GetMapping("/list")
    public ResponseEntity<List<StandardText>> getAll() {
        List<StandardText> plans = standardTextManager.getAll();
        return ResponseEntity.ok(plans);
    }


}
