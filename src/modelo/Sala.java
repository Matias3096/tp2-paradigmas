package modelo;

public class Sala {

    private int idSala;
    private String nombre;

    public Sala (int id, String nombre){
        this.idSala = id;
        this.nombre = nombre;
    }

    public int getidSala(){
        return idSala;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getNombre(){
        return nombre;
    }
}
