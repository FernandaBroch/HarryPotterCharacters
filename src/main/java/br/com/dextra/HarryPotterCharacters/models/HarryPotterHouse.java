package br.com.dextra.HarryPotterCharacters.models;

import lombok.Data;

import java.util.List;

@Data
public class HarryPotterHouse {

    private String id;

    private String name;

    private String headOfHouse;

    private List<String> values;

    private List<String> colors;

    private String school;

    private String mascot;

    private String houseGhost;

    private String founder;
}
