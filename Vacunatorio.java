package centroVacunacion;

import java.util.HashMap;
import java.util.HashSet;

public class Vacunatorio {
	
	protected HashMap<Fecha,HashSet<Vacuna>> vacunas;
	protected HashMap<String,Integer> vacunasVencidas;
	
	public Vacunatorio() {
		HashMap<Fecha,HashSet<Vacuna>> vacunas1=new HashMap<Fecha,HashSet<Vacuna>>();
		HashMap<String,Integer> vacunas2=new HashMap<String,Integer>();
		this.vacunas=vacunas1;
		this.vacunasVencidas=vacunas2;
	}
	
	public void ingresanVacunas(String nombre, int cantidad, Fecha fechaDeIngreso) {
		switch(nombre) {
		case "Pfizer":
			for(int i=0; i<cantidad; i++) {
				Vacuna aux0=new Pfizer(fechaDeIngreso);
				if(this.vacunas.containsKey(fechaDeIngreso)) {
					this.vacunas.get(fechaDeIngreso).add(aux0);
				}
				else {
					HashSet<Vacuna> coleccion0=new HashSet<Vacuna>();
					coleccion0.add(aux0);
					this.vacunas.put(fechaDeIngreso,coleccion0 );
				}
			}
			break;
		case "Moderna":
			for(int i=0; i<cantidad; i++) {
				Vacuna aux1=new Moderna(fechaDeIngreso);
				if(this.vacunas.containsKey(fechaDeIngreso)) {
					this.vacunas.get(fechaDeIngreso).add(aux1);
				}
				else {
					HashSet<Vacuna> coleccion1=new HashSet<Vacuna>();
					coleccion1.add(aux1);
					this.vacunas.put(fechaDeIngreso,coleccion1 );
				}
			}
			break;
		case "Sinopharm":
			for(int i=0; i<cantidad; i++) {
				Vacuna aux2=new Sinopharm(fechaDeIngreso);
				if(this.vacunas.containsKey(fechaDeIngreso)) {
					this.vacunas.get(fechaDeIngreso).add(aux2);
				}
				else {
					HashSet<Vacuna> coleccion2=new HashSet<Vacuna>();
					coleccion2.add(aux2);
					this.vacunas.put(fechaDeIngreso,coleccion2 );
				}
			}
			break;
		case "AstraZeneca":
			for(int i=0; i<cantidad; i++) {
				Vacuna aux3=new AstraZeneca(fechaDeIngreso);
				if(this.vacunas.containsKey(fechaDeIngreso)) {
					this.vacunas.get(fechaDeIngreso).add(aux3);
				}
				else {
					HashSet<Vacuna> coleccion3=new HashSet<Vacuna>();
					coleccion3.add(aux3);
					this.vacunas.put(fechaDeIngreso,coleccion3 );
				}
			}
			break;
		case "Sputnik":
			for(int i=0; i<cantidad; i++) {
				Vacuna aux4=new Sputnik(fechaDeIngreso);
				if(this.vacunas.containsKey(fechaDeIngreso)) {
					this.vacunas.get(fechaDeIngreso).add(aux4);
				}
				else {
					HashSet<Vacuna> coleccion4=new HashSet<Vacuna>();
					coleccion4.add(aux4);
					this.vacunas.put(fechaDeIngreso,coleccion4 );
				}
			}
			break;
			}
		}
	
	public int darCantidadDeVacunas() {
		int cantidad=0;
		for(Fecha f:this.vacunas.keySet()) {
			for(Vacuna v:this.vacunas.get(f)) {
				cantidad ++;
			}
		}
		return cantidad;
	}
	
	public int darCantidadDeVacunas(String vacuna) {
		int cantidad=0;
		for(Fecha f:this.vacunas.keySet()) {
			for(Vacuna v:this.vacunas.get(f)) {
				if(v.getClass().getSimpleName().equals(vacuna)) {
					cantidad++;
				}
			}
		}
		return cantidad;
	}
	
	public void analizarVacunasVencidas() {
		HashMap<Fecha,HashSet<Vacuna>> estanVencidas=new HashMap<Fecha,HashSet<Vacuna>>();//aca se guardan las vacunasVencidas para luego eliminarlas, sin interrumpir la iteracion
		for(Fecha f:this.vacunas.keySet()) { //por fechas
			for(Vacuna v:this.vacunas.get(f)) { //por vacuna en el conjunto de vacunas de la Fecha
				if(v.vences()==true) {
					if(v.estaVencida()) { //si esta vencida la llevo a la lista de vacunas vencidas
						String nombreVacuna=v.getClass().getSimpleName(); //contengo el nombre de la vacuna
						if(this.vacunasVencidas.containsKey(nombreVacuna)) {//si la clave con el nombre de la vacuna ya esta en el diccionario
							int cantidad=this.vacunasVencidas.get(nombreVacuna);
							cantidad++; //solo sumo el valor de la cantidad
							this.vacunasVencidas.replace(nombreVacuna,this.vacunasVencidas.get(nombreVacuna), cantidad); //reemplazo el valor anterior por el nuevo
							this.vacunasVencidas.get(nombreVacuna);
						}
						else {
							this.vacunasVencidas.put(nombreVacuna, 1);//si no esta, agrego el nombre y cantidad
						}
						if(estanVencidas.containsKey(f)) {
							estanVencidas.get(f).add(v);
						}
						else {
							HashSet<Vacuna> vacunas=new HashSet<Vacuna>();
							vacunas.add(v);
							estanVencidas.put(f,vacunas); //la pongo en la lista de vacunas a sacar
						}
					}
				}
			}
		}
		//eliminar vacunas vencidas de la lista de Vacunas disponibles, iterando por estanVencidas
		for(Fecha f1:estanVencidas.keySet()) {
			for(Vacuna v1:estanVencidas.get(f1)) {
				this.vacunas.get(f1).remove(v1);
			}
		}
	}
	
	
	public Vacuna asignarVacuna(Persona persona) {
		for(Fecha f:this.vacunas.keySet()) {
			for(Vacuna v: this.vacunas.get(f)) {
				if(persona.darEdad()<60) {
					if(v.paraQueEdadSos()==18) {
						Vacuna aAsignar=v;
						this.vacunas.get(f).remove(v);
						return aAsignar;
					}
				}
				else if(60<=persona.darEdad()) {
					Vacuna aAsignar=v;
					this.vacunas.get(f).remove(v);
					return aAsignar;
				}
			}
		}
		return null;
	}
	
	public HashMap<String, Integer> darVacunasVencidas() {
		return this.vacunasVencidas;
	}
	
	public int dameTemperatura(Vacuna vacuna) {
		return vacuna.queTemperaturanecesitas();
	}

	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("Vacunatorio [vacunas=");
		sb.append(this.vacunas);
		sb.append(", vacunasVencidas=");
		sb.append(this.vacunasVencidas);
		sb.append("]");
		return sb.toString();
	}
	
	
}
