package com.groupeisi.ges_impot.service;

import com.groupeisi.ges_impot.dao.IDeclarantRepository;
import com.groupeisi.ges_impot.dao.IDeclarationRepository;
import com.groupeisi.ges_impot.dto.Declaration;
import com.groupeisi.ges_impot.entities.DeclarantEntity;
import com.groupeisi.ges_impot.entities.DeclarationEntity;
import com.groupeisi.ges_impot.exception.EntityNotFoundException;
import com.groupeisi.ges_impot.exception.RequestException;
import com.groupeisi.ges_impot.mapping.DeclarationMapper;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DeclarationService {
    private IDeclarationRepository iDeclarationRepository;

    private IDeclarantRepository iDeclarantRepository;
    private DeclarationMapper declarationMapper;
    MessageSource messageSource;

    public DeclarationService(IDeclarationRepository iDeclarationRepository, DeclarationMapper declarationMapper, MessageSource messageSource, IDeclarantRepository iDeclarantRepository) {
        this.iDeclarationRepository = iDeclarationRepository;
        this.iDeclarantRepository = iDeclarantRepository;
        this.declarationMapper = declarationMapper;
        this.messageSource = messageSource;
    }


    @Transactional(readOnly = true)
    public List<Declaration> getDeclarations() {
        return StreamSupport.stream(iDeclarationRepository.findAll().spliterator(), false)
                .map(declarationEntity -> {
                    Declaration declarationDTO = declarationMapper.toDeclaration(declarationEntity);
                    declarationDTO.setDeclarantId(declarationEntity.getDeclarant().getId());
                    return declarationDTO;
                })
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public Declaration getDeclaration(Long id){
        return declarationMapper.toDeclaration(iDeclarationRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException(messageSource.getMessage("Declaration.notfound", new Object[]{id},
                        Locale.getDefault()))));
    }
    /*
    @Transactional
    public Declaration createDeclaration(Declaration declaration){
        return declarationMapper.toDeclaration(iDeclarationRepository.save(declarationMapper.fromDeclaration(declaration)));
    }
     */

    @Transactional
    public Declaration createDeclaration(Declaration declaration) {
        // Récupérer l'entité du déclarant à partir de l'ID inclus dans la déclaration
        DeclarantEntity declarantEntity = iDeclarantRepository.findById(declaration.getDeclarantId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Déclarant introuvable"));

        // Créer l'entité DeclarationEntity à partir du DTO Declaration
        DeclarationEntity declarationEntity = declarationMapper.fromDeclaration(declaration);

        // Associer l'entité du déclarant à l'entité de la déclaration
        declarationEntity.setDeclarant(declarantEntity);

        // Convertir l'entité sauvegardée en DTO et renvoyer le résultat
        return declarationMapper.toDeclaration(iDeclarationRepository.save(declarationEntity));
    }

    @Transactional
    public Declaration updateDeclaration(Long id, Declaration declaration){
        return iDeclarationRepository.findById(id)
                .map(entity->{
                    declaration.setId(id);
                    return declarationMapper.toDeclaration(iDeclarationRepository.save(declarationMapper.fromDeclaration(declaration)));
                }).orElseThrow(()->new EntityNotFoundException(messageSource.getMessage("Declaration.notfound", new Object[]{id},
                        Locale.getDefault())));
    }

    @Transactional
    public void deleteDeclaration(Long id){
        try{
            iDeclarationRepository.deleteById(id);
        }catch (Exception e){
            throw new RequestException(messageSource.getMessage("Declaration.errordeletion",new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }
}
