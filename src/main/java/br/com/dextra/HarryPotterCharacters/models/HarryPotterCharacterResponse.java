package br.com.dextra.HarryPotterCharacters.models;

import lombok.Data;

@Data
public class HarryPotterCharacterResponse {

    private String name;

    private String role;

    private String school;

    private String house;

    private String patronus;

    public HarryPotterCharacterResponse(HarryPotterCharacter harryPotterCharacter) {

        this.name = harryPotterCharacter.getName();

        this.role = harryPotterCharacter.getRole();

        this.school = harryPotterCharacter.getSchool();

        this.house = harryPotterCharacter.getHouse();

        this.patronus = harryPotterCharacter.getPatronus();
    }
}
