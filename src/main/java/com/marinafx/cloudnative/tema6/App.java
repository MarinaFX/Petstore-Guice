package com.marinafx.cloudnative.tema6;

import com.google.common.collect.LinkedHashMultimap;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.marinafx.cloudnative.tema6.module.PetModule;
import com.marinafx.cloudnative.tema6.pet.Pet;
import com.marinafx.cloudnative.tema6.service.Petstore;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.Map;


public class App {
    public static void main(String[] args) {
        try {
            System.out.println("Welcome to Guice's petstore");
            Injector injector = Guice.createInjector(new PetModule());
            Petstore petstore = injector.getInstance(Petstore.class);

            UUID doc = petstore.addPet("Doc", "Shepherd", 7);
            UUID fido = petstore.addPet("Fido", "Bulldog", 12);
            UUID rudra = petstore.addPet("Rudra", "German Shepherd", 8);
            UUID agni = petstore.addPet("Agni", "Golden Retriever", 14);
            UUID koshka = petstore.addPet("Koshka", "No Breed", 7);
            UUID billy = petstore.addPet("Billy", "Doberman", 2);
            UUID thommy = petstore.addPet("Thommy", "Beagle", 4);
            UUID mary = petstore.addPet("Mary", "Poodle", 5);
            UUID sasa = petstore.addPet("Sasa", "chihuahua", 8);
            UUID grey = petstore.addPet("Grey", "Terrier", 10);
            UUID polly = petstore.addPet("Polly", "Pug", 13);

            List<Pet> petsFound = petstore.searchByAge(7);

            for (Pet pet : petsFound){
                System.out.println(pet.toString());
            }

            petstore.doBath(doc, true, true);
            petstore.doBath(doc, false, true);
            petstore.doHaircut(doc,"short");

            petstore.doBath(fido, true, false);
            petstore.doHaircut(fido,"long");
            petstore.doHaircut(fido,"short");

            petstore.doBath(koshka, true, true);
            petstore.doHaircut(koshka,"short");

            petstore.doBath(sasa, false, false);
            petstore.doHaircut(sasa, "long");

            petstore.doBath(rudra, true, true);
            petstore.doHaircut(rudra, "short");

            petstore.doBath(polly, false, true);
            petstore.doBath(thommy, false, true);
            petstore.doBath(agni, false, true);
            petstore.doBath(grey, true, false);
            petstore.doBath(mary, true, false);
            petstore.doBath(billy, false, false);

            petstore.getLogs().forEach(System.out::println);

            System.out.println();

            List<Map.Entry<Pet, Double>> topDogs = petstore.topRevenues();

            for (Map.Entry<Pet, Double> entry: topDogs) {
                System.out.println("Pet: " + entry.getKey().getName() + "\nRevenue: " + entry.getValue());
            }

            System.out.println();

            LinkedHashMultimap<Pet, String> history = petstore.history();

            for (Map.Entry<Pet, String> entry : history.entries()){
                System.out.println("Pet: " + entry.getKey().getName() + "\nService: " + entry.getValue());
            }


        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
