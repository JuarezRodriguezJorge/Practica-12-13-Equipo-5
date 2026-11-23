package modelo;

/** Observer: el modelo avisa al controlador sin conocer la vista. */
public interface ObservadorRestaurante {
    void onEvento(String actor, String mensaje);
}
