package com.henrique.Sistema.Assinatura.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.henrique.Sistema.Assinatura.entity.RetornoAssinatura.StatusAssinatura;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "assinatura")
public class Assinatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "data_de_Inicio")
    private LocalDate dataInicio;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "data_de_Expiracao")
    private LocalDate dataExpiracao;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "proxima_Cobranca")
    private LocalDate proximaCobranca;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_de_Assinatura")
    private StatusAssinatura statusAssinatura;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "data_de_Cancelamento")
    private LocalDate dataCancelamento;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnore
    private Usuario usuario;

    @ManyToOne
    @JsonIgnore
    private Plano plano;

    @OneToMany(mappedBy = "assinatura")
    @JsonIgnore
    private List<Pagamento> pagamentos;

    public Assinatura() {
    }

    public Assinatura(LocalDate dataExpiracao, LocalDate proximaCobranca, StatusAssinatura statusAssinatura, Usuario usuario, Plano plano) {
        this.dataInicio = LocalDate.now();
        this.dataExpiracao = dataExpiracao;
        this.proximaCobranca = proximaCobranca;
        this.statusAssinatura = statusAssinatura;
        this.usuario = usuario;
        this.plano = plano;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(LocalDate dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public LocalDate getProximaCobranca() {
        return proximaCobranca;
    }

    public void setProximaCobranca(LocalDate proximaCobranca) {
        this.proximaCobranca = proximaCobranca;
    }

    public StatusAssinatura getStatusAssinatura() {
        return statusAssinatura;
    }

    public void setStatusAssinatura(StatusAssinatura statusAssinatura) {
        this.statusAssinatura = statusAssinatura;
    }

    public LocalDate getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(LocalDate dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Assinatura that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
