package com.anyaudit.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Entity
@Table(name = "annexures")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Annexures extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "name")
    private String name;

    @NotBlank
    @Size(max = 50)
    @Column(name = "description")
    private String description;

    @NotBlank
    @Size(max = 50)
    @Column(name = "chapter")
    private String chapter;


    @NotBlank
    @Size(max = 50)
    @Column(name = "areaofAudit")
    private String areaofAudit;
}
