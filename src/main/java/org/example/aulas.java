package org.example;

public class aulas {
    private String idAula;
    private String reservadoPor;
    private int totalAulas;
    private int capacidad;
    private int disponibles;

    // Constructor
    public aulas(String idAula, String reservadoPor, int totalAulas, int capacidad, int disponibles) {
        this.idAula = idAula;
        this.reservadoPor = reservadoPor;
        this.totalAulas = totalAulas;
        this.capacidad = capacidad;
        this.disponibles = disponibles;
    }

    // Getters y Setters
    public String getIdAula() {
        return idAula;
    }

    public void setIdAula(String idAula) {
        this.idAula = idAula;
    }

    public String getReservadoPor() {
        return reservadoPor;
    }

    public void setReservadoPor(String reservadoPor) {
        this.reservadoPor = reservadoPor;
    }

    public int getTotalAulas() {
        return totalAulas;
    }

    public void setTotalAulas(int totalAulas) {
        this.totalAulas = totalAulas;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(int disponibles) {
        this.disponibles = disponibles;
    }
}
