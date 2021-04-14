package br.com.dextra.HarryPotterCharacters.service;

import br.com.dextra.HarryPotterCharacters.models.HarryPotterCharacter;
import br.com.dextra.HarryPotterCharacters.models.HarryPotterCharacterResponse;
import br.com.dextra.HarryPotterCharacters.models.exceptions.UnexistentEntityException;
import br.com.dextra.HarryPotterCharacters.repositories.HarryPotterCharacterRepository;
import br.com.dextra.HarryPotterCharacters.services.HarryPotterCharacterService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class HarryPotterCharacterServiceTest {

    @Autowired
    HarryPotterCharacterService harryPotterCharacterService;

    @MockBean
    HarryPotterCharacterRepository harryPotterCharacterRepository;

    @Test
    public void creatingSuccess() {

        // Scenery
        HarryPotterCharacter harryPotterCharacter = new HarryPotterCharacter();
        harryPotterCharacter.setName("Harry Potter");
        harryPotterCharacter.setRole("student");
        harryPotterCharacter.setSchool("Hogwarts School of Witchcraft and Wizardry");
        harryPotterCharacter.setHouse("1760529f-6d51-4cb1-bcb1-25087fce5bde");
        harryPotterCharacter.setPatronus("stag");

        when(harryPotterCharacterRepository.save(Mockito.any(HarryPotterCharacter.class))).thenAnswer(i -> {
            HarryPotterCharacter mentorToReturn = i.getArgument(0);
            mentorToReturn.setId(1L);
            return mentorToReturn;
        });

        // Action
        HarryPotterCharacter returnedMentor = harryPotterCharacterService.create(harryPotterCharacter);

        // Check
        assertThat(returnedMentor).isEqualTo(harryPotterCharacter);
    }

    @Test
    public void deleteSuccess() {

        // Scenery
        HarryPotterCharacter harryPotterCharacter = new HarryPotterCharacter();
        harryPotterCharacter.setId(1L);
        harryPotterCharacter.setName("Harry Potter");
        harryPotterCharacter.setRole("student");
        harryPotterCharacter.setSchool("Hogwarts School of Witchcraft and Wizardry");
        harryPotterCharacter.setHouse("1760529f-6d51-4cb1-bcb1-25087fce5bde");
        harryPotterCharacter.setPatronus("stag");


        Mockito.when(harryPotterCharacterRepository.findById(1L)).thenReturn(Optional.of(harryPotterCharacter));

        // Action
        harryPotterCharacterService.delete(1L);

        // Check
        verify(harryPotterCharacterRepository, times(1)).deleteById(1L);

    }

    @Test
    public void findOneSuccess(){
        // Scenery
        HarryPotterCharacter harryPotterCharacter = new HarryPotterCharacter();
        harryPotterCharacter.setId(1L);
        harryPotterCharacter.setName("Harry Potter");
        harryPotterCharacter.setRole("student");
        harryPotterCharacter.setSchool("Hogwarts School of Witchcraft and Wizardry");
        harryPotterCharacter.setHouse("1760529f-6d51-4cb1-bcb1-25087fce5bde");
        harryPotterCharacter.setPatronus("stag");

        Mockito.when(harryPotterCharacterRepository.findById(1L)).thenReturn(Optional.of(harryPotterCharacter));

        // Action
        HarryPotterCharacterResponse mentorResponse = harryPotterCharacterService.findOne(1L);

        // Check
        assertThat(mentorResponse).usingRecursiveComparison().isEqualTo(harryPotterCharacter);

    }

    @Test
    public void listSuccess(){
        // Scenery
        List<HarryPotterCharacter> harryPotterCharacterList = new ArrayList<HarryPotterCharacter>();

        HarryPotterCharacter harryPotterCharacter = new HarryPotterCharacter();
        harryPotterCharacter.setId(1L);
        harryPotterCharacter.setName("Harry Potter");
        harryPotterCharacter.setRole("student");
        harryPotterCharacter.setSchool("Hogwarts School of Witchcraft and Wizardry");
        harryPotterCharacter.setHouse("1760529f-6d51-4cb1-bcb1-25087fce5bde");
        harryPotterCharacter.setPatronus("stag");
        harryPotterCharacterList.add(harryPotterCharacter);

        HarryPotterCharacter harryPotterCharacter2 = new HarryPotterCharacter();
        harryPotterCharacter2.setId(2L);
        harryPotterCharacter2.setName("Hermione Granger");
        harryPotterCharacter2.setRole("student");
        harryPotterCharacter2.setSchool("Hogwarts School of Witchcraft and Wizardry");
        harryPotterCharacter2.setHouse("1760529f-6d51-4cb1-bcb1-25087fce5bde");
        harryPotterCharacter2.setPatronus("otter");
        harryPotterCharacterList.add(harryPotterCharacter2);

        HarryPotterCharacter harryPotterCharacter3 = new HarryPotterCharacter();
        harryPotterCharacter3.setId(3L);
        harryPotterCharacter3.setName("Luna Lovegood");
        harryPotterCharacter3.setRole("student");
        harryPotterCharacter3.setSchool("Hogwarts School of Witchcraft and Wizardry");
        harryPotterCharacter3.setHouse("542b28e2-9904-4008-b038-034ab312ad7e");
        harryPotterCharacter3.setPatronus("hare");
        harryPotterCharacterList.add(harryPotterCharacter3);

        Mockito.when(harryPotterCharacterRepository.findAll())
                .thenReturn(harryPotterCharacterList);

        // Action
        List<HarryPotterCharacterResponse> returnedList = harryPotterCharacterService.findAll();

        // Check
        assertThat(returnedList).isNotEmpty();

    }

    @Test
    public void findOneException() {
        // Scenery
        Long id = 2L;
        Mockito.when(harryPotterCharacterRepository.findById(id)).thenReturn(Optional.empty());

        // Check
        assertThrows(UnexistentEntityException.class, () -> {
            // Action
            harryPotterCharacterService.findOne(id);
        });
    }



}
