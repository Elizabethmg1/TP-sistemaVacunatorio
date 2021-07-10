package centroVacunacion;

public class ConVencimiento extends Vacuna{
	private Fecha fechaVencimiento; //

	ConVencimiento(String nombre, Fecha fechaDeIngreso) {
		super(fechaDeIngreso);
		if(nombre.equals("Moderna")) {
			Fecha vence=new Fecha(fechaDeIngreso.dia(),fechaDeIngreso.mes(),fechaDeIngreso.anio());
			vence.avanzarDias(60);
			this.fechaVencimiento=vence;
		}
		else if(nombre.equals("Pfizer")) {
			Fecha vence=new Fecha(fechaDeIngreso.dia(),fechaDeIngreso.mes(),fechaDeIngreso.anio());
			vence.avanzarDias(30);
			this.fechaVencimiento=vence;
		}
	}
	@Override
	public boolean vences() {
		return true;
	}
	
	@Override
	public boolean estaVencida(){
		if (Fecha.hoy().posterior(this.getFechaVencimiento())) {
			return true;
		}
		return false;
	}
    @Override
	public Fecha getFechaVencimiento() {
		return this.fechaVencimiento;
	}


	@Override
	public String toString() {
		return " ConVencimiento [fechaVencimiento=" + fechaVencimiento +"\n";
	}

}
