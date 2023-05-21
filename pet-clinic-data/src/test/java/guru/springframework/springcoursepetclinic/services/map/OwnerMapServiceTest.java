package guru.springframework.springcoursepetclinic.services.map;

import guru.springframework.springcoursepetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    final Long ownerId = 1L;
    final String lastName = "Smith";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService()); // Doing DI manually
        ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerMapService.findAll();
        assertThat(owners)
                .hasSize(1);
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);
        assertThat(owner.getId())
                .isEqualTo(ownerId);
    }

    @Test
    void saveWithId() {
        Long id = 2L;
        Owner owner2 = Owner.builder().id(id).build();
        Owner savedOwner = ownerMapService.save(owner2);
        assertThat(savedOwner.getId())
                .isEqualTo(id);

    }

    @Test
    void saveWithNoId() {
        Owner savedOwner = ownerMapService.save(Owner.builder().build());
        assertThat(savedOwner)
                .isNotNull();
        assertThat(savedOwner.getId())
                .isNotNull();

    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));
        assertThat(ownerMapService.findAll())
                .hasSize(0);
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);
        assertThat(ownerMapService.findAll())
                .hasSize(0);
    }

    @Test
    void findByLastName() {
        Owner owner = ownerMapService.findByLastName(lastName);
        assertThat(owner)
                .isNotNull();
        assertThat(owner.getId())
                .isEqualTo(ownerId);
        assertThat(owner.getLastName())
                .isEqualTo(lastName);
    }

    @Test
    void findByLastNameNotFound() {
        Owner owner = ownerMapService.findByLastName("Non_Existent_last_name");
        assertThat(owner)
                .isNull();
    }
}