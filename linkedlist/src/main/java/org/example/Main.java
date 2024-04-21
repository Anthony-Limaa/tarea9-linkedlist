package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Establecer la conexión con MongoDB
        TelefonoCRUD.conexion();

        // Crear un teléfono
        //TelefonoCRUD.crearTelefono();

        // Actualizar un teléfono
        //TelefonoCRUD.actualizarTelefono("123456789012345", "NuevaMarca");

        // Eliminar un teléfono
        //TelefonoCRUD.eliminarTelefono("123456789012345");

        // Obtener los teléfonos como LinkedList
        Nodo cabeza = TelefonoCRUD.obtenerTelefonosComoLinkedList();

        // Imprimir los teléfonos obtenidos como LinkedList
        System.out.println("Teléfonos obtenidos como LinkedList:");
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.println(actual.getTelefono());
            actual = actual.getSiguiente();
        }

        // Cerrar la conexión con MongoDB
        TelefonoCRUD.cerrarConexion();
    }
}