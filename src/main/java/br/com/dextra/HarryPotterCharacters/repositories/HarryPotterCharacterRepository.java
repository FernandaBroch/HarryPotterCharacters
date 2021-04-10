package br.com.dextra.HarryPotterCharacters.repositories;

import br.com.dextra.HarryPotterCharacters.models.HarryPotterCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HarryPotterCharacterRepository extends JpaRepository<HarryPotterCharacter, Long> {}

