package proyecto3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.*;
import java.util.ArrayList;

public class Principal {
	
	Laboratorio lab = new Laboratorio();

	public static void main(String [] args) {
	
	    Principal Consola = new Principal();
	    Consola.ejecutarAplicacion();
	    
	    
	  }
	public void ejecutarAplicacion() {
		crearnuevoProyecto();
	}
	
	public void crearnuevoProyecto() {
		String nombreproy = input("Ingrese nombre de proyecto");
		String descrproy = input("Ingrese la descripcion del proyecto");
		Proyecto proyectoNuevo = new Proyecto(nombreproy, descrproy, LocalDateTime.now(),
				null, LocalDateTime.now());
		lab.agregarProyectos(proyectoNuevo);
		System.out.println("Agregue un dueño a su proyecto");
		Participante dueño = crearnuevoParticipante();
		proyectoNuevo.setDueño(dueño);
		proyectoNuevo.agregarParticipantes(dueño);
		agregarMasParticipantes(proyectoNuevo);
		agregarTipos(proyectoNuevo);		
		crearnuevaActividad(proyectoNuevo);
		
		
	}
	
	public void agregarMasParticipantes(Proyecto tproyecto) {
		boolean continuar = true;
		while (continuar) {
			String res = input("Desea agregar mas participantes? (1- Si | 2- No");
			int resint = Integer.parseInt(res);
			if (resint == 1) {
				Participante nuevoParticipante = crearnuevoParticipante();
				boolean yaesta = lab.buscarParticipante(nuevoParticipante.getNombre(), tproyecto);
				if (yaesta == false){
					tproyecto.agregarParticipantes(nuevoParticipante);
				}
				else {
					System.out.println("El participante ya se encuentra agregado al proyecto");
				}
		}
			else {
				continuar = false;
			}
			
		}
	}
		
	public void agregarTipos(Proyecto tproyecto) {
		boolean continuar = true;
		while (continuar) {
			String res = input("Desea agregar un tipo? 1- Si | 2- No");
			int resint = Integer.parseInt(res);
			if (resint == 1) {
				String nuevoTipo = input("Ingrese el nombre del nuevo tipo de actividad");
				boolean yaesta = tproyecto.buscarTipo(nuevoTipo);
				if (yaesta){
					System.out.println("El tipo ya ha sido agregado previamente");
				}
				else {
					tproyecto.agregarTipos(nuevoTipo);
				}
			
			}
			else {
				continuar = false;
			}
		}
	}
		
	public void agregarActividades(Proyecto tproyecto) {
		boolean continuar = true;
		while (continuar) {
			String res = input("Desea agregar una actividad? 1- Si | 2- No");
			int resint = Integer.parseInt(res);
			if (resint == 1) {
				crearnuevaActividad(tproyecto);
			}
			else {
				continuar = false;
			}
		}
	}
		
	
	
	public Participante crearnuevoParticipante() {
		String nombreest = input("Ingrese su nombre");
		String correoest = input("Ingrese su correo");
		Participante nuevoParticipante = new Participante(nombreest, correoest);
		return nuevoParticipante;
	}
	
	public void crearnuevaActividad(Proyecto tproyecto) {
		String titulo = input("Titulo");
		String descripcion = input("Descripcion");
		String tipo = noEstaTipo(tproyecto);
		LocalDate fecha = LocalDate.now();
		LocalTime horai = LocalTime.now();
		LocalTime horaf = LocalTime.now();
		Participante encargado = noEstaPar(tproyecto);
		Actividad nuevaActividad = new Actividad(titulo, descripcion, tipo, fecha, horai, 
				horaf, encargado);
		tproyecto.agregarActividades(nuevaActividad);
		}
	
	public Participante estaParticipante(String nombre, Proyecto tproyecto) {
		
		Participante res = null;
		ArrayList<Participante> listaparticipantes = tproyecto.getParticipantes();
		for (int j=0;j<listaparticipantes.size();j++) {
			String nombreencon = listaparticipantes.get(j).getNombre();
			if (nombre.equals(nombreencon)) {
				res = listaparticipantes.get(j);
			}
		}
		return res;
		
	}
	
	public Participante noEstaPar(Proyecto tproyecto) {
		Participante encargado = null;
		boolean incorrecto = true;
		while (incorrecto) {
			String nombre = input("Nombre del encargado");
			encargado = estaParticipante(nombre, tproyecto);
			if (encargado == null) {
				System.out.println("El estudiante no hace parte del proyecto");
			}
			else {
				incorrecto = false;
			}
		}
		return encargado;
	
	}
	
	
	public String noEstaTipo(Proyecto tproyecto) {
		String tipo = null;
		boolean incorrecto = true;
		while (incorrecto) {
			tipo = input("Tipo");
			boolean yaesta = tproyecto.buscarTipo(tipo);
			if (yaesta) {
				System.out.println("El tipo no hace parte del proyecto, por favor seleccione otro");
				
			}
			else {
				incorrecto = false;
			}
		}
		return tipo;
	
	}
	
	public ArrayList<String> buscarProyectos (Participante nombreparticipante){
		
		return null;
	}
	
	
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
}
