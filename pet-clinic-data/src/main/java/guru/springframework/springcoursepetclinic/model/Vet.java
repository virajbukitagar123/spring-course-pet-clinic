package guru.springframework.springcoursepetclinic.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "vets")
public class Vet extends Person {

    @Builder
    public Vet(Long id, String firstName, String lastName, Set<Speciality> specialities) {
        super(id, firstName, lastName);
        this.specialities = specialities;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialities",
    joinColumns = @JoinColumn(name = "vet_id"),
    inverseJoinColumns = @JoinColumn(name = "speciality_id"))
    private Set<Speciality> specialities = new HashSet<>();
}
