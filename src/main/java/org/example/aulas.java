package org.example;

public class aulas {
    String id;
    String nombre;
    int capacidad;
    boolean disponible;

    //construtor

    public aulas(String id, String nombre, int capacidad, boolean disponible){

        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.disponible = disponible;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

}
