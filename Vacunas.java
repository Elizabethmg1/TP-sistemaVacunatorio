package CentroVacunacion;

import java.util.*;
import java.util.Map.Entry;

import java.sql.Date;

public  class  Vacunas {
	
	  /* IREP: las vacunas tienenun id unico
	 	vacunas = LISTA DE VACUNAS DEL DICCIONARIO NOTAR
	 	QUE NO ES NCESARIO DECIR QUE NO HAY VACUNASDUPLICADAS
	 	DAD0 QUE EL RESTO DEL IREP VIENE GARANTIZADO POR EL MAP
	 	
	  */
	private static int  Grados;
	
	private int sumaCantTotal;
	
	private static int contador=0;
	private int id_Vacuna;
	
	private String nombre;
	private int stock;
	private Fecha fechaIngreso;
	
	private int edadPermitida;
	private int gradoAlmacenamiento;
	
	private HashMap <String, Integer> _vacunas;//lista de vacunas
	private HashMap<String, ConVencimiento> conVenc;//= new HashSet <ConVencimiento>(
	//lista de vacunas vencidas
	private HashMap <String,SinVencimiento> sinVenc;//= new HashSet<SinVencimiento>();
	//lista de vacunas sin venciminto
	ArrayList<Vacunas> list;

	//private ArrayList <Vacunas> listaVacunas=new ArrayList<Vacunas>();;

	
Vacunas(){
		_vacunas=new HashMap<String,Integer>();
		conVenc= new HashMap <String,ConVencimiento>();
		sinVenc= new HashMap<String,SinVencimiento>();
		list= new ArrayList<Vacunas>();
		 	 }
	
Vacunas(String nombre,int Stock, Fecha fecha){
	 
	 this.nombre=nombre;
	 this.stock=Stock;
	 this.fechaIngreso=fecha;
	 
	 this.id_Vacuna= ++contador;
	 
	 this.edadPermitida=edadPermitida(this.nombre);
	 this.gradoAlmacenamiento=gradosAlmacenamiento(this.nombre);	 
 	
	 this.sumaCantTotal=this.sumaCantTotal+Stock; //suma el Stoc total de todas las vac;
	 

	 
	 
	 if (tieneVencimiento(nombre)) {
		 conVenc.put(nombre,new ConVencimiento(nombre,Stock,fecha));
	     System.out.println(tieneVencimiento(nombre));}
	 else 
		 sinVenc.put(nombre,new SinVencimiento(nombre,Stock,fecha));
		 
 }
public int dameTemperatura(String nombre){
		int tem=0;
		Iterator <String> it = conVenc.keySet().iterator();
		while (it.next() != null) {
			String clave=it.next();
		   tem=	conVenc.get(clave).getGradoAlmacenamiento();
		}
		return tem;
	}
	
public  void modificarStock(String nombre){
		if (_vacunas.containsKey(nombre)) 
				_vacunas.getOrDefault(nombre ,modificarStockVacunasVencidas(nombre)-1);	
			this.stock--;
		_vacunas.getOrDefault(nombre , modificarStockVacunasVencidas(nombre)+1);
	}
	
public HashMap<String, Integer> getVacunas() {
		return _vacunas;
	}
	
public  String darNombre() {//(int idVac) {
		return this.nombre;
	}

public int darCantidadVacunasTotal(String elemVac) {
		Iterator<String> it = _vacunas.keySet().iterator();
		int valor=0;
		while(it.hasNext()){
		    String clave = it.next();
		    return _vacunas.get(clave).intValue();
		}
		//throw new RuntimeException("no existe dicho vacuna con ese nombre");
	return valor;
	}
		
public void setGradoAlmacenamiento(int gradoAlmacenamiento) {
		this.gradoAlmacenamiento = gradoAlmacenamiento;
	}

public int EsEdadPermitida(String nombre2) {
		if (tieneVencimiento(nombre2))
			return _vacunas.get(nombre2);
		
		return 0;
	}

public HashMap<String,Integer>  vacunasVencidas() {
		HashMap<String,Integer> vacVen= new HashMap<String,Integer>();
		Iterator <String> it = conVenc.keySet().iterator();
		while (it.next() != null) {
			String clave=it.next();
		   vacVen.put(clave, conVenc.get(clave).getStock());
		}
		return vacVen;
	}

public int darCantidadVacunasTotal() {
		Iterator<String> it = _vacunas.keySet().iterator();
		int valor=0;
		while(it.hasNext()){
		    String clave = it.next();
		    valor=valor+ _vacunas.get(clave).intValue();
		}
		return valor;
	}

	/***********************************************************************/
	/******operaciones auxiliares*******************************************/

public  boolean tieneVencimiento(String nombre2) {
			return (nombre2=="Pfizer"||nombre2=="Moderna");
		}

private static int gradosAlmacenamiento(String nombre2) {
	    	// Pfizer y Sputnik son para mayores de 60 
	    	//y Sinopharm, Moderna y Astrazeneca
	        String[] menos18= {"Pfizer", "Moderna"};
	    	for (String m: menos18) {
				if (m.contains(nombre2))
					Grados= -18;
				else
					Grados= -3;
		
	       }
	    	return Grados;
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

private int modificarStockVacunasVencidas(String nombre2) {
			if (_vacunas.containsKey(nombre2))
				_vacunas.put(nombre,_vacunas.getOrDefault(nombre2, 0)-1);
			return _vacunas.get(nombre2);
}
/****************************************/		
//lista de vencidas y sin vencer

public void listaVencidas() {
	Iterator<String> it = conVenc.keySet().iterator();
	while(it.hasNext()){
		String clave = it.next();
		System.out.println("Nombre: "+ clave +" Stock: "+ conVenc.get(clave));
	}
	
}
public void listaSinVencer() {
	Iterator<String> it = sinVenc.keySet().iterator();
	while(it.hasNext()){
		String clave = it.next();
		System.out.println("Nombre: "+ clave +" Stock: "+ sinVenc.get(clave));
	}
}
//Lista de Vacunas
public void listaeVacunas(){
	Iterator<String> it = _vacunas.keySet().iterator();
	while(it.hasNext()){
		String clave = it.next();
		System.out.println("Nombre: "+ clave +" Stock: "+ _vacunas.get(clave));
	}
	
}
	/**
	 * @return the grados
	 */
	public static int getGrados() {
		return Grados;
	}

	/**
	 * @return the sumaCantTotal
	 */
	public int getSumaCantTotal() {
		return sumaCantTotal;
	}

	/**
	 * @return the contador
	 */
	public static int getContador() {
		return contador;
	}

	/**
	 * @return the id_Vacuna
	 */
	public int getId_Vacuna() {
		return id_Vacuna;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return the stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * @return the fechaIngreso
	 */
	public Fecha getFechaIngreso() {
		return fechaIngreso;
	}

	/**
	 * @return the edadPermitida
	 */
	public int getEdadPermitida() {
		return edadPermitida;
	}

	/**
	 * @return the gradoAlmacenamiento
	 */
	public int getGradoAlmacenamiento() {
		return gradoAlmacenamiento;
	}

	/**
	 * @return the _vacunas
	 */
	public HashMap<String, Integer> get_vacunas() {
		return _vacunas;
	}

	/**
	 * @return the conVenc
	 */
	public HashMap<String, ConVencimiento> getConVenc() {
		return conVenc;
	}

	/**
	 * @return the sinVenc
	 */
	public HashMap<String, SinVencimiento> getSinVenc() {
		return sinVenc;
	}
	public <Objeto> ArrayList<Objeto> get_List() {
		return (ArrayList<Objeto>) list;
	}



	@Override
	public String toString() {
		return "Vacunas [sumaCantTotal=" + sumaCantTotal + ", id_Vacuna=" + id_Vacuna + ", nombre=" + nombre
				+ ", stock=" + stock + ", fechaIngreso=" + fechaIngreso + ", edadPermitida=" + edadPermitida
				+ ", gradoAlmacenamiento=" + gradoAlmacenamiento + ", _vacunas=" + _vacunas + ", conVenc=" + conVenc
				+ ", sinVenc=" + sinVenc + ", list=" + list + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_vacunas == null) ? 0 : _vacunas.hashCode());
		result = prime * result + ((conVenc == null) ? 0 : conVenc.hashCode());
		result = prime * result + edadPermitida;
		result = prime * result + ((fechaIngreso == null) ? 0 : fechaIngreso.hashCode());
		result = prime * result + gradoAlmacenamiento;
		result = prime * result + id_Vacuna;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((sinVenc == null) ? 0 : sinVenc.hashCode());
		result = prime * result + stock;
		result = prime * result + sumaCantTotal;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vacunas other = (Vacunas) obj;
		if (_vacunas == null) {
			if (other._vacunas != null)
				return false;
		} else if (!_vacunas.equals(other._vacunas))
			return false;
		if (conVenc == null) {
			if (other.conVenc != null)
				return false;
		} else if (!conVenc.equals(other.conVenc))
			return false;
		if (edadPermitida != other.edadPermitida)
			return false;
		if (fechaIngreso == null) {
			if (other.fechaIngreso != null)
				return false;
		} else if (!fechaIngreso.equals(other.fechaIngreso))
			return false;
		if (gradoAlmacenamiento != other.gradoAlmacenamiento)
			return false;
		if (id_Vacuna != other.id_Vacuna)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (sinVenc == null) {
			if (other.sinVenc != null)
				return false;
		} else if (!sinVenc.equals(other.sinVenc))
			return false;
		if (stock != other.stock)
			return false;
		if (sumaCantTotal != other.sumaCantTotal)
			return false;
		return true;
	}

	
/**************************************************/




	
	
	
}
