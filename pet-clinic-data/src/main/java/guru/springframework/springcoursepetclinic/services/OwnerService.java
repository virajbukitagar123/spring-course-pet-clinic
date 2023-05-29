package guru.springframework.springcoursepetclinic.services;

import guru.springframework.springcoursepetclinic.model.Owner;

import java.util.List;
import java.util.Set;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastname);
}
