package com.example.demo.model;

//TODO Add validation here
public record Address(
        int id,
        String firstName,
        String lastName,
        String companyName,
        String address,
        long tlf,
        String email,
        long mobileNum,
        long faxNum
) {

}
