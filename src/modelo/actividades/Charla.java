package modelo.actividades;

public class Charla extends Actividad{

    //Atributos
    private String disertante;

    //Constructor
    public Charla(int idAct, String tituloAct, int cupoMaximo,String disertante){
        super(idAct, tituloAct, cupoMaximo);
        this.disertante = disertante;

    }


    @Override
    public double calcularCostoMateriales() {
        return 0.0;
    }

    @Override
    public void mostrarDetallesIdentificacion() {
        System.out.println("\nDisertante: " + disertante + "\n El costo es gratuito");

    }

    @Override
    public String getTipo() {
        return this.getClass().getSimpleName();
    }
}
