package centroVacunacion;


public class SinVencimiento  extends Vacuna{

	SinVencimiento(Fecha fechaDeIngreso) {
		super(fechaDeIngreso);
	}
	
	@Override
	public boolean vences() {
		return false;
	}
	
}
