package com.henrique.Sistema.Assinatura.service;

import com.henrique.Sistema.Assinatura.DTOs.PlanoRequestDTO;
import com.henrique.Sistema.Assinatura.entity.Plano;
import com.henrique.Sistema.Assinatura.exceptions.BadRequestException;
import com.henrique.Sistema.Assinatura.exceptions.NotFoundException;
import com.henrique.Sistema.Assinatura.repository.PlanoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class PlanoService {

    private final static Logger logger = LoggerFactory.getLogger(PlanoService.class.getName());
    private final PlanoRepository planoRepository;

    public PlanoService(PlanoRepository planoRepository) {
        this.planoRepository = planoRepository;
    }

    public List<Plano> findAllPlans(){
        return planoRepository.findAll();
    }

    public Plano findByPlans (Long id){
        Plano plano = planoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Plan not found"));
        return planoRepository.save(plano);
    }

    public Plano createPlans(Plano plano) {

        logger.info("create one Plan");

        if (plano.getValor() == null || plano.getValor().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("The value must be greater than zero.");
        }

        Plano plan = new Plano();

        plan.setPlanoTipo(plano.getPlanoTipo());
        plan.setValor(plano.getValor());
        plan.setAtivo(true);
        plan.setDataCriacao(LocalDate.now());

        return planoRepository.save(plan);
    }

    public Plano updatePlans (Long id, Plano plans){
        logger.info("Updating the plan");
        Plano plano = planoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Plan not found"));
        plano.setPlanoTipo(plans.getPlanoTipo());
        plano.setPlanoTipo(plans.getPlanoTipo());
        plano.setValor(plans.getValor());

        return planoRepository.save(plano);
    }

    public void acivatePlan(Long id){
        Plano plano = planoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Plan not found"));
        plano.setAtivo(true);

        planoRepository.save(plano);
    }

    public void deactivePlan (Long id){
        Plano plano = planoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Plan not found"));
        plano.setAtivo(false);

        planoRepository.save(plano);
    }

}
