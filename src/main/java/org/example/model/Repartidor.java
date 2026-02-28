package org.example;

public class Repartidor {

    private int id;
    private String nombre;
    private String telefono;

    public Repartidor(int id, String nombre, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Repartidor(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }

    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}