package modelo.actividades;

import excepciones.CupoExcedidoException;
import modelo.Estudiante;
import modelo.Inscripcion;

import java.util.ArrayList;
import java.util.List;

public abstract class Actividad {



    protected int idAct;
    protected String tituloAct;
    private int cupoMaximo;

    //Estaticos
    public static final int cupoMinimo;
    static {
        cupoMinimo =0;
        System.out.println("Se cargo la clase actividad");
    }

    //Relaciones
    //private Estudiante inscribe;
    private List<Inscripcion> inscripciones;

    //cero a muchos
    private List<Estudiante> inscribe = new ArrayList<>();

    //Metodos
    public Inscripcion inscribir(Estudiante estudiante ) throws  CupoExcedidoException {
        //Validacion si hay cupo disponible
        if (inscripciones.size()>= cupoMaximo){
            throw new CupoExcedidoException( "Cupo lleno, no se puede inscribir al estudiante" + estudiante.getNombre() + ". Cupo maximo alcanzado");
        }
        //Si se crea una nueva inscripcion
        Inscripcion inscripcion = new Inscripcion(estudiante, this);
        inscripciones.add(inscripcion);

        System.out.println("Estudiante " + inscripcion.getEstudiante().getNombre()+
                "Inscripto en actividad " + tituloAct);

        return inscripcion;
    }
    public void mostrarInscripciones(){

        //Si quiero recorrer todas las incripciones, debo hacer un for con
        //las inscripciones como parametro
        for(Inscripcion ins : inscripciones){
            System.out.println( " *** INSCRIPCION ***  " +
                    "\n Estudiante:  " + ins.getEstudiante().getNombre() +
                    " \nActividad: "+ins.getActividad().getTituloAct() +
                    "\n Estado: " + ins.getEstado() +
                    "\nFECHA:" + ins.getFecha() );
        }
    }

    //Constructor
    public Actividad(int idAct, String tituloAct, int cupoMaximo){
        this.idAct = idAct;
        this.tituloAct = tituloAct;
        this.cupoMaximo = cupoMaximo;
        this.inscripciones = new ArrayList<>(); //en esta linea inicializa la lista
    }


    //Habian faltado los getters y setters

    public String getTituloAct() {
        return tituloAct;
    }

    public final void mostrarIdentificacion(){
        System.out.println("- " + getTipo() + ": " + tituloAct + " (id=" + idAct + ")" + " - Cupo máximo: " + cupoMaximo );
        mostrarDetallesIdentificacion();
    }
    public abstract double calcularCostoMateriales();
    public abstract void mostrarDetallesIdentificacion();
    public abstract String getTipo();


}
