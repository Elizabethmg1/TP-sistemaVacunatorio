package centroVacunacion;

public class Persona {
	private Integer dni;
	private Fecha fechaNac;
	private boolean trabajadorEsencial;
	private boolean enfermedadPreexistente;
	
	public Persona(Integer dni,Fecha fechaNac, boolean trabajadorEsencial, boolean enfermedadPreexistente) {
		this.dni=dni;
		this.fechaNac=fechaNac;
		this.trabajadorEsencial=trabajadorEsencial;
		this.enfermedadPreexistente=enfermedadPreexistente;
	}
	
	public int prioridad() {
		if(this.enfermedadPreexistente) {
			return 1;
		}
		else if(this.trabajadorEsencial) {
			return 2;
		}
		else return 3;
	}
	
	public int darEdad() {
		return this.fechaNac.diferenciaAnios(Fecha.hoy(), this.fechaNac);
	}
	
	public int darDni() {
		return this.dni;
	}
	
	public int damePrioridad() {
		return this.prioridad();
	}

}
