package fi.erpg.sfgpetclinic.bootstrap;

import fi.erpg.sfgpetclinic.model.Owner;
import fi.erpg.sfgpetclinic.model.PetType;
import fi.erpg.sfgpetclinic.model.Vet;
import fi.erpg.sfgpetclinic.services.OwnerService;
import fi.erpg.sfgpetclinic.services.PetTypeService;
import fi.erpg.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Olli");
        owner1.setLastName("Omisaja");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Outi");
        owner2.setLastName("Omistaja");

        ownerService.save(owner2);

        System.out.println("Loaded owners.");

        Vet vet1 = new Vet();
        vet1.setFirstName("Eero");
        vet1.setLastName("Eläinlääkäri");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Eleanoora");
        vet2.setLastName("Eläinlääkäri");

        vetService.save(vet2);

        System.out.println("Loaded vets.");
    }
}
