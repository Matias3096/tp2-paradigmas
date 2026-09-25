package modelo;

public class Estudiante {


    //Atributos
    private String legajo;
    private String nombre;

    //Constructor
    public Estudiante(String legajo, String nombre){
        this.legajo = legajo;
        this.nombre = nombre;
    }

    //Getters y setters
    public String getLegajo(){
        return legajo;
    }
    public void setLegajo(String leg){
        if (legajo == null ){
            System.out.println("El legajo es nulo");
        } else {
            this.legajo = leg;
        }
    }
    public String getNombre(){
        return nombre;
    }

}
