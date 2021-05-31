package centroVacunacion;

public class Persona {
	private int edad;
	private int dni;
	private boolean trabajadorEsencial;
	private boolean enfermedadPreexistente;
	
	public Persona(int edad, int dni, boolean trabajadorEsencial, boolean enfermedadPreexistente) {
		this.edad=edad;
		this.dni=dni;
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
		return this.edad;
	}
	
	public int darDni() {
		return this.dni;
	}
	
	public int damePrioridad() {
		return this.prioridad();
	}

}
