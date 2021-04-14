package br.com.dextra.HarryPotterCharacters.service;
import br.com.dextra.HarryPotterCharacters.models.HarryPotterHouse;
import br.com.dextra.HarryPotterCharacters.services.HarryPotterHouseService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class HarryPotterHouseServiceTest {
    @Autowired
    HarryPotterHouseService harryPotterHouseService;

    @Test
    public void checkHouseExistsSuccess(){
        Boolean checkHouseExists;

        // Scenery
        HarryPotterHouse harryPotterHouse = new HarryPotterHouse();
        harryPotterHouse.setId("1760529f-6d51-4cb1-bcb1-25087fce5bde");

        // Action
        checkHouseExists = harryPotterHouseService.checkHouseExists(harryPotterHouse.getId());

        // Check
        assertThat(checkHouseExists).isEqualTo(true);

    }

    @Test
    public void checkHouseExistsFailed(){
        Boolean checkHouseExists;

        // Scenery
        HarryPotterHouse harryPotterHouse = new HarryPotterHouse();
        harryPotterHouse.setId("1760529f-9999-4cb1-bcb1-25087fce5bde");

        // Action
        checkHouseExists = harryPotterHouseService.checkHouseExists(harryPotterHouse.getId());

        // Check
        assertThat(checkHouseExists).isEqualTo(false);

    }
}
