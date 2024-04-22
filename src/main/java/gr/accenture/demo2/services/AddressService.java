package gr.accenture.demo2.services;

import gr.accenture.demo2.models.Address;
import gr.accenture.demo2.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public Address createAddress(Address address){
        addressRepository.save(address);
        return address;
    }

}
