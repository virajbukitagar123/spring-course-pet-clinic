package guru.springframework.springcoursepetclinic.repositories;

import guru.springframework.springcoursepetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
