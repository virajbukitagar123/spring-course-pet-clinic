package guru.springframework.springcoursepetclinic.repositories;

import guru.springframework.springcoursepetclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
