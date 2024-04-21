package org.example;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.example.Nodo;
import org.example.Telefono;

public class TelefonoCRUD {
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection<Document> collection;

    public static void conexion() {
        // Cadena de conexión, contiene la información de la instalación de MongoDB
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");

        // Se crean las configuraciones específicas para conexión y manejo de la db
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        // Crea la conexión y establece la comunicación
        mongoClient = MongoClients.create(settings);

        // Busca la base de datos y colección, si no existe, la crea
        database = mongoClient.getDatabase("tarea9");
        collection = database.getCollection("telefonos");


    }


    public static void cerrarConexion() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Conexión cerrada con MongoDB.");
        }
    }

    public static void crearTelefono() {
        // Teléfono 1
        Document telefono1 = new Document("marca", "Samsung")
                .append("modelo", "Galaxy S20")
                .append("sistemaOperativo", "Android")
                .append("tamanoPantalla", 6.2)
                .append("memoriaRAM", 8)
                .append("almacenamientoInterno", 128)
                .append("tieneCamara", true)
                .append("resolucionCamara", 64)
                .append("esSmartphone", true)
                .append("imei", "123456789012345");

        // Teléfono 2
        Document telefono2 = new Document("marca", "Apple")
                .append("modelo", "iPhone 12")
                .append("sistemaOperativo", "iOS")
                .append("tamanoPantalla", 6.1)
                .append("memoriaRAM", 4)
                .append("almacenamientoInterno", 64)
                .append("tieneCamara", true)
                .append("resolucionCamara", 12)
                .append("esSmartphone", true)
                .append("imei", "987654321098765");

        // Teléfono 3
        Document telefono3 = new Document("marca", "Google")
                .append("modelo", "Pixel 5")
                .append("sistemaOperativo", "Android")
                .append("tamanoPantalla", 6.0)
                .append("memoriaRAM", 8)
                .append("almacenamientoInterno", 128)
                .append("tieneCamara", true)
                .append("resolucionCamara", 12)
                .append("esSmartphone", true)
                .append("imei", "1357924680");

        // Teléfono 4
        Document telefono4 = new Document("marca", "OnePlus")
                .append("modelo", "8T")
                .append("sistemaOperativo", "Android")
                .append("tamanoPantalla", 6.55)
                .append("memoriaRAM", 12)
                .append("almacenamientoInterno", 256)
                .append("tieneCamara", true)
                .append("resolucionCamara", 48)
                .append("esSmartphone", true)
                .append("imei", "2468013579");

        // Teléfono 5
        Document telefono5 = new Document("marca", "Xiaomi")
                .append("modelo", "Redmi Note 9")
                .append("sistemaOperativo", "Android")
                .append("tamanoPantalla", 6.53)
                .append("memoriaRAM", 4)
                .append("almacenamientoInterno", 64)
                .append("tieneCamara", true)
                .append("resolucionCamara", 48)
                .append("esSmartphone", true)
                .append("imei", "3692581470");

        // Insertar los documentos en la colección
        collection.insertOne(telefono1);
        collection.insertOne(telefono2);
        collection.insertOne(telefono3);
        collection.insertOne(telefono4);
        collection.insertOne(telefono5);

        System.out.println("Teléfonos creados");
    }


    public static void actualizarTelefono(String imei, String nuevaMarca) {
        Document filtro = new Document("imei", imei);
        Document actualizacion = new Document("$set", new Document("marca", nuevaMarca));
        collection.updateOne(filtro, actualizacion);
        System.out.println("Teléfono actualizado");
    }

    public static void eliminarTelefono(String imei) {
        try {
            // Crear el filtro para buscar el teléfono por su IMEI
            Document filtro = new Document("imei", imei);

            // Eliminar el teléfono que coincide con el filtro
            DeleteResult result = collection.deleteOne(filtro);

            // Verificar si se eliminó exitosamente
            if (result.getDeletedCount() > 0) {
                System.out.println("Teléfono con IMEI " + imei + " eliminado exitosamente.");
            } else {
                System.out.println("No se encontró ningún teléfono con IMEI " + imei + ".");
            }
        } catch (Exception e) {
            System.err.println("Error al eliminar el teléfono: " + e.getMessage());
        }
    }
    public static Nodo obtenerTelefonosComoLinkedList() {
        Nodo cabeza = null;
        Nodo ultimo = null;
        try {
            FindIterable<Document> documents = collection.find();

            for (Document doc : documents) {
                Telefono telefono = documentToTelefono(doc);
                Nodo nodo = new Nodo(telefono);
                if (cabeza == null) {
                    cabeza = nodo;
                } else {
                    ultimo.setSiguiente(nodo);
                }
                ultimo = nodo;
            }
        } catch (Exception e) {
            System.err.println("Error al obtener los teléfonos: " + e.getMessage());
        }
        return cabeza;
    }

    private static Telefono documentToTelefono(Document doc) {
        Telefono telefono = new Telefono();
        telefono.setMarca(doc.getString("marca"));
        telefono.setModelo(doc.getString("modelo"));
        telefono.setSistemaOperativo(doc.getString("sistemaOperativo"));
        telefono.setTamanoPantalla(doc.getDouble("tamanoPantalla"));
        telefono.setMemoriaRAM(doc.getInteger("memoriaRAM"));
        telefono.setAlmacenamientoInterno(doc.getInteger("almacenamientoInterno"));
        telefono.setTieneCamara(doc.getBoolean("tieneCamara"));
        telefono.setResolucionCamara(doc.getInteger("resolucionCamara"));
        telefono.setEsSmartphone(doc.getBoolean("esSmartphone"));
        telefono.setImei(doc.getString("imei"));
        return telefono;
    }
}
