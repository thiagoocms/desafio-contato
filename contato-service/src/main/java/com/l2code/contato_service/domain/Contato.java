package com.l2code.contato_service.domain;

import com.l2code.contato_service.converter.BooleanStringConverter;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "contato", schema = "desafio")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contato_id")
    private Long id;

    @Column(name = "contato_nome", length = 100)
    private String nome;

    @Column(name = "contato_email", length = 255)
    private String email;

    @Column(name = "contato_celular", length = 11)
    private String celular;

    @Column(name = "contato_telefone", length = 10)
    private String telefone;

    @Column(name = "contato_sn_favorito", length = 1)
    @Convert(converter = BooleanStringConverter.class)
    private Boolean snFavorito;

    @Column(name = "contato_sn_ativo", length = 1)
    @Convert(converter = BooleanStringConverter.class)
    private Boolean snAtivo;

    @Column(name = "contato_dh_cad")
    private LocalDateTime dataCadastro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Boolean getSnFavorito() {
        return snFavorito;
    }

    public void setSnFavorito(Boolean snFavorito) {
        this.snFavorito = snFavorito;
    }

    public Boolean getSnAtivo() {
        return snAtivo;
    }

    public void setSnAtivo(Boolean snAtivo) {
        this.snAtivo = snAtivo;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @PrePersist
    protected void onPersiste() {
        snAtivo = Boolean.TRUE;
        dataCadastro = LocalDateTime.now();

    }
}
