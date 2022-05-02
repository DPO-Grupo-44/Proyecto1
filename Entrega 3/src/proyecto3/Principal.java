package proyecto3;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;

public class Principal {
	
	Laboratorio lab = new Laboratorio();
	CargaDeDatos cargar = new CargaDeDatos();

	public static void main(String [] args) {
	
	    Principal Consola = new Principal();
	    Consola.ejecutarAplicacion();
	    
	    
	  }
	public void ejecutarAplicacion() {
		boolean continuar = true;
		while(continuar) {
		try {
			mostrarMenu();
			int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opciÃ³n"));
			ArrayList<Proyecto> proyectosdisp = lab.getProyectos();
			if (opcion_seleccionada == 1) {
				cargar.leerArchivo_proyectos(lab);
				cargar.leerArchivo_proyectos(lab);
			}	
			else if (opcion_seleccionada == 2)
			{
				
				crearnuevoProyecto();
			}
			else if (opcion_seleccionada == 3)
			{
				if (proyectosdisp.size() < 1) {
					System.out.println("No hay proyectos disponibles, cree uno primero");
				}
				else {
					int pos = mostrarProyectos(proyectosdisp);
					Proyecto ubicacion = proyectosdisp.get(pos);
					agregarMasParticipantes(ubicacion);
				}
			}
			else if (opcion_seleccionada == 4)
			{
				if (proyectosdisp.size() < 1) {
					System.out.println("No hay proyectos disponibles, cree uno primero");
				}
				else {
					int pos = mostrarProyectos(proyectosdisp);
					Proyecto ubicacion = proyectosdisp.get(pos);
					agregarActividades(ubicacion);
				}
			}
			else if (opcion_seleccionada == 5) {
				int pos = mostrarProyectos(proyectosdisp);
				Proyecto ubicacion = proyectosdisp.get(pos);
				ArrayList<Actividad>lista = ubicacion.getActividades();
				if (lista.size() < 1) {
					System.out.println("No hay actividades disponibles, cree una primero");
				}
				else {
					int posact = mostrarActividades(lista);
					Actividad ubact = lista.get(posact);
					String res = input("Seleccione la hora");
					ubact.setHora(res);
					System.out.println(ubact.calcularDuracion());
				}
				
				
			}
			else if (opcion_seleccionada == 6) {
				int pos = mostrarProyectos(proyectosdisp);
				Proyecto ubicacion = proyectosdisp.get(pos);
				ArrayList<Actividad> lista = ubicacion.getActividades();
				crearCalendario(lista);
			}
			
			else if (opcion_seleccionada == 7) {
				continuar = false;
			}
			
			else {
				System.out.println("Opcion no valida, por favor seleccione otra");
			}
		}
		catch (NumberFormatException e)
		{
			System.out.println("Debe seleccionar uno de los nÃºmeros de las opciones.");
		}
	}
	}
	
	public void mostrarMenu()
	{
		System.out.println("\nOpciones de la aplicaciÃ³n\n");
		System.out.println("1. Cargar Un Archivo");
		System.out.println("2. Crear Un Proyecto");
		System.out.println("3. Agregar Un Participante");
		System.out.println("4. Crear Una Actividad");
		System.out.println("5. Modificar Una Actividad");
		System.out.println("6. Salir de la Aplicacion");
		System.out.println("7. Salir de la Aplicacion");
		
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
		agregarTipos(proyectoNuevo);		
		
		
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
		LocalTime horai = LocalTime.now();
		String titulo = input("Titulo");
		String descripcion = input("Descripcion");
		String tipo = noEstaTipo(tproyecto);
		LocalDate fecha = LocalDate.now();
		LocalDate fechaf = LocalDate.now();
		LocalTime horaf = LocalTime.now();
		Participante encargado = noEstaPar(tproyecto);
		Actividad nuevaActividad = new Actividad(titulo, descripcion, tipo, fecha, fechaf, horai, 
				horaf, encargado);
		tproyecto.agregarActividades(nuevaActividad);
		System.out.println("Actividad agregada de forma exitosa, pulse enter");
		Scanner teclado = new Scanner(System.in);
		teclado.nextLine(); 
		System.out.println("\n\t\tSe esta tomando el tiempo de la actividad, presione ENTER para continuar..."); //Mensaje en pantalla
		teclado.nextLine();
	    try
	    {
	    	nuevaActividad.setHoraf(LocalTime.now());
			String duracion = nuevaActividad.calcularDuracion();
			System.out.println("La duracion fue de " + duracion);
	    }
	    catch(Exception e)
	    {}
		
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
				System.out.println("El estudiante no hace parte del proyecto, por favor seleccione otro");
				System.out.println("Tipos validos: \n");
				
				for (int j=0;j<tproyecto.getParticipantes().size();j++) {
					System.out.println(tproyecto.getParticipantes().get(j).getNombre());
				}
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
				incorrecto = false;
				
			}
			else {
				System.out.println("El tipo no hace parte del proyecto, por favor seleccione otro");
				System.out.println("Tipos validos: \n");
				for (int j=0;j<tproyecto.getTipos().size();j++) {
					System.out.println(tproyecto.getTipos().get(j));
				}
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
	public int mostrarProyectos(ArrayList<Proyecto> proyectosdisp) {
		System.out.println("Seleccione el proyecto del que quiere hacer las modificaciones");
		for (int j=0;j<proyectosdisp.size();j++) {
			System.out.println(j+"-" + " " + proyectosdisp.get(j).getNombre());
		}
		int respuesta = Integer.parseInt(input("Numero"));
		return respuesta;
	}
	
	public int mostrarActividades(ArrayList<Actividad> actividadesdisp) {
		System.out.println("Seleccione la actividad de la que va a realizar cambios");
		for (int j=0;j<actividadesdisp.size();j++) {
			System.out.println(j+"-" + " " + actividadesdisp.get(j).getNombre());
	    }
		int respuesta = Integer.parseInt(input("Numero"));
		return respuesta;
	}	
	
	public void crearCalendario(ArrayList<Actividad> actividades) {
		int ano = 2022;
		JPanel calendario = new JPanel();
		calendario.setBackground(Color.WHITE);
		
		for(int i = 1; i <= 12; i++) {
			
			System.out.println("\nMes: "+ i );
			System.out.println("Dom\tLun\tMar\tMier\tJue\tVie\tSab");
			
			int dias = diasMes(ano, i);
			int conDia = 0;
			int z = zeller(ano, i);
			for(int k = 0; k < z; k++) {
				conDia++;
				System.out.print("\t");
			}
			
			
			for(int j =1; j <= dias; j++) {
				String res = j + "\t";
				int l = 1;
				for(l= 1; l <= actividades.size(); l++) {
					int fecha = actividades.get(l-1).getFecha().getMonthValue();
					int dia = actividades.get(l-1).getFecha().getDayOfMonth();
					if((fecha == i)&&(dia == j)) {
						res = j+ "*" + "\t"; 
					}
				}
				System.out.print(res);
				conDia++;
				if(conDia == 7) {
					System.out.println();
					conDia = 0;
				}
			}
		}
		
	}
	
	private static int zeller(int ano, int mes) {
		int a = (14 - mes) / 12;
		int y = ano - a;
		int m = mes + 12 * a - 2;
		int dia = 1, d;
		d = (dia + y + y / 4 - y / 100 + y / 400 + (31 * m) / 12) % 7;
		return (d);
	}
	
	public static int diasMes(int ano, int mes) {
		if(mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || 	mes == 10 || mes == 12) {
			return 31;
		}
		else if(mes == 2) {
			return 28;
		}
		else {
			return 30;
		}
		
	}
	
}
