package centroVacunacion;

public class Moderna extends ConVencimiento{
	private int gradosAlmacenamiento;
	private int edadPermitida;

	public Moderna( Fecha fechaDeIngreso) {
		super("Moderna",fechaDeIngreso);
		this.edadPermitida=18;
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
		return "Moderna";
	}
	
	
}
