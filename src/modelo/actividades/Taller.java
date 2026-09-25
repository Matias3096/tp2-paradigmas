package modelo.actividades;

public class Taller extends Actividad{
    //Atributos
    private boolean requiereNotebook;

    //Constructor
    public Taller(int idAct, String tituloAct, int cupoMaximo, boolean requiereNot){
        super(idAct,tituloAct, cupoMaximo);
        this.requiereNotebook = requiereNot;
    }



    //Metodos polimorficos
    @Override
    public double calcularCostoMateriales() {
        if(requiereNotebook) {
            return 5000.0;
        }
        return 2000.0;
    }

    @Override
    public void mostrarDetallesIdentificacion() {
        System.out.println("¿Requiere notebook? : " + requiereNotebook + "El costo es: " + calcularCostoMateriales() );
    }


    @Override
    public String getTipo() {
        return this.getClass().getSimpleName();
    }
}
