package com.example.demo.model;

//TODO Add validation here
public record Address(
        int id,
        String firstName,
        String lastName,
        String companyName,
        String street,
        long tlf,
        String email,
        long mobileNum,
        long faxNum
) {

}
