package org.example;

public class Pedido {

    private String direccion;
    private String tipo;
    private String estado;

    public Pedido(String direccion, String tipo, String estado) {
        this.direccion = direccion;
        this.tipo = tipo;
        this.estado = estado;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEstado() {
        return estado;
    }
}
