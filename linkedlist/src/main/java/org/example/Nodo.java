package org.example;

public class Nodo {
    private Telefono telefono;
    private Nodo siguiente;

    public Nodo(Telefono telefono) {
        this.telefono = telefono;
        this.siguiente = null;
    }

    public Telefono getTelefono() {
        return telefono;
    }

    public void setTelefono(Telefono telefono) {
        this.telefono = telefono;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}
