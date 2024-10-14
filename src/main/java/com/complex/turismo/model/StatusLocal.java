package com.complex.turismo.model;

public enum StatusLocal {


    DISPONIVEL("Disponível"),
    INDISPONIVEL("Indisponível");

    private final String label;

    StatusLocal(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}