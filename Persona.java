package centroVacunacion;

public class Persona {
	private Integer dni;
	private Fecha fechaNac;
	private boolean trabajadorEsencial;
	private boolean enfermedadPreexistente;
	
	public Persona(Integer dni,Fecha fechaNac, boolean enfermedadPreexistente, boolean trabajadorEsencial) {
		this.dni=dni;
		this.fechaNac=fechaNac;
		this.trabajadorEsencial=trabajadorEsencial;
		this.enfermedadPreexistente=enfermedadPreexistente;
	}
	
	public int prioridad() {
		if(this.trabajadorEsencial) {
			return 1;
		}
		else if(this.darEdad()>=60) {
			return 2;
		}
		else if(this.enfermedadPreexistente) {
			return 3;
		}
		return 4; //resto de la poblacion
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

	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("[Persona con dni: ");
		sb.append(this.darDni());
		sb.append(" y edad:");
		sb.append(this.darEdad());
		sb.append("]");
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
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
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}
	
	
	

}
