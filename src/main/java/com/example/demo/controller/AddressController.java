package com.example.demo.controller;

import com.example.demo.model.Address;
import com.example.demo.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/address-book")
//TODO Fix validation here after adding it to Address class
public class AddressController {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Address> findAllAddresses() {
        return addressRepository.getAddressBook();
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{id}")
    public Address findAddressById(@PathVariable int id) {
        return addressRepository.getAddressById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Address createAddress(@RequestBody Address address) {
        return addressRepository.createAddress(address);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateAddress(@RequestBody Address address, @PathVariable int id) {
        addressRepository.updateAddress(address, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable int id) {
        addressRepository.deleteAddress(id);
    }

    @GetMapping("/search")
    public List<Address> search(@RequestParam(value = "name", defaultValue = "") String name,
                                @RequestParam(value = "street", defaultValue = "") String street) throws Exception {

        if (!name.isEmpty() && !street.isEmpty()) {
            return addressRepository.getAddressBook().stream()
                    .filter(address -> (address.firstName().toLowerCase().contains(name.toLowerCase()) ||
                            address.lastName().toLowerCase().contains(name.toLowerCase()))
                            && address.street().toLowerCase().contains(street.toLowerCase()))
                    .toList();
        }

        if (!name.isEmpty() && street.isEmpty()) {
            return addressRepository.getAddressBook().stream()
                    .filter(address -> address.firstName().toLowerCase().contains(name.toLowerCase()) ||
                            address.lastName().toLowerCase().contains(name.toLowerCase()))
                    .toList();
        }

        if (name.isEmpty() && !street.isEmpty()) {
            return addressRepository.getAddressBook().stream()
                    .filter(address -> address.street().toLowerCase().contains(street.toLowerCase()))
                    .toList();
        }

        return addressRepository.getAddressBook();
    }
}