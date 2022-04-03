package proyecto3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;

public class Principal {

	public static void main(String [] args) {
	
	    
	    LocalDateTime tiempo = (LocalDateTime.now()).minusDays(5);
	    System.out.println(tiempo.getMinute());
	    System.out.println(tiempo.getMonthValue());
	    System.out.println(tiempo);
	    
	    Principal Consola = new Principal();
	    Consola.ejecutarAplicacion();
	    
	    
	  }
	public void ejecutarAplicacion() {
		crearnuevoParticipante();
	}
	
	public void crearnuevoProyecto() {
		String nombreproy = input("Ingrese nombre de proyecto");
		String descrproy = input("Ingrese la descripcion del proyecto");
	}
	
	public void crearnuevoParticipante() {
		String nombreest = input("Ingrese su nombre");
		String correoest = input("Ingrese su correo");
		Participante nuevoParticipante = new Participante(nombreest, correoest);
		
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
