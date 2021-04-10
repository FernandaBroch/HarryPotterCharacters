package br.com.dextra.HarryPotterCharacters.controllers;

import br.com.dextra.HarryPotterCharacters.models.HarryPotterCharacter;
import br.com.dextra.HarryPotterCharacters.models.HarryPotterCharacterResponse;
import br.com.dextra.HarryPotterCharacters.models.exceptions.UnexistentEntityException;
import br.com.dextra.HarryPotterCharacters.services.HarryPotterCharacterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api
@RestController
@RequestMapping("api/v1/hp-character")
public class HarryPotterCharacterController {

    @Autowired
    HarryPotterCharacterService harryPotterCharacterService;

    @PostMapping
    @ApiOperation(value = "Criaçao de Personagem de Harry Potter")
    @ApiResponses({
            @ApiResponse( code= HttpServletResponse.SC_CREATED, message = "Criando um personagem de Harry Potter", response = Long.class )
    })
    public ResponseEntity<Long> create (@RequestBody HarryPotterCharacter harryPotterCharacter){
        HarryPotterCharacter harryPotterCharacterResponse = harryPotterCharacterService.create(harryPotterCharacter);
        return ResponseEntity.status(HttpStatus.CREATED).body(harryPotterCharacterResponse.getId());
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Alteração de Personagem de Harry Potter")
    @ApiResponses({
            @ApiResponse( code= HttpServletResponse.SC_OK, message = "Alteração de personagem de Harry Potter", response = Long.class )
    })
    public ResponseEntity<Long> update(@PathVariable(name="id") Long id , @RequestBody HarryPotterCharacter harryPotterCharacter) {
        harryPotterCharacter.setId(id);
        HarryPotterCharacter returnedMentor = harryPotterCharacterService.update(harryPotterCharacter);

        return ResponseEntity.status(HttpStatus.OK).body(returnedMentor.getId());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Encontrar personagem por ID")
    @ApiResponses({
            @ApiResponse( code= HttpServletResponse.SC_OK, message = "Encontrar personagem por ID", response = HarryPotterCharacterResponse.class),
            @ApiResponse( code= HttpServletResponse.SC_NOT_FOUND, message = "Não foi encontrado personagem especificado", response = String.class)
    })
    public ResponseEntity<Object> findOne(@PathVariable(name = "id") Long id) {
        try {
            HarryPotterCharacterResponse harryPotterCharacterResponse = harryPotterCharacterService.findOne(id);
            return ResponseEntity.ok(harryPotterCharacterResponse);
        } catch (UnexistentEntityException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    @ApiOperation(value = "Listagem de personagens de Harry Potter")
    @ApiResponses({
            @ApiResponse( code= HttpServletResponse.SC_OK, message = "Listagem de personagens de Harry Potter", response = List.class),
            @ApiResponse( code= HttpServletResponse.SC_NO_CONTENT, message = "Não foram encontrados personagens de Harry Potter", response = List.class)
    })
    public ResponseEntity<List<HarryPotterCharacterResponse>> findMany(@RequestParam(required = false) String house){
        List<HarryPotterCharacterResponse> returnedList;

        if(!(house == null)){
            returnedList = harryPotterCharacterService.findByParameter(house);
        }else {
            returnedList = harryPotterCharacterService.findAll();
        }

        if(returnedList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(returnedList);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleção de personagem de Harry Potter")
    @ApiResponses({
            @ApiResponse( code= HttpServletResponse.SC_OK, message = "Deleção de personagem de Harry Potter", response = String.class )
    })
    public ResponseEntity<String> delete(@PathVariable(name="id") Long id) {
        harryPotterCharacterService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
