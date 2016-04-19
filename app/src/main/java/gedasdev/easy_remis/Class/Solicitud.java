package gedasdev.easy_remis.Class;

/**
 * Created by Colorado on 17/04/2016.
 */
public class Solicitud {

    public int getNumeroSolicitud() {
        return numeroSolicitud;
    }

    public void setNumeroSolicitud(int numeroSolicitud) {
        this.numeroSolicitud = numeroSolicitud;
    }

    private int numeroSolicitud;
    private long latitudOrigen;
    private long longitudOrigen;
    private long latitudDestino;
    private long longitudDestino;
    private String origenDireccion;
    private String destinoDireccion;
    private String referencia;
    private double precioEstimado;
    private double precioFinal;


    private String estado;
    private String Chofer;
    private long distancia;

    public Solicitud(int numeroSolicitud, String origenDireccion, String destinoDireccion, double precioFinal) {
        this.numeroSolicitud = numeroSolicitud;
        this.origenDireccion = origenDireccion;
        this.destinoDireccion = destinoDireccion;
        this.precioFinal = precioFinal;
    }

    public Solicitud(long latitudOrigen, long longitudOrigen, long latitudDestino, long longitudDestino, String origenDireccion, String destinoDireccion, String referencia, double precioEstimado, double precioFinal, String estado, String chofer, long distancia) {
        this.latitudOrigen = latitudOrigen;
        this.longitudOrigen = longitudOrigen;
        this.latitudDestino = latitudDestino;
        this.longitudDestino = longitudDestino;
        this.origenDireccion = origenDireccion;
        this.destinoDireccion = destinoDireccion;
        this.referencia = referencia;
        this.precioEstimado = precioEstimado;
        this.precioFinal = precioFinal;
        this.estado = estado;
        Chofer = chofer;
        this.distancia = distancia;
    }



    public long getLatitudOrigen() {
        return latitudOrigen;
    }

    public void setLatitudOrigen(long latitudOrigen) {
        this.latitudOrigen = latitudOrigen;
    }

    public long getLongitudOrigen() {
        return longitudOrigen;
    }

    public void setLongitudOrigen(long longitudOrigen) {
        this.longitudOrigen = longitudOrigen;
    }

    public long getLatitudDestino() {
        return latitudDestino;
    }

    public void setLatitudDestino(long latitudDestino) {
        this.latitudDestino = latitudDestino;
    }

    public long getLongitudDestino() {
        return longitudDestino;
    }

    public void setLongitudDestino(long longitudDestino) {
        this.longitudDestino = longitudDestino;
    }

    public String getOrigenDireccion() {
        return origenDireccion;
    }

    public void setOrigenDireccion(String origenDireccion) {
        this.origenDireccion = origenDireccion;
    }

    public String getDestinoDireccion() {
        return destinoDireccion;
    }

    public void setDestinoDireccion(String destinoDireccion) {
        this.destinoDireccion = destinoDireccion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public double getPrecioEstimado() {
        return precioEstimado;
    }

    public void setPrecioEstimado(double precioEstimado) {
        this.precioEstimado = precioEstimado;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getChofer() {
        return Chofer;
    }

    public void setChofer(String chofer) {
        Chofer = chofer;
    }

    public long getDistancia() {
        return distancia;
    }

    public void setDistancia(long distancia) {
        this.distancia = distancia;
    }







}
