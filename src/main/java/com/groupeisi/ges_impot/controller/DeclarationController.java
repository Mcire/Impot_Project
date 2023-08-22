package com.groupeisi.ges_impot.controller;

import com.groupeisi.ges_impot.dto.Declaration;
import com.groupeisi.ges_impot.service.DeclarationService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/declarations")
@AllArgsConstructor
public class DeclarationController {

    private DeclarationService declarationService;

    @GetMapping
    public List<Declaration> getDeclarations(){
        return declarationService.getDeclarations();
    }

    @GetMapping("/{id}")
    public Declaration getAppRoles(@PathVariable("id") Long id)
    {
        return declarationService.getDeclaration(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Declaration createDeclaration(@Valid @RequestBody Declaration declaration){
        return declarationService.createDeclaration(declaration);
    }

    @PutMapping("/{id}")
    public Declaration updateDeclaration(@PathVariable("id") Long id, @Valid @RequestBody Declaration declaration){
        return declarationService.updateDeclaration(id, declaration);
    }

    @DeleteMapping("/{id}")
    public Object deleteDeclaration(@PathVariable("id") Long id){
        try {
            declarationService.deleteDeclaration(id);
            return "Donnee supprimees";
        }catch (Exception e)
        {
            return "Donnee non supprimees";
        }

    }
}
