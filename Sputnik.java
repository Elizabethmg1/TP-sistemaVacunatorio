package centroVacunacion;

public class Sputnik extends SinVencimiento{
	private int gradosAlmacenamiento;
	private int edadPermitida;

	public Sputnik(Fecha fechaDeIngreso) {
		super(fechaDeIngreso);
		this.edadPermitida=60;
		this.gradosAlmacenamiento=3;
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
		return "Sputnik";
	}
	
	

}
