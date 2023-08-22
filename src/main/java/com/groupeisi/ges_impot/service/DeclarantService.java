package com.groupeisi.ges_impot.service;

import com.groupeisi.ges_impot.dao.IDeclarantRepository;
import com.groupeisi.ges_impot.dto.Declarant;
import com.groupeisi.ges_impot.exception.EntityNotFoundException;
import com.groupeisi.ges_impot.exception.RequestException;
import com.groupeisi.ges_impot.mapping.DeclarantMapper;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DeclarantService {
    private IDeclarantRepository iDeclarantRepository;
    private DeclarantMapper declarantMapper;
    MessageSource messageSource;

    public DeclarantService(IDeclarantRepository iDeclarantRepository, DeclarantMapper declarantMapper, MessageSource messageSource) {
        this.iDeclarantRepository = iDeclarantRepository;
        this.declarantMapper = declarantMapper;
        this.messageSource = messageSource;
    }


    @Transactional(readOnly = true)
    public List<Declarant> getDeclarants(){
        return StreamSupport.stream(iDeclarantRepository.findAll().spliterator(),false)
                .map(declarantMapper::toDeclarant)
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public Declarant getDeclarant(Long id){
        return declarantMapper.toDeclarant(iDeclarantRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException(messageSource.getMessage("Declarant.notfound", new Object[]{id},
                        Locale.getDefault()))));
    }
    @Transactional
    public Declarant createDeclarant(Declarant declarant){
        return declarantMapper.toDeclarant(iDeclarantRepository.save(declarantMapper.fromDeclarant(declarant)));
    }

    @Transactional
    public Declarant updateDeclarant(Long id, Declarant declarant){
        return iDeclarantRepository.findById(id)
                .map(entity->{
                    declarant.setId(id);
                    return declarantMapper.toDeclarant(iDeclarantRepository.save(declarantMapper.fromDeclarant(declarant)));
                }).orElseThrow(()->new EntityNotFoundException(messageSource.getMessage("Declarant.notfound", new Object[]{id},
                        Locale.getDefault())));
    }

    @Transactional
    public void deleteDeclaration(Long id){
        try{
            iDeclarantRepository.deleteById(id);
        }catch (Exception e){
            throw new RequestException(messageSource.getMessage("Declarant.errordeletion",new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }
}
