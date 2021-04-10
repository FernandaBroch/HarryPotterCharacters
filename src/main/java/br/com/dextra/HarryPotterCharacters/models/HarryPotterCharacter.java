package br.com.dextra.HarryPotterCharacters.models;

import lombok.Data;

import javax.persistence.GenerationType;
import javax.persistence.*;

@Entity
@Data
public class HarryPotterCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String role;

    private String school;

    private String house;

    private String patronus;

}
