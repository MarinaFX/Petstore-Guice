package com.marinafx.cloudnative.tema6.module;

import com.google.inject.AbstractModule;
import com.marinafx.cloudnative.tema6.logger.InfoLogger;
import com.marinafx.cloudnative.tema6.pet.Pet;
import com.marinafx.cloudnative.tema6.pet.PetBuilder;
import com.marinafx.cloudnative.tema6.service.Petstore;

public class PetModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Pet.class);
        bind(Petstore.class);
        bind(PetBuilder.class);
        bind(InfoLogger.class);
    }
}
