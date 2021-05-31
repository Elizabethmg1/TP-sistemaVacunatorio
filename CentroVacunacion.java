package centroVacunacion;
import java.util.HashMap;
import java.util.HashSet;

public class CentroVacunacion {
	private int capacidadDiaria;
	private HashMap<Fecha,Turnos> fechaConTurnos;
	private HashSet<Persona> listaDeEspera;
	private Vacunatorio vacunatorio;
}

		public CentroVacunacion(int capacidadDiaria) {
		
		this.capacidadDiaria=capacidadDiaria;
		HashMap<Fecha,Turno> aux1=new HashMap<Fecha,Turno>();
		this.fechaConTurnos=aux1;
		HashSet<Persona> aux2=new HashSet<Persona>();
		this.listaDeEspera=aux2;
		Vacunatorio aux3=new Vacunatorio();
		this.vacunatorio=aux3;
	}
	
		public boolean ingresanVacunas(String nombre, int cantidad) {
		return this.vacunatorio.ingresanVacunas(nombre,cantidad);
	}
	
		public boolean altaDePersona(int edad, int dni, boolean trabajadorEsencial, boolean enfermedadPreexistente) {
		Persona aux=new Persona(edad,dni,trabajadorEsencial,enfermedadPreexistente);
		if(!this.listaDeEspera.contains(aux)) {
			this.listaDeEspera.add(aux);
			return true;
		}
		else {
			return false;
		}	
	}
	
		public boolean generarFechaVacunacion(int dia, int mes, int anio) {
			Fecha fecha=new Fecha(dia,mes,anio);
			Turnos turnos=new Turnos(this.capacidadDiaria);
			if (!this.fechaConTurnos.containsKey(fecha)) {
				this.fechaConTurnos.put(fecha, turnos);
			}
		}
	}
