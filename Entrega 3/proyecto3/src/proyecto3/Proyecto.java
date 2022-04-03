package proyecto3;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Proyecto {

	private String nombre;
	private String descripcion;
	private LocalDateTime fechainicio;
	private Participante due�o;
	private ArrayList<Participante> listaparticipantes;
	private LocalDateTime fechafin;
	
	public Proyecto(String tnombre, String tdescripcion, LocalDateTime tfechai, 
			Participante tdue�o, LocalDateTime fechaf) {
		nombre = tnombre;
		descripcion = tdescripcion;
		fechainicio = tfechai;
		fechafin = fechaf;
		due�o = tdue�o;
		listaparticipantes = new ArrayList<Participante>();
	}
	
	public void agregarParticipantes(Participante tparticipante) {
		listaparticipantes.add(tparticipante);
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public LocalDateTime getFechaI() {
		return fechainicio;
	}
	
	public LocalDateTime getFechaF() {
		return fechafin;
	}
	
	public ArrayList<Participante> getParticipantes(){
		return listaparticipantes;
	}
}
