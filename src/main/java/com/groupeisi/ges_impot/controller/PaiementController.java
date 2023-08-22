package com.groupeisi.ges_impot.controller;

import com.groupeisi.ges_impot.dto.Paiement;
import com.groupeisi.ges_impot.service.PaiementService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/paiements")
@AllArgsConstructor
public class PaiementController {
    private PaiementService paiementService;

    @GetMapping
    public List<Paiement> getPaiements(){
        return paiementService.getPaiements();
    }

    @GetMapping("/{id}")
    public Paiement getPaiement(@PathVariable("id") Long id)
    {
        return paiementService.getPaiement(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Paiement createPaiement(@Valid @RequestBody Paiement paiement){
        return paiementService.createPaiement(paiement);
    }

    @PutMapping("/{id}")
    public Paiement updatePaiement(@PathVariable("id") Long id, @Valid @RequestBody Paiement paiement){
        return paiementService.updatePaiement(id, paiement);
    }

    @DeleteMapping("/{id}")
    public Object deletePaiement(@PathVariable("id") Long id){
        try {
            paiementService.deletePaiement(id);
            return "Donnee supprimees";
        }catch (Exception e)
        {
            return "Donnee non supprimees";
        }

    }
}
