package centroVacunacion;

public class AstraZeneca extends SinVencimiento{
	private int gradosAlmacenamiento;
	private int edadPermitida;

	public AstraZeneca( Fecha fechaDeIngreso) {
		super( fechaDeIngreso);
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
		return "AstraZeneca" ;
	}
}
	