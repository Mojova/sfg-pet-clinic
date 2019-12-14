package fi.erpg.sfgpetclinic.bootstrap;

import fi.erpg.sfgpetclinic.model.Owner;
import fi.erpg.sfgpetclinic.model.Pet;
import fi.erpg.sfgpetclinic.model.PetType;
import fi.erpg.sfgpetclinic.model.Specialty;
import fi.erpg.sfgpetclinic.model.Vet;
import fi.erpg.sfgpetclinic.services.OwnerService;
import fi.erpg.sfgpetclinic.services.PetTypeService;
import fi.erpg.sfgpetclinic.services.SpecialtyService;
import fi.erpg.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();
        if (count == 0) {
        loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedRadiology = specialtyService.save(radiology);
        Specialty savedSurgery = specialtyService.save(surgery);
        Specialty savedDentistry = specialtyService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Olli");
        owner1.setLastName("Omisaja");
        owner1.setAddress("Kaduntie 1");
        owner1.setCity("Kaupunkila");
        owner1.setTelephone("040 123 1234");

        Pet ollisPet = new Pet();
        ollisPet.setPetType(savedDogPetType);
        ollisPet.setOwner(owner1);
        ollisPet.setBirthDate(LocalDate.now());
        ollisPet.setName("Hunde");
        owner1.getPets().add(ollisPet);


        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Outi");
        owner2.setLastName("Omistaja");
        owner2.setAddress("Tienkatu 1");
        owner2.setCity("Kylälä");
        owner2.setTelephone("040 431 5431");

        Pet outisPet = new Pet();
        outisPet.setPetType(savedCatPetType);
        outisPet.setName("Monni");
        outisPet.setBirthDate(LocalDate.now());
        outisPet.setOwner(owner2);
        owner2.getPets().add(outisPet);

        ownerService.save(owner2);

        System.out.println("Loaded owners.");

        Vet vet1 = new Vet();
        vet1.setFirstName("Eero");
        vet1.setLastName("Eläinlääkäri");
        vet1.getSpecialties().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Eleanoora");
        vet2.setLastName("Eläinlääkäri");
        vet2.getSpecialties().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded vets.");
    }
}
