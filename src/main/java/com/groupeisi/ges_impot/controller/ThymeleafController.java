package com.groupeisi.ges_impot.controller;

import com.groupeisi.ges_impot.dto.Declarant;
import com.groupeisi.ges_impot.dto.Declaration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@Controller
public class ThymeleafController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/view-declarants")
    public String viewDeclarants(Model model) {
        String apiUrl = "http://localhost:8080/declarants"; // Remplacez par l'URL de votre API
        Declarant[] apiResponse = restTemplate.getForObject(apiUrl, Declarant[].class);
        model.addAttribute("declarants", apiResponse);
        return "declarants"; // Nom du template Thymeleaf
    }

    @GetMapping("/create-declarant")
    public String createDeclarantForm(Model model) {
        model.addAttribute("newDeclarant", new Declarant()); // Pour lier le formulaire à un nouvel objet Declarant
        return "declarants"; // Nom du template Thymeleaf pour le formulaire
    }

    @PostMapping("/create-declarant")
    public String createDeclarantSubmit(@Valid @ModelAttribute("newDeclarant") Declarant newDeclarant, BindingResult result) {
        if (result.hasErrors()) {
            return "declarants"; // Redirige vers le formulaire en cas d'erreurs de validation
        }

        // Appel de l'API REST pour créer un déclarant
        String apiUrl = "http://localhost:8080/declarants";
        restTemplate.postForObject(apiUrl, newDeclarant, Declarant.class);

        return "redirect:/view-declarants"; // Redirige vers la liste des déclarants après la création réussie
    }


    @GetMapping("/create-declaration")
    public String createDeclarationForm(Model model) {
        model.addAttribute("newDeclaration", new Declaration()); // Initialize a new Declaration object for the form
        return "declarations"; // Thymeleaf template name for the creation form
    }


    @PostMapping("/create-declaration")
    public String createDeclarationSubmit(@Valid @ModelAttribute("newDeclaration") Declaration newDeclaration, BindingResult result) {
        if (result.hasErrors()) {
            return "declarations"; // Return to the creation form in case of validation errors
        }

        // Call the REST API to create a new declaration
        String apiUrl = "http://localhost:8080/declarations";
        restTemplate.postForObject(apiUrl, newDeclaration, Declaration.class);

        return "redirect:/view-declarations"; // Redirect to the list of declarants after successful creation
    }

    @GetMapping("/view-declarations/{id}")
    public String viewDeclarant(@PathVariable("id") Long id, Model model) {
        String apiUrl = "http://localhost:8080/declarants/" + id; // URL pour obtenir un déclarant spécifique
        Declarant apiResponse = restTemplate.getForObject(apiUrl, Declarant.class);
        model.addAttribute("declarant", apiResponse);
        return "declarations"; // Nom du template Thymeleaf pour la vue détaillée du déclarant
    }


    @GetMapping("/view-declarations")
    public String viewDeclarations(Model model) {
        String apiUrl = "http://localhost:8080/declarations"; // Remplacez par l'URL de votre API
        Declaration[] apiResponse = restTemplate.getForObject(apiUrl, Declaration[].class);
        model.addAttribute("declarations", apiResponse);
        return "ldeclarations"; // Nom du template Thymeleaf
    }
}
