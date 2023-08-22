package com.groupeisi.ges_impot.service;

import com.groupeisi.ges_impot.dao.IDeclarantRepository;
import com.groupeisi.ges_impot.dao.IDeclarationRepository;
import com.groupeisi.ges_impot.dao.IPaiementRepository;
import com.groupeisi.ges_impot.dto.Declaration;
import com.groupeisi.ges_impot.dto.Paiement;
import com.groupeisi.ges_impot.entities.DeclarantEntity;
import com.groupeisi.ges_impot.entities.DeclarationEntity;
import com.groupeisi.ges_impot.entities.PaiementEntity;
import com.groupeisi.ges_impot.exception.EntityNotFoundException;
import com.groupeisi.ges_impot.exception.RequestException;
import com.groupeisi.ges_impot.mapping.PaiementMapper;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PaiementService {

    private IPaiementRepository iPaiementRepository;
    private IDeclarationRepository iDeclarationRepository;
    private PaiementMapper paiementMapper;
    MessageSource messageSource;

    public PaiementService(IPaiementRepository iPaiementRepository, PaiementMapper paiementMapper, MessageSource messageSource,IDeclarationRepository iDeclarationRepository){
        this.iPaiementRepository = iPaiementRepository;
        this.iDeclarationRepository = iDeclarationRepository;
        this.paiementMapper = paiementMapper;
        this.messageSource = messageSource;
    }

/*
    @Transactional(readOnly = true)
    public List<Paiement> getPaiements(){
        return StreamSupport.stream(iPaiementRepository.findAll().spliterator(),false)
                .map(paiementMapper::toPaiement)
                .collect(Collectors.toList());
    }
*/

    @Transactional(readOnly = true)
    public List<Paiement> getPaiements() {
        return StreamSupport.stream(iPaiementRepository.findAll().spliterator(), false)
                .map(paiementEntity -> {
                    Paiement paiementDTO = paiementMapper.toPaiement(paiementEntity);
                    paiementDTO.setDeclarationId(paiementEntity.getDeclaration().getId());
                    return paiementDTO;
                })
                .collect(Collectors.toList());
    }

    /*
    @Transactional
    public Paiement createPaiement(Paiement paiement){
        return paiementMapper.toPaiement(iPaiementRepository.save(paiementMapper.fromPaiement(paiement)));
    }
     */

    @Transactional
    public Paiement createPaiement(Paiement paiement) {
        // Récupérer l'entité du déclarant à partir de l'ID inclus dans la déclaration
        DeclarationEntity declarationEntity = iDeclarationRepository.findById(paiement.getDeclarationId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Déclarant introuvable"));

        // Créer l'entité DeclarationEntity à partir du DTO Declaration
        PaiementEntity paiementEntity = paiementMapper.fromPaiement(paiement);

        // Associer l'entité du déclarant à l'entité de la déclaration
        paiementEntity.setDeclaration(declarationEntity);

        // Convertir l'entité sauvegardée en DTO et renvoyer le résultat
        return paiementMapper.toPaiement(iPaiementRepository.save(paiementEntity));
    }


    @Transactional(readOnly = true)
    public Paiement getPaiement(Long id){
        return paiementMapper.toPaiement(iPaiementRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException(messageSource.getMessage("Paiement.notfound", new Object[]{id},
                        Locale.getDefault()))));
    }

    @Transactional
    public Paiement updatePaiement(Long id, Paiement paiement){
        return iPaiementRepository.findById(id)
                .map(entity->{
                    paiement.setId(id);
                    return paiementMapper.toPaiement(iPaiementRepository.save(paiementMapper.fromPaiement(paiement)));
                }).orElseThrow(()->new EntityNotFoundException(messageSource.getMessage("Paiement.notfound", new Object[]{id},
                        Locale.getDefault())));
    }

    @Transactional
    public void deletePaiement(Long id){
        try{
            iPaiementRepository.deleteById(id);
        }catch (Exception e){
            throw new RequestException(messageSource.getMessage("Paiement.errordeletion",new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }
}
