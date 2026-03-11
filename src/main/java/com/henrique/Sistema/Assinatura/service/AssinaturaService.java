package com.henrique.Sistema.Assinatura.service;

import com.henrique.Sistema.Assinatura.entity.Assinatura;
import com.henrique.Sistema.Assinatura.entity.Plano;
import com.henrique.Sistema.Assinatura.entity.RetornoAssinatura.StatusAssinatura;
import com.henrique.Sistema.Assinatura.entity.Usuario;
import com.henrique.Sistema.Assinatura.exceptions.BadRequestException;
import com.henrique.Sistema.Assinatura.exceptions.ConflictRequestException;
import com.henrique.Sistema.Assinatura.exceptions.NotFoundException;
import com.henrique.Sistema.Assinatura.repository.AssinaturaRepository;
import com.henrique.Sistema.Assinatura.repository.PlanoRepository;
import com.henrique.Sistema.Assinatura.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AssinaturaService {

    private final AssinaturaRepository assinaturaRepository;
    private final PlanoRepository planoRepository;
    private final UsuarioRepository usuarioRepository;

    public AssinaturaService(
            AssinaturaRepository assinaturaRepository,
            PlanoRepository planoRepository,
            UsuarioRepository usuarioRepository) {
        this.assinaturaRepository = assinaturaRepository;
        this.planoRepository = planoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Assinatura> findAllSignature(){
        return assinaturaRepository.findAll();
    }

    public List<Assinatura> findByUsuarioIdOrderByDataInicioDesc(Long id){
        return assinaturaRepository.findByUsuarioIdOrderByDataInicioDesc(id);
    }

    public Assinatura findActiveByUser(Long usuarioId) {
        return assinaturaRepository
                .findByUsuarioIdAndStatusAssinatura(usuarioId, StatusAssinatura.ATIVA)
                .orElseThrow(() -> new NotFoundException("Active signature not found"));
    }

    public Assinatura findBySignature(Long id){
        return assinaturaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Signature not found"));
    }


    public Assinatura createSignature(Long planoId, Long usuarioId) {
        Assinatura ativa = assinaturaRepository.findByUsuarioIdAndStatusAssinatura(usuarioId, StatusAssinatura.ATIVA)
                .orElse(null);
        Plano plano = planoRepository.findById(planoId)
                .orElseThrow(() -> new NotFoundException("Plan not found"));
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (ativa != null){
            throw new ConflictRequestException("User already has an active signature");
        }
        if (!plano.getAtivo()){
            throw new BadRequestException("the user plan is this inactivate");
        }


//        ==========================   CALCULO DE PROXIMO COBRANCA  ==========================

        LocalDate dataInicio = LocalDate.now();
        Integer mesesDuracao = plano.getPlanoTipo().getDuracaoMeses();
        LocalDate dataExpiracao = dataInicio.plusMonths(mesesDuracao);
        LocalDate proximaCobranca = dataInicio.plusMonths(1);

//        ===========================   CALCULO DE EXPIRAÇÃO    ==============================

        StatusAssinatura status = StatusAssinatura.ATIVA;

        Assinatura assinatura = new Assinatura();

        assinatura.setDataInicio(dataInicio);
        assinatura.setDataExpiracao(dataExpiracao);
        assinatura.setProximaCobranca(proximaCobranca);
        assinatura.setStatusAssinatura(status);
        assinatura.setUsuario(usuario);
        assinatura.setPlano(plano);

        return assinaturaRepository.save(assinatura);
    }


    public Assinatura changePlan(Long assinaturaId, Long novoPlanoId) {

        Assinatura assinaturaAtual = assinaturaRepository.findById(assinaturaId)
                .orElseThrow(() -> new NotFoundException("Signature not found"));

        if (assinaturaAtual.getStatusAssinatura() == StatusAssinatura.EXPIRADA){
            throw new BadRequestException("The subscription has expired");
        }

        if (assinaturaAtual.getStatusAssinatura() == StatusAssinatura.CANCELADA) {
            throw new ConflictRequestException("Cancelled signature cannot change plan");
        }

        Plano novoPlano = planoRepository.findById(novoPlanoId)
                .orElseThrow(() -> new NotFoundException("Plan not found"));

        if (!novoPlano.getAtivo()){
            throw new BadRequestException("Plan is not active");
        }

        LocalDate hoje = LocalDate.now();
        LocalDate dataBase;

        if (assinaturaAtual.getDataExpiracao().isAfter(hoje)) {
            dataBase = assinaturaAtual.getDataExpiracao();
        } else {
            dataBase = hoje;
        }

        Integer meses = novoPlano.getPlanoTipo().getDuracaoMeses();

        assinaturaAtual.setStatusAssinatura(StatusAssinatura.CANCELADA);
        assinaturaAtual.setDataCancelamento(hoje);
        assinaturaRepository.save(assinaturaAtual);

        Assinatura novaAssinatura = new Assinatura();

        novaAssinatura.setUsuario(assinaturaAtual.getUsuario());
        novaAssinatura.setPlano(novoPlano);

        novaAssinatura.setDataInicio(dataBase);
        novaAssinatura.setDataExpiracao(dataBase.plusMonths(meses));
        novaAssinatura.setProximaCobranca(dataBase.plusMonths(1));

        novaAssinatura.setStatusAssinatura(StatusAssinatura.ATIVA);

        return assinaturaRepository.save(novaAssinatura);
    }

    public Assinatura renewSignature(Long assinaturaId) {

        Assinatura assinatura = assinaturaRepository.findById(assinaturaId)
                .orElseThrow(() -> new NotFoundException("Signature not found"));

        if (assinatura.getStatusAssinatura() == StatusAssinatura.EXPIRADA){
            throw new BadRequestException("The subscription has expired");
        }

        if (assinatura.getStatusAssinatura() == StatusAssinatura.CANCELADA) {
            throw new ConflictRequestException("Cancelled signature cannot be renewed");
        }

        if (!assinatura.getPlano().getAtivo()){
            throw new BadRequestException("The plan not in activate");
        }

        Integer meses = assinatura.getPlano()
                .getPlanoTipo()
                .getDuracaoMeses();

        LocalDate hoje = LocalDate.now();

        LocalDate novaBase;

        if (assinatura.getDataExpiracao().isAfter(hoje)) {
            novaBase = assinatura.getDataExpiracao();
        } else {
            novaBase = hoje;
        }

        LocalDate novaExpiracao = novaBase.plusMonths(meses);
        LocalDate novaProximaCobranca = novaBase.plusMonths(1);

        assinatura.setDataExpiracao(novaExpiracao);
        assinatura.setProximaCobranca(novaProximaCobranca);
        assinatura.setStatusAssinatura(StatusAssinatura.ATIVA);

        return assinaturaRepository.save(assinatura);
    }

    public void expireSignaturesAutomatically() {
        List<Assinatura> assinaturas = assinaturaRepository.findAll();

        for (Assinatura assinatura : assinaturas) {
            if (assinatura.getDataExpiracao().isBefore(LocalDate.now())
                    && assinatura.getStatusAssinatura() == StatusAssinatura.ATIVA) {

                assinatura.setStatusAssinatura(StatusAssinatura.EXPIRADA);
                assinaturaRepository.save(assinatura);
            }
        }
    }

    public void cancelSignature(Long id){

        Assinatura assinatura = assinaturaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Signature not found"));

        if (assinatura.getStatusAssinatura() == StatusAssinatura.CANCELADA) {
            throw new ConflictRequestException("Signature already cancelled");
        }

        assinatura.setStatusAssinatura(StatusAssinatura.CANCELADA);
        assinatura.setDataCancelamento(LocalDate.now());

        assinaturaRepository.save(assinatura);
    }

    public Assinatura reactivateSignature (Long id){
        Assinatura assinatura = assinaturaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Signature not found"));

        if (assinatura.getStatusAssinatura() == StatusAssinatura.ATIVA){
            throw new ConflictRequestException("Signature already activate");
        }

        Integer meses = assinatura.getPlano()
                .getPlanoTipo()
                .getDuracaoMeses();

        LocalDate hoje = LocalDate.now();

        LocalDate novaBase;

        if (assinatura.getDataExpiracao().isAfter(hoje)) {
            novaBase = assinatura.getDataExpiracao();
        } else {
            novaBase = hoje;
        }

        LocalDate novaExpiracao = novaBase.plusMonths(meses);
        LocalDate novaProximaCobranca = novaBase.plusMonths(meses);

        assinatura.setDataExpiracao(novaExpiracao);
        assinatura.setProximaCobranca(novaProximaCobranca);
        assinatura.setStatusAssinatura(StatusAssinatura.ATIVA);
        assinatura.setDataInicio(LocalDate.now());
        return assinaturaRepository.save(assinatura);
    }
}
