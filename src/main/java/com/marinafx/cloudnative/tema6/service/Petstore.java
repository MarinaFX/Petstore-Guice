package com.marinafx.cloudnative.tema6.service;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.marinafx.cloudnative.tema6.logger.InfoLogger;
import com.marinafx.cloudnative.tema6.module.PetModule;
import com.marinafx.cloudnative.tema6.pet.Pet;
import com.marinafx.cloudnative.tema6.pet.PetBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class Petstore {
    private static final String SHORT = "short";
    private static final String LONG = "long";

    private InfoLogger logger;
    private Injector injector;
    private List<Pet> petList;
    private HashMap<Pet, Double> revenue;
    private Multimap<Pet, String> history;

    @Inject
    public Petstore() {
        injector = Guice.createInjector(new PetModule());
        petList = new ArrayList<>();
        revenue = new HashMap<>();
        history = LinkedHashMultimap.create();
        logger = injector.getInstance(InfoLogger.class);
    }

    public UUID addPet(String name, String breed, int age){
        if (isStringInvalid(name))
            throw new IllegalArgumentException("The name provided is invalid");

        if (isStringInvalid(breed))
            throw new IllegalArgumentException("The breed provided is invalid");

        if (age < 0)
            throw new IllegalArgumentException("The age provided is invalid");

        UUID id = UUID.randomUUID();
        petList.add(injector.getInstance(PetBuilder.class)
                .withId(id)
                .withName(name)
                .withBreed(breed)
                .withAge(age)
                .build());
        return id;
    }

    public boolean removePet(UUID id){
        if (id == null)
            throw new IllegalArgumentException("The id provided is null");

        for (Pet pet : petList){
            if (id.equals(pet.getId())){
                petList.remove(pet);
                return true;
            }
        }

        return false;
    }

    public List<Optional<Pet>> searchByAge(int age){
        if (age < 0)
            throw new IllegalArgumentException("The age provided cannot be negative");

        List<Optional<Pet>> petsFound = new ArrayList<>();

        for (Pet pet : petList){
            if (age == pet.getAge())
                petsFound.add(Optional.of(pet));
        }

        return petsFound;
    }

    public boolean doBath(UUID id, boolean withPerfume, boolean withWater){
        if (id == null)
            throw new IllegalArgumentException("The id provided is null");

        Optional<Pet> pet = searchById(id);

        if (pet.isPresent()){
            if (withPerfume){
                if(withWater){
                    revenue.put(pet.get(), (getPetValue(pet.get()) + 75.0));
                    history.put(pet.get(), "wet bath w/ perfume");
                    logger.info(pet.get().getName() + " took a wet bath with perfume");
                }
                else {
                    revenue.put(pet.get(), (getPetValue(pet.get()) + 50.0));
                    history.put(pet.get(), "dry bath w/ perfume");
                    logger.info(pet.get().getName() + " took a dry bath with perfume");
                }
            }
            else {
                if (withWater){
                    revenue.put(pet.get(), (getPetValue(pet.get()) + 65.0));
                    history.put(pet.get(), "wet bath without perfume");
                    logger.info(pet.get().getName() + " took a wet bath without perfume");
                }
                else {
                    revenue.put(pet.get(), (getPetValue(pet.get()) + 40.0));
                    history.put(pet.get(), "dry bath without perfume");
                    logger.info(pet.get().getName() + " took a dry bath without perfume");
                }
            }
            return true;
        }
        else {
            logger.info("Sorry, but we could not bath any pet with the provided id");
            return false;
        }
    }

    public boolean doHaircut(UUID id, String cutStyle){
        if (id == null)
            throw new IllegalArgumentException("The id provided is null");

        if (isStringInvalid(cutStyle))
            throw new IllegalArgumentException("The provided cut style is invalid");

        Optional<Pet> pet = searchById(id);

        if (pet.isPresent()){
            cutStyle = cutStyle.toLowerCase();
            switch (cutStyle){
                case SHORT:
                    revenue.put(pet.get(), (getPetValue(pet.get()) + 90.0));
                    history.put(pet.get(), "short haircut");
                    logger.info(pet.get().getName() + " took a short hair cut");
                    return true;
                case LONG:
                    revenue.put(pet.get(), (getPetValue(pet.get()) + 60.0));
                    history.put(pet.get(), "long haircut");
                    logger.info(pet.get().getName() + " took a long hair cut");
                    return true;
                default:
                    logger.info("Sorry, but the cut provided is not available");
                    return false;
            }
        }
        else {
            logger.info("Sorry, but we could not cut the hair of any pet with the provided id");
            return false;
        }
    }

    public List<Map.Entry<Pet, Double>> topRevenues(){
        List<Map.Entry<Pet, Double>> entries = new ArrayList<>(revenue.entrySet());
        entries.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        entries = entries.stream().limit(10).collect(Collectors.toList());

        return entries;
    }

    public LinkedHashMultimap<Pet, String> history(){
        return LinkedHashMultimap.create(history);
    }

    public List<String> getLogs(){
        return logger.getLogs();
    }

    private Double getPetValue(Pet pet){
        if (revenue.get(pet) == null)
            return 0.0;
        return revenue.get(pet);
    }

    private Optional<Pet> searchById(UUID id){
        for (Pet pet : petList){
            if (id.toString().equals(pet.getId().toString()))
                return Optional.of(pet);
        }

        return Optional.empty();
    }

    private boolean isStringInvalid(String str){
        if (str == null)
            return true;

        return str.equals("") || str.equals(" ");
    }
}
