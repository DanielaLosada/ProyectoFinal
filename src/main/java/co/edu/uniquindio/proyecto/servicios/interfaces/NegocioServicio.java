package co.edu.uniquindio.proyecto.servicios.interfaces;

public interface NegocioServicio {
    void crearNegocio();
    void actualizarNegocio();
    void eliminarNegocio();
    void buscarNegocios();
    void filtrarPorEstado();

    void listarNegocioPropietario();

    void cambiarEstado();

    void registrarRevision();


}
