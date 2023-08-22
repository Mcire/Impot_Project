package com.groupeisi.ges_impot.controller;

import com.groupeisi.ges_impot.dto.Declarant;
import com.groupeisi.ges_impot.service.DeclarantService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/declarants")
@AllArgsConstructor
public class DeclarantController {
    private DeclarantService declarantService;

    @GetMapping
    public List<Declarant> getDeclarants(){
        return declarantService.getDeclarants();
    }

    @GetMapping("/{id}")
    public Declarant getDeclarant(@PathVariable("id") Long id)
    {
        return declarantService.getDeclarant(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Declarant createDeclarant(@Valid @RequestBody Declarant declarant){
        return declarantService.createDeclarant(declarant);
    }

    @PutMapping("/{id}")
    public Declarant updateDeclarant(@PathVariable("id") Long id, @Valid @RequestBody Declarant declarant){
        return declarantService.updateDeclarant(id, declarant);
    }

    @DeleteMapping("/{id}")
    public Object deleteDeclarant(@PathVariable("id") Long id){
        try {
            declarantService.deleteDeclaration(id);
            return "Donnee supprimees";
        }catch (Exception e)
        {
            return "Donnee non supprimees";
        }

    }


}
