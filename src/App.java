import excepciones.CupoExcedidoException;
import modelo.Estudiante;
import modelo.EventoUniversitario;
import modelo.Sala;
import modelo.actividades.Actividad;

import javax.swing.undo.CannotUndoException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class App {
    public static void main(String[] args) {



        //1. Creamos una lista de estudiantes
        List<Estudiante> ListaEstudiantes = new ArrayList<>();

        //2. Creamos varios estudiantes
        Estudiante est1 = new Estudiante("L45466","Matias Autopartes");
        Estudiante est2 = new Estudiante("L7474","Expreso Amarillo");

        Estudiante est3 = new Estudiante("L48476","Azul Rosado");
        Estudiante est4 = new Estudiante("L2442","Ventana Cerrada");

        Estudiante est5 = new Estudiante("L42426","Butaca rota");
        Estudiante est6 = new Estudiante("L2322","Mochila roja");

        Estudiante est7 = new Estudiante("L4866","Zapatillas negras");
        Estudiante est8 = new Estudiante("L744","Piso gris");

        Estudiante est9 = new Estudiante("L42466","Flor Durazno");
        Estudiante est10 = new Estudiante("L0074","Montaña nevada");


        //3. Construimos eventos
        EventoUniversitario ev1 = new EventoUniversitario("132","Hackaton",1000.0,false);
        EventoUniversitario ev2 = new EventoUniversitario("134","Lanzamiento de impresoras",0.0,true);
        EventoUniversitario ev3 = new EventoUniversitario("Id03","Hackaton", 0.0, true);
        EventoUniversitario ev4 = new EventoUniversitario("Id04","Charla pps",0,true);
        EventoUniversitario ev5 = new EventoUniversitario("Id05","Visita de empresas", 0.0, true);
        EventoUniversitario ev6 = new EventoUniversitario("Id06","Choripaneada",2000,false);
        EventoUniversitario ev7 = new EventoUniversitario("Id07","Charla Base de datos", 1500, false);
        EventoUniversitario ev8 = new EventoUniversitario("Id08","Curso electricidad",2000,false);


        //3.1 Crear lista de eventos universitarios
        ArrayList<EventoUniversitario> listaDeEventos = new ArrayList<>();
        listaDeEventos.add(ev1);
        listaDeEventos.add(ev2);
        listaDeEventos.add(ev3);
        listaDeEventos.add(ev4);
        listaDeEventos.add(ev5);
        listaDeEventos.add(ev6);
        listaDeEventos.add(ev7);
        listaDeEventos.add(ev8);

        //4. Construimos una o varias salas
        Sala sala1 = new Sala(123,"Sum al lado de la cancha");
        Sala sala2 = new Sala (11, "Salon de conferencias");

        //5.Asignamos eventos a las salas
        ev1.asignarSala(sala1);
        ev2.asignarSala(sala2);
        ev3.asignarSala(sala1);
        ev4.asignarSala(sala2);
        ev5.asignarSala(sala1);
        ev6.asignarSala(sala2);
        ev7.asignarSala(sala1);
        ev8.asignarSala(sala2);

        //6. Creamos actividades para cada evento
        ev1.crearActividad(1,"Charla previa al evento",30,"Charla","Prof Cortez");
        ev2.crearActividad(2,"Programacion en java",30,"taller", "true" );

        //7. Estudiantes se inscriben en actividad y agregamos excepcion para lanzar excepcion

        try {
            //Inscribir estudiantes
            ev1.getActividadesComposicion().get(0).inscribir(est1);
            ev1.getActividadesComposicion().get(0).inscribir(est2);


            ev2.getActividadesComposicion().get(0).inscribir(est1);
            ev2.getActividadesComposicion().get(0).inscribir(est2);

            ev1.getActividadesComposicion().get(0).inscribir(est3);
            ev2.getActividadesComposicion().get(0).inscribir(est1);

            ev1.getActividadesComposicion().get(0).inscribir(est4);
            ev2.getActividadesComposicion().get(0).inscribir(est4);

            ev2.getActividadesComposicion().get(0).inscribir(est5);
            ev1.getActividadesComposicion().get(0).inscribir(est5);

            ev1.getActividadesComposicion().get(0).inscribir(est6);
            ev2.getActividadesComposicion().get(0).inscribir(est6);

            //Persistir
            System.out.println("\nPersistiendo eventos");
            ev1.persistirEvento();
            ev2.persistirEvento();
            System.out.println("\n Eventos persistidos en memoria");

            //Recuperar(LEER)
            System.out.println("\n Recuperando Evento");
            EventoUniversitario recuperarDesdeArchivo = ev1.recuperarEvento(ev1.getId());
            System.out.println("Evento recuperado desde archivo" + recuperarDesdeArchivo.getTitulo());
            
        } catch (CupoExcedidoException e){
            System.out.println("Error al inscribir: " + e.getMessage());
        } catch (IOException e){
            System.out.println("No se pudo guardar el archivo " + e.getMessage() );
        } catch (ClassNotFoundException e) {
            System.out.println("No se pudo recuperar el evento " + e.getMessage() );
        } finally {
            System.out.println("El finally fue ejecutado");
        }


        //8. Mostramos el array de eventos y las actividades
        for(EventoUniversitario evento : listaDeEventos){
            evento.mostrarDatos();

            for(Actividad activi : evento.getActividadesComposicion()){
                activi.mostrarInscripciones();
            }
        }

        //9. mostramos el total de eventos creados
        System.out.println("Eventos creados " + EventoUniversitario.getCantidadEventos());


        //10 para ejercicio 1 Tp2

    }
}