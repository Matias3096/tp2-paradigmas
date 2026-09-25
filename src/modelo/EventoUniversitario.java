package modelo;

import modelo.actividades.Actividad;
import modelo.actividades.Charla;
import modelo.actividades.Taller;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventoUniversitario implements Serializable {
    //Atributos
    public final String id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;

    //Estaticos
    private static int cantidadEventos;

    //Inicializacion de estativos
    static {
        cantidadEventos = 0;
    }

    //Relaciones
    public java.util.List<Actividad> actividades;
    public Sala agrega;

    //Constructor
    public EventoUniversitario(String id, String titulo, double costoBase, boolean gratuito){
        this.id =id;
        this.titulo= titulo;
        this.costoBase =  gratuito ? 0 : costoBase;
        this.gratuito = gratuito;
        this.actividades = new ArrayList<>();
        cantidadEventos++;
    }

    //Constructor de copia
    public EventoUniversitario(EventoUniversitario otro){
        this.id= otro.id;
        this.titulo = otro.titulo;
        this.costoBase = otro.costoBase;
        this.gratuito = otro.gratuito;
        this.actividades = new ArrayList<>();
        cantidadEventos++;
    }

    //Agregacion dinamica
    public void asignarSala(Sala agrega){
        this.agrega= agrega;
    }
    public void crearActividad(int id, String titulo, int cupo, String tipoActividad, String datoAdicional){
        switch (tipoActividad.toLowerCase()){
            case "charla" :
                Actividad charla = new Charla(id,titulo, cupo, datoAdicional);
                actividades.add(charla);
                break;
            case "taller":
                boolean requiereNotebook = Boolean.parseBoolean(datoAdicional);
                Actividad taller = new Taller(id, titulo, cupo, requiereNotebook);
                actividades.add(taller);
                break;
            default:
                System.out.println("TIpo de actividad no reconocido");
        }
    }

    public double calcularCostoEstimado(){
        if(gratuito){
            return 0.0;
        }
        double costoTotal = costoBase;
        for(Actividad actividad : actividades) {
            costoTotal += actividad.calcularCostoMateriales();
        }
        return costoTotal * 1.21;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getCostoBase() {
        return costoBase;
    }

    public void setCostoBase(double costoBase) {
        this.costoBase = costoBase;
    }

    public boolean isGratuito() {
        return gratuito;
    }

    public void setGratuito(boolean gratuito) {
        this.gratuito = gratuito;
    }

    public static int getCantidadEventos() {
        return cantidadEventos;
    }

    public String getId() {
        return id;
    }

    public void setAgrega(Sala agrega) {
        this.agrega = agrega;
    }

    public java.util.List<Actividad> getActividadesComposicion() {
        return Collections.unmodifiableList(actividades);
    }

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }

    //Mostrar datos, para el polimorfismo
    public void mostrarDatos(){

        System.out.println("**DATOS DEL EVENTO**"+
                "\nId: " + id
                +"\ntitulo: "+ titulo+
                "\n Costo : $"+calcularCostoEstimado()+
                "\n ¿Es gratuito? :"+gratuito+
                "\n  Sala asignada : "+ (agrega != null ? agrega.getNombre() : "Sin sala") + "\n");

        for(Actividad actividad : actividades){
            actividad.mostrarIdentificacion();
            actividad.mostrarInscripciones();
        }

    }


    //Persistencia
     public boolean persistirEvento() throws IOException, ClassNotFoundException {
        String nombreArchivo = "Evento" + this.id + ".dat";
        try
            (ObjectOutputStream oss =
                    new ObjectOutputStream( new FileOutputStream(nombreArchivo))){

            oss.writeObject(this);
            return true;
            }
     }

     //Recuperar eventos
    public EventoUniversitario recuperarEvento(String id) throws IOException, ClassNotFoundException {

        String nombreArchivo = "Evento_" + id + ".dat";
        try (ObjectInputStream ois =
                new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            return (EventoUniversitario) ois.readObject();
        }
    }

}
