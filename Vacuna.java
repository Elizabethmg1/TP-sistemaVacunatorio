package centroVacunacion;

public abstract class Vacuna {
	
	protected Fecha fechaDeIngreso;
	
	public Vacuna(Fecha fechaDeIngreso) {
		this.fechaDeIngreso=fechaDeIngreso;
	}
	
	
	public int paraQueEdadSos() {
		return 0;
	}
	
	public int queTemperaturanecesitas() {
		return 0;
	}
	
	//metodo abstracto para que lo definan las clases ConVencimiento y SinVencimiento
	public abstract boolean vences();
	
	public boolean estaVencida() {
		return false;
	}
	
	public Fecha getFechaVencimiento() {
		return null;
	}
	
}


