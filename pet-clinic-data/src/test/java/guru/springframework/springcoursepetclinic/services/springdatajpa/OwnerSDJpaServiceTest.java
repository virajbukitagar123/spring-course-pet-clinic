package guru.springframework.springcoursepetclinic.services.springdatajpa;

import guru.springframework.springcoursepetclinic.model.Owner;
import guru.springframework.springcoursepetclinic.repositories.OwnerRepository;
import guru.springframework.springcoursepetclinic.repositories.PetRepository;
import guru.springframework.springcoursepetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    public static final long OWNER_ID = 1L;
    public static final String OWNER_LAST_NAME = "Smith";
    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService service;
    Owner ownerStub;

    @BeforeEach
    void setUp() {
        ownerStub = Owner.builder().id(OWNER_ID).lastName(OWNER_LAST_NAME).build();
    }

    @Test
    void findAll() {
        Set<Owner> returnOwnerSet = new HashSet<>();
        returnOwnerSet.add(ownerStub);
        returnOwnerSet.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll())
                .thenReturn(returnOwnerSet);

        Set<Owner> owners = service.findAll();
        assertThat(owners)
                .isNotNull()
                .hasSize(2);
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong()))
                .thenReturn(Optional.of(ownerStub));

        Owner owner = service.findById(OWNER_ID);
        assertThat(owner.getId())
                .isEqualTo(OWNER_ID);
    }

    @Test
    void findByIdNotFount() {
        when(ownerRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        Owner owner = service.findById(OWNER_ID);
        assertThat(owner)
                .isNull();
        verify(ownerRepository)
                .findById(anyLong());
    }

    @Test
    void save() {
        when(ownerRepository.save(any()))
                .thenReturn(ownerStub);

        Owner savedOwner = service.save(ownerStub);
        assertThat(savedOwner)
                .isNotNull();
        assertThat(savedOwner.getId())
                .isEqualTo(OWNER_ID);
        verify(ownerRepository)
                .save(any());

    }

    @Test
    void delete() {
        service.delete(service.findById(OWNER_ID));
        verify(ownerRepository, times(1))
                .delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(OWNER_ID);
        // default is one time
        verify(ownerRepository, times(1))
                .deleteById(anyLong());
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(anyString()))
                .thenReturn(ownerStub);

        Owner owner = service.findByLastName(OWNER_LAST_NAME);
        assertThat(owner)
                .isNotNull();
        assertThat(owner.getId())
                .isEqualTo(OWNER_ID);
        assertThat(owner.getLastName())
                .isEqualTo(OWNER_LAST_NAME);
        verify(ownerRepository)
                .findByLastName(anyString());
    }

    @Test
    void findByLastNameNotFound() {
        when(ownerRepository.findByLastName(anyString()))
                .thenReturn(null);

        Owner owner = service.findByLastName("Non_Existent_last_name");
        assertThat(owner)
                .isNull();
    }
}