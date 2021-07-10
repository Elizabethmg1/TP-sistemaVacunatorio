package centroVacunacion;

public class Sinopharm extends SinVencimiento{
	private int gradosAlmacenamiento;
	private int edadPermitida;

	public Sinopharm( Fecha fechaDeIngreso) {
		super(fechaDeIngreso);
		this.edadPermitida=18;
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
		return "Sinopharm";
	}
	
	

}
