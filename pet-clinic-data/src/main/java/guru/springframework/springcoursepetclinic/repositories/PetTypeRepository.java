package guru.springframework.springcoursepetclinic.repositories;

import guru.springframework.springcoursepetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
