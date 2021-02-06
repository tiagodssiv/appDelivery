package com.vaptvupt.com.vaptvupt;

public class ListaTalaPrincipal {

    public ListaTalaPrincipal(String titulo, int icone) {
        this.titulo = titulo;
        this.icone = icone;
    }

    private String titulo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getIcone() {
        return icone;
    }

    public void setIcone(int icone) {
        this.icone = icone;
    }

    private int icone;
}
