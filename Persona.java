package centroVacunacion;

public class Persona {
	private Fecha fechaNac;
	private int dni;
	private boolean trabajadorEsencial;
	private boolean enfermedadPreexistente;
	
	public Persona( int dni,Fecha fechaNac, boolean trabajadorEsencial, boolean enfermedadPreexistente) {
		this.fechaNac=fechaNac;
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
	
	public int darEdad(Fecha hoy) {
		return this.fechaNac.diferenciaAnios(hoy, this.fechaNac);
	}
	
	public int darDni() {
		return this.dni;
	}
	
	public int damePrioridad() {
		return this.prioridad();
	}

	@Override
	public String toString() {
		return "Persona [dni=" + dni + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dni;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		if (dni != other.dni)
			return false;
		return true;
	}

	

}
