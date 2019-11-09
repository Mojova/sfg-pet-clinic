package fi.erpg.sfgpetclinic.services;

import fi.erpg.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);
}
