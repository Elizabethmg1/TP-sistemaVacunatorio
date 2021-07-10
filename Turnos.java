package centroVacunacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Turnos {
	private HashMap<Persona,Vacuna> personaVacuna; //turnos asignados aun no vacunados
	private HashMap<Integer,String> personasVacunadas; //personas ya vacunadas
	
	public Turnos() {
		HashMap<Persona,Vacuna> aux=new HashMap<Persona,Vacuna>();
		this.personaVacuna=aux;
		HashMap<Integer,String> aux1=new HashMap<Integer,String>();
		this.personasVacunadas=aux1;
	}
	
	public void agregarTurno(Persona persona, Vacuna vacuna) {
			this.personaVacuna.put(persona, vacuna);
	}
	
	public ArrayList<Integer> darListaPersona(){
		ArrayList<Integer> dnis=new ArrayList<Integer>();
		for(Persona p:this.personaVacuna.keySet()) {
			dnis.add(p.darDni());
		}
		return dnis;
	}
	
	public HashMap<Integer,String> darListaPersonasVacunadas() {
		return this.personasVacunadas;
	}
	
	public void sePresento(Integer dni) {
		Persona sePresenta = null;
		boolean tieneTurno=false;
		for(Persona p: this.personaVacuna.keySet()) {
			if(p.darDni()==dni) { //si el dni pasado por parametro coincide con alguien que tiene turno
				tieneTurno=true;
				this.personasVacunadas.put(p.darDni(), this.personaVacuna.get(p).getClass().getSimpleName()); //paso a la persona a vacunadas
				sePresenta=p; //pongo a la persona en una variable para eliminarla de turnos luego de terminar las iteraciones
			}
		}
		if(tieneTurno==true) { //si la persona tiene turno se la remueve
			this.personaVacuna.remove(sePresenta);
		}
	}
	
//Uso iterator	
	public ArrayList<Vacuna> devolverVacunas() {
		ArrayList<Vacuna> vacunasADevolver=new ArrayList<Vacuna>();
		Iterator<Persona> iterPersonas=this.personaVacuna.keySet().iterator();
		Persona persona;
		while(iterPersonas.hasNext()) {
			persona=iterPersonas.next();
			vacunasADevolver.add(this.personaVacuna.get(persona));
		}
		return vacunasADevolver;
	}
	
	public void eliminarTurnos() {
		this.personaVacuna.clear();
	}
	
	
}
