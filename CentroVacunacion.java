package centroVacunacion;
import java.util.HashMap;
import java.util.HashSet;

public class CentroVacunacion {
	private String nombre;
	private int capacidadDiaria;
	private HashMap<Fecha,Turnos> fechaConTurnos;
	private HashSet<Persona> listaDeEspera;
	private Vacunatorio vacunatorio;

		public CentroVacunacion(String nombre,int capacidadDiaria) {
			
		this.nombre=nombre;
		this.capacidadDiaria=capacidadDiaria;
		HashMap<Fecha,Turnos> aux1=new HashMap<Fecha,Turnos>();
		this.fechaConTurnos=aux1;
		HashSet<Persona> aux2=new HashSet<Persona>();
		this.listaDeEspera=aux2;
		Vacunatorio aux3=new Vacunatorio();
		this.vacunatorio=aux3;
	}
	
		public boolean ingresarVacunas(String nombre, int cantidad, Fecha fecha) {
		return this.vacunatorio.ingresanVacunas(nombre,cantidad,fecha);
	}
	
		public boolean inscribirPersona(int dni,Fecha fecha, boolean trabajadorEsencial, boolean enfermedadPreexistente) {
		Persona aux=new Persona(dni,fecha,trabajadorEsencial,enfermedadPreexistente);
		if(!this.listaDeEspera.contains(aux)) {
			this.listaDeEspera.add(aux);
			return true;
		}
		else {
			return false;
		}	
	}
	
		public boolean generarTurnos(Fecha fechaInicial) {
			Turnos turnos=new Turnos(this.capacidadDiaria);
			if (!this.fechaConTurnos.containsKey(fechaInicial)) {
				this.fechaConTurnos.put(fechaInicial, turnos);
			}
		}
		
		
		public HashSet<Persona> listaDeEspera(){
			return this.listaDeEspera;
		}
		
		public int vacunasDisponibles() { //todas las vacunas
			this.vacunatorio.darCantidadDeVacunas();
		}
		
		public int vacunasDisponibles(String vacuna) {  //vacuna especifica
			this.vacunatorio.darCantidadDeVacunas(vacuna);
		}
		
		public HashSet<Persona> turnosConFecha(Fecha fecha){  //
			if(this.fechaConTurnos.containsKey(fecha)) {
				return this.fechaConTurnos.get(fecha).darListaPersona();
			}
		}
		
		public HashMap<Persona,String> reporteVacunacion() {
			return null; //completar
	}
		
		public HashMap<Persona,String> reporteVacunasVencidas(){
			return null; //completar
		}
		
		public boolean vacunarInscripto(int dni, Fecha fecha) {
			return false;
		}
}
