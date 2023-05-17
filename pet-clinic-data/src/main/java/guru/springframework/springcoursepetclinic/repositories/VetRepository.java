package guru.springframework.springcoursepetclinic.repositories;

import guru.springframework.springcoursepetclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
