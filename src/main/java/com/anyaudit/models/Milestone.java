package com.anyaudit.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "milestone")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Milestone extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    @Size(max = 30)
    @Column(name = "milestone_name")
    private String milestoneName;

//    @NotBlank
//    @Size(max = 30)
//    @Column(name = "checker_user")
//    private String checkerUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userCU_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User checkerUser;


//    @NotBlank
//    @Size(max = 30)
//    @Column(name = "team")
//    private String team;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "user_team",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id"))
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Set<User> team = new HashSet<>();

//    @NotBlank

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    @Column(name = "start_date")
    private Date startDate;

//    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id", referencedColumnName = "assignment_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Assignment assignment;
}
