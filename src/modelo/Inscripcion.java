package modelo;

import modelo.actividades.Actividad;

import java.time.LocalDate;

public class Inscripcion {


    //Atributos
    private LocalDate fecha;
    private String estado;


    //Relaciones
    private Estudiante inscribe;
    private Actividad actividad;

    //Constructor
    public Inscripcion(Estudiante inscribe, Actividad actividad){

        this.inscribe = inscribe;
        this.actividad = actividad;
        this.fecha = LocalDate.now();
        this.estado = "CONFIRMADA";

    }

    //getters y setters
    public LocalDate getFecha(){
        return fecha;
    }
    public void setFecha(LocalDate fecha){
        this.fecha = fecha;
    }
    public String getEstado(){
        return estado;
    }
    public void confirmarInscripcion(){
        this.estado = "INSCRIPCION FONFIRMADA";
    }

    //Get de la relacion
    public Estudiante getEstudiante(){
        return inscribe;
    }
    public Actividad getActividad(){
        return actividad;
    }


}
