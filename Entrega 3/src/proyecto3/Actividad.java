package proyecto3;

import java.sql.Time;
import java.sql.Date;

public class Actividad {
	private String titulo;
	private String descripcion;
	private String tipo;
	private Date fecha;
	private Time horai;
	private Time horaf;
	private Participante encargado;
	
	public Actividad (String ttitulo, String tdescripcion, String ttipo, Date tfecha, 
			Time thorai, Time thoraf, Participante tparticipante) {
		titulo = ttitulo;
		descripcion = tdescripcion;
		tipo = ttipo;
		fecha = tfecha;
		horai = thorai;
		horaf = thoraf;
		encargado = tparticipante;
		
	}
}
