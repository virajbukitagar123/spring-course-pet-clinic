package guru.springframework.springcoursepetclinic.repositories;

import guru.springframework.springcoursepetclinic.model.Speciality;
import org.springframework.data.repository.CrudRepository;

public interface SpecialityRepository extends CrudRepository<Speciality, Long> {
}
