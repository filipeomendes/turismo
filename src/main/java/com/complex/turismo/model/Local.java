package com.complex.turismo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TB_TDSPV_LOCAL")
@Getter @Setter
public class Local {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank @Size(max = 100)
    @Column(name = "nm_local", nullable = false, length = 100)
    private String nome;

    @Size(max = 200)
    @Column(name = "ds_local", length = 200)
    private String descricao;

    @NotBlank
    @Column(name = "localizacao", nullable = false)
    private String localizacao;

    @Column(name="st_local")
    @Enumerated(EnumType.STRING)
    private StatusLocal status;
}