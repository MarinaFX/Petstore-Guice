package com.marinafx.cloudnative.tema6.pet;

import com.google.inject.Inject;
import java.util.UUID;

public class PetBuilder {
    private Pet pet;

    @Inject
    public PetBuilder(Pet pet) {
        this.pet = pet;
    }

    public PetBuilder withId(UUID id){
        pet.setId(id);
        return this;
    }

    public PetBuilder withName(String name){
        pet.setName(name);
        return this;
    }

    public PetBuilder withBreed(String breed){
        pet.setBreed(breed);
        return this;
    }

    public PetBuilder withAge(int age){
        pet.setAge(age);
        return this;
    }

    public Pet build(){
        return pet;
    }
}
