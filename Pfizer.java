package centroVacunacion;

public class Pfizer extends ConVencimiento{
	private int gradosAlmacenamiento;
	private int edadPermitida;

	public Pfizer(Fecha fechaDeIngreso) {
		super("Pfizer", fechaDeIngreso);
		this.edadPermitida=60;
		this.gradosAlmacenamiento=-18;
	}
	
	@Override
	public int queTemperaturanecesitas() {
		return this.gradosAlmacenamiento;
	}
	@Override
	public int paraQueEdadSos() {
		return this.edadPermitida;
	}
	@Override
	public String toString() {
		return "Pfizer";
	}
	
	

}
