package com.example.demo.repository;

import com.example.demo.model.Address;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AddressRepository {

    private List<Address> addressBook = new ArrayList<>();

    /**
     * Adding data
     */
    public AddressRepository() {
        addressBook.add(new Address(111,"Mary", "Clarinton", "Bocosto",
                "Prinsens gate 12", 23452345, "mary.clarinton@yahoo.com", 987698761, 234523));
        addressBook.add(new Address(112,"John", "Karken", "Kalis",
                "Dronning gate 12", 45678123, "mary.clarinton@yahoo.com", 34587567, 234412));
        addressBook.add(new Address(113,"Stella", "Tare", "Smart",
                "Jernbanetorget", 98712234, "mary.clarinton@yahoo.com", 9889912, 7456234));
        addressBook.add(new Address(114, "Anya", "Floyer", "HP",
                "Olsens gate 6", 98712334, "mary.clarinton@yahoo.com", 12399002, 345654));
    }

    public List<Address> getAddressBook() {
        return addressBook;
    }

    public Address findById(int id) {
        return addressBook.stream().filter(address -> address.id() == id).findFirst().orElseThrow();
    }

    public Address create(Address address) {
        addressBook.add(address);
        return address;
    }

    public void update(Address address, int id) {
        Address existingAddress = addressBook.stream().filter(add -> add.id() == id).findFirst().orElseThrow();
        int index = addressBook.indexOf(existingAddress);
        addressBook.set(index, address);
    }

    public void delete(int id) {
        addressBook.removeIf(address -> address.id() == id);
    }
}
