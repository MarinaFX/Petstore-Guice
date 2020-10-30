package com.marinafx.cloudnative.tema6.pet;

import com.google.inject.Inject;
import java.util.UUID;

public class Pet {

    private UUID id;
    private String name;
    private String breed;
    private int age;

    @Inject
    public Pet() { }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return  "id    = " + id + "\n" +
                "name  = " + name + "\n" +
                "breed = " + breed + "\n" +
                "age   = " + age + "\n" ;
    }
}
