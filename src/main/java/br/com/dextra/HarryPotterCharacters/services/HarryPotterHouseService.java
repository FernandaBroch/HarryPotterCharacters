package br.com.dextra.HarryPotterCharacters.services;

import br.com.dextra.HarryPotterCharacters.PropertiesReader;
import br.com.dextra.HarryPotterCharacters.models.HarryPotterHouse;
import br.com.dextra.HarryPotterCharacters.models.HarryPotterHouseResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class HarryPotterHouseService {
    private static final String APP_KEY = PropertiesReader.getProperty("APP_KEY");

    private String url = "http://us-central1-rh-challenges.cloudfunctions.net/potterApi/houses";

    private RestTemplate restTemplate;

    public HarryPotterHouseService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public boolean checkHouseExists(String house){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.set("apiKey", APP_KEY);

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        List<HarryPotterHouse> harryPotterHouses = restTemplate.exchange(url , HttpMethod.GET, entity , HarryPotterHouseResponse.class).getBody().getHouses();

        Optional<HarryPotterHouse> result = harryPotterHouses.stream().filter(hpHouse -> hpHouse.getId().equals(house)).findAny();

        return result.isPresent();
    }
}
