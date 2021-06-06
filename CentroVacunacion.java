package centroVacunacion;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CentroVacunacion {
	private String nombre;
	private int capacidadDiaria;
	private HashMap<Fecha,Turnos> fechaConTurnos;
	private HashSet<Persona> listaDeEspera;
	private Vacunatorio vacunatorio;

		public CentroVacunacion(String nombre,int capacidadDiaria) {
			if(capacidadDiaria<=0) {
				throw new RuntimeException("la capacidad ingresada debe ser un valor positivo");
			}
			else {
				this.nombre=nombre;
				this.capacidadDiaria=capacidadDiaria;
				HashMap<Fecha,Turnos> aux1=new HashMap<Fecha,Turnos>();
				this.fechaConTurnos=aux1;
				HashSet<Persona> aux2=new HashSet<Persona>();
				this.listaDeEspera=aux2;
				Vacunatorio aux3=new Vacunatorio();
				this.vacunatorio=aux3;
			}
	}
	
		public void ingresarVacunas(String nombre, int cantidad, Fecha fecha) {
			if(!nombre.equals("Sputnik") && !nombre.equals("Astrazeneca") && !nombre.equals("Pfizer") && !nombre.equals("Moderna") && !nombre.equals("Sinopharm")) {
				throw new RuntimeException("la vacuna ingresada no es valida");
			}
			if(cantidad<=0) {
				throw new RuntimeException("la cantidad no es valida");
			}
			this.vacunatorio.ingresanVacunas(nombre,cantidad,fecha);
	}
	
		public void inscribirPersona(Integer dni,Fecha fechaNac, boolean trabajadorEsencial, boolean enfermedadPreexistente) {
			Persona aux=new Persona(dni,fechaNac,trabajadorEsencial,enfermedadPreexistente);
			if(!this.listaDeEspera.contains(aux) && fechaNac.diferenciaAnios(Fecha.hoy(), fechaNac)>=18) {
				this.listaDeEspera.add(aux);
			}
		}
	
		public void generarTurnos(Fecha fechaInicial) {
//			Set<Fecha> fechas=this.fechaConTurnos.keySet();
//			for(Fecha f:fechas) {
//				if(f.anterior(Fecha.hoy())) {
//					Turnos turnos=this.fechaConTurnos.get(f);
//				}
//			}
			Set<Fecha> fechas=this.fechaConTurnos.keySet();
			
			
//			Turnos turnos=new Turnos(this.capacidadDiaria);
//			if (!this.fechaConTurnos.containsKey(fechaInicial)) {
//				this.fechaConTurnos.put(fechaInicial, turnos);
//			}
		}
		
		
		public HashSet<Integer> listaDeEspera(){
			HashSet<Integer> dnis=new HashSet<Integer>();
			for(Persona persona:this.listaDeEspera) {
				dnis.add(persona.darDni());
			}
			return dnis;
		}
		
		public int vacunasDisponibles() { //todas las vacunas
			this.vacunatorio.darCantidadDeVacunas();
		}
		
		public int vacunasDisponibles(String vacuna) {  //vacuna especifica
			this.vacunatorio.darCantidadDeVacunas(vacuna);
		}
		
		public Set<Persona> turnosConFecha(Fecha fecha){  //
			if(this.fechaConTurnos.containsKey(fecha)) {
				return this.fechaConTurnos.get(fecha).darListaPersona();
			}
		}

		//USO FOR EACH
		public HashMap<Integer,String> reporteVacunacion() {
			Set<Fecha> fechas=this.fechaConTurnos.keySet(); //hago un set de fechas
			HashMap<Integer,String> reporte=new HashMap<Integer,String>(); //hago un map reporte donde guardar los vacunados
			for(Fecha f:fechas) { //itero las fechas
				HashMap<Integer,String> reporte1=this.fechaConTurnos.get(f).darListaPersonasVacunadas(); //obtengo las personas que fueron vacunadas
				Set<Integer> dnis=reporte1.keySet();
				for(Integer i:dnis) { //itero las personas que fueron vacunadas
					reporte.put(i, reporte1.get(i)); //las agrego al reporte a devolver.
				}
			}
			return reporte;
	}
		
		public HashMap<String,Integer> reporteVacunasVencidas(){
			return null; //completar
		}
		
		public void vacunarInscripto(int dni, Fecha fecha) {
			//completaar
		}
}
