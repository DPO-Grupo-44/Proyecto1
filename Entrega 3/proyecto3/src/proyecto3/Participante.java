package proyecto3;

import java.sql.Date;
import java.sql.Time;

public class Participante {

	private String nombre;
	private String correo;
	
	public Participante(String tnombre, String tcorreo) {
		nombre = tnombre;
		correo = tcorreo;
		
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public void crearActividad(String ttitulo, String tdescripcion, String ttipo, Date tfecha, 
			Time thorai, Time thoraf, Participante tparticipante)
	{
		Actividad nuevaActividad = new Actividad(ttitulo, tdescripcion, ttipo, tfecha, 
				thorai, thoraf, tparticipante);
	}
	
	
	
}
