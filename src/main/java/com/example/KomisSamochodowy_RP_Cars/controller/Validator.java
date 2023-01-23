package com.example.KomisSamochodowy_RP_Cars.controller;

public class Validator {

    final static String DATE_FORMAT = "yyyy-MM-dd";

    public static boolean validateDate(String date) {
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    public static boolean validateFirstName(String firstName) {
        return firstName.matches("[A-Z][a-zA-Z]*");
    }

    public static boolean validateLastName(String lastName) {
        return lastName.matches("[a-zA-z]+(['-][a-zA-Z]+)*");
    }

    public static boolean validateVIN(String vin) {
        return vin.matches("\\b[(A-H|J-N|P|R-Z|0-9)]{17}\\b");
    }

    public static boolean validateDostepnosc(String dostepnosc) {
        if(dostepnosc.matches("true")){
            return true;
        }
        if(dostepnosc.matches("false")){
            return true;
        }
        return false;
    }

    public static boolean validateEmail(String email) {
        return email.matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$");
    }

    public static boolean validatePesel(String pesel) {
        return pesel.matches("^\\d{11}$");
    }

    public static boolean validateRokProdukcji(String rokProdukcji) {
        return rokProdukcji.matches("^\\d{4}$");
    }

    public static boolean validateNumerTelefonu(String numerTel) {
        return numerTel.matches("^\\d{9}$");
    }

}
