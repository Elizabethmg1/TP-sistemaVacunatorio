package CentroVacunacion;

import java.util.*;
import java.sql.Date;

public class conVencimiento extends Vacunas {
	private Date fechaVencimiento; //
	
	conVencimiento(String nombre, int Stock, Date fecha) {
		super(nombre, Stock,fecha);
		this.fechaVencimiento=vence(fecha);
	}
	
	private Date vence(Date fecha) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean estaVencida(Date fecha){
		return false;
	}

	
	
	

}
