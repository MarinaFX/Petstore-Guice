package com.marinafx.cloudnative.tema6;

import com.google.common.collect.LinkedHashMultimap;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.marinafx.cloudnative.tema6.module.PetModule;
import com.marinafx.cloudnative.tema6.pet.Pet;
import com.marinafx.cloudnative.tema6.service.Petstore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet(name = "petstore", urlPatterns = "petstore")
public class PetController extends HttpServlet {

    private final Petstore petstore;

    public PetController() {
        Injector injector = Guice.createInjector(new PetModule());
        this.petstore = injector.getInstance(Petstore.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {
        try {
            String button = req.getParameter("button");

            if ("add".equals(button)){
                String name = req.getParameter("petName");
                String breed = req.getParameter("petBreed");
                int age = Integer.parseInt(req.getParameter("petAge"));
                UUID id = petstore.addPet(name, breed, age);

                req.setAttribute("petId", id);
                req.setAttribute("petName", name);
                req.setAttribute("petBreed", breed);
                req.setAttribute("petAge", age);
                req.getRequestDispatcher("/pet-added.jsp").forward(req, resp);
            } else {
                if ("remove".equals(button)){
                    PrintWriter writer = resp.getWriter();
                    writer.println(req.getParameter("petId"));
                    UUID id = UUID.fromString(req.getParameter("petId"));
                    boolean wasRemoved = petstore.removePet(id);

                    req.setAttribute("petRemoved", wasRemoved);
                    req.getRequestDispatcher("/pet-removed.jsp").forward(req, resp);
                }
                else {
                    if ("doBath".equals(button)){
                        UUID id = UUID.fromString(req.getParameter("petId"));
                        boolean withPerfume = Boolean.parseBoolean(req.getParameter("perfume"));
                        boolean withWater = Boolean.parseBoolean(req.getParameter("water"));
                        boolean showered = petstore.doBath(id, withPerfume, withWater);

                        req.setAttribute("showered", showered);
                        req.getRequestDispatcher("/pet-showered.jsp").forward(req, resp);
                    }
                    else {
                        if ("doHaircut".equals(button)){
                            UUID id = UUID.fromString(req.getParameter("petId"));
                            String cutStyle = req.getParameter("hair");
                            boolean haircut = petstore.doHaircut(id, cutStyle);

                            req.setAttribute("haircut", haircut);
                            req.setAttribute("cutStyle", cutStyle);
                            req.getRequestDispatcher("/pet-haircut.jsp").forward(req, resp);

                        }
                        else {
                            if ("getRevenues".equals(button)){
                                List<Map.Entry<Pet, Double>> entries = petstore.topRevenues();
                                req.setAttribute("entries", entries);
                                req.getRequestDispatcher("/pet-revenues.jsp").forward(req, resp);
                            }
                            else {
                                if ("getHistory".equals(button)){
                                    LinkedHashMultimap<Pet, String> history = petstore.history();
                                    req.setAttribute("history", history);
                                    req.getRequestDispatcher("/pet-history.jsp").forward(req, resp);
                                }
                            }
                        }
                    }
                }
            }

        } catch (IllegalArgumentException | ServletException | IOException e) {
            try {
                PrintWriter writer = resp.getWriter();
                writer.println(e.getMessage());
            } catch (IOException ioException) {
                System.out.println(ioException.getMessage());
            }
        }
    }
}
