package CentroVacunacion;

import java.util.*;
import java.sql.Date;

public abstract class  Vacunas {
	private static int contador=0;
	private int id_Vacuna;
	
	private String nombre;
	private int stock;
	private Date fechaIngreso;
	
	private int edadPermitida;
	private int gradoAlmacenamiento;

 Vacunas(String nombre,int Stock, Date fecha){
	 this.nombre=nombre;
	 this.stock=Stock;
	 this.fechaIngreso=fecha;
	 
	 this.id_Vacuna= ++contador;
	 
	 this.edadPermitida=edadPermitida(this.nombre);
	 this.gradoAlmacenamiento=gradosAlmacenamiento(this.nombre);	 
 	 
 }
    private static int gradosAlmacenamiento(String nombre2) {
    	// Pfizer y Sputnik son para mayores de 60 
    	//y Sinopharm, Moderna y Astrazeneca
    	int grados=3;
    	String[] menos18= {"Pfizer", "Moderna"};
    	for (String m: menos18) {
			if (m.contains(nombre2)) {
				 grados=-18;
			}
			
       }
		return grados;
    }
    // Las vacunas Pfizer y Sputnik son para mayores de 60   
	private static int edadPermitida(String nombre2) {
		int permitido=18;
		String[] mayores= {"Pfizer", "Sputnik"};
		for (String m: mayores) {
			if (m.contains(nombre2)) {
				 permitido=60;
			}
			
       }
		return permitido;
}
 
	private void modificarStock(boolean vencida){
		if (vencida==true) {
			this.stock--;
		}
		this.stock++;
	}
	
	private	 int darStok(){
		
		return this.stock;
	}
	
	
	private	 int dameTemperatura(String nombre){
		return this.gradoAlmacenamiento;
	}
	
	
	private String darNombre() {//(int idVac) {
		return this.nombre;
	}

 
}
