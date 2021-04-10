package br.com.dextra.HarryPotterCharacters.services;

import br.com.dextra.HarryPotterCharacters.models.HarryPotterCharacter;
import br.com.dextra.HarryPotterCharacters.models.HarryPotterCharacterResponse;
import br.com.dextra.HarryPotterCharacters.models.exceptions.UnexistentEntityException;
import br.com.dextra.HarryPotterCharacters.repositories.HarryPotterCharacterRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HarryPotterCharacterService {

    @Autowired
    HarryPotterCharacterRepository harryPotterCharacterRepository;

    @Autowired
    HarryPotterHouseService harryPotterHouseService;

    public HarryPotterCharacter create(HarryPotterCharacter harryPotterCharacter){

         if(!harryPotterHouseService.checkHouseExists(harryPotterCharacter.getHouse())){
            throw new UnexistentEntityException("A Casa código: " + harryPotterCharacter.getHouse() + " não existe no banco de dados");
         }
         harryPotterCharacterRepository.save(harryPotterCharacter);

        return harryPotterCharacter;
    }

    public HarryPotterCharacter update(HarryPotterCharacter harryPotterCharacter){

        harryPotterCharacterRepository.save(harryPotterCharacter);

        return harryPotterCharacter;
    }

    public void delete(Long id){

        harryPotterCharacterRepository.deleteById(id);
    };

    public HarryPotterCharacter findOnDataBaseById(Long id) throws UnexistentEntityException {

        Optional<HarryPotterCharacter> mentor = harryPotterCharacterRepository.findById(id);

        if (mentor.isEmpty()) {
            throw new UnexistentEntityException("O personagem não existe no banco de dados");
        }

        return mentor.get();
    }

    public HarryPotterCharacterResponse findOne(Long id) throws UnexistentEntityException {

        HarryPotterCharacter harryPotterCharacter = this.findOnDataBaseById(id);

        return new HarryPotterCharacterResponse(harryPotterCharacter);
    }

    public List<HarryPotterCharacterResponse> findByParameter(String parameter){

        return harryPotterCharacterRepository.findAll()
                .stream()
                .filter(harryPotterCharacter -> parameter.equals(harryPotterCharacter.getHouse()))
                .map(harryPotterCharacter -> new HarryPotterCharacterResponse(harryPotterCharacter))
                .collect(Collectors.toList());

    };

    public List<HarryPotterCharacterResponse> findAll( ){

        return harryPotterCharacterRepository.findAll()
                .stream()
                .map(harryPotterCharacter -> new HarryPotterCharacterResponse(harryPotterCharacter))
                .collect(Collectors.toList());

    };
}
