package centroVacunacion;
import java.util.ArrayList;
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
			if(!nombre.equals("Sputnik") && !nombre.equals("AstraZeneca") && !nombre.equals("Pfizer") && !nombre.equals("Moderna") && !nombre.equals("Sinopharm")) {
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
			for(Fecha f:this.fechaConTurnos.keySet()) {
				if(f.anterior(Fecha.hoy())) { //verifico si la fecha en turnos es pasada
					ArrayList<Vacuna> vacunas=this.fechaConTurnos.get(f).devolverVacunas();
					for(Vacuna v:vacunas) {
						this.ingresarVacunas(v.getClass().getSimpleName(),1,v.fechaDeIngreso);//las vacunas vuelven a ingresar
					}
					this.fechaConTurnos.get(f).eliminarTurnos();//elimina del sistema a las personas que perdieron el turno
				}
			}
			this.vacunatorio.analizarVacunasVencidas();
		if(fechaInicial.anterior(Fecha.hoy())) { //antes de generar turno comprueba que no sea una fecha pasada
			throw new RuntimeException("La fecha ingresada no es valida");
		}
		else {
			//asignacion turnos
			if(this.vacunatorio.darCantidadDeVacunas()>0) { //si hay vacunas entonces se pueden otorgar turnos
				Fecha turnoActual=new Fecha(fechaInicial.dia(),fechaInicial.mes(),fechaInicial.anio()); //variable cambiante que controla en que fecha se asignan los turnos
				int noSeLesPuedeAsignarTurno=0;//va contando las personas que no se le puede asignar vacunas para su edad
				while(this.listaDeEspera.size()-noSeLesPuedeAsignarTurno>0 && (this.listaDeEspera.size()-noSeLesPuedeAsignarTurno)<=this.vacunasDisponibles()) { //mientras que la cantidad de personas sea menor que las vacunas disponibles
					Fecha nuevaFecha=new Fecha(turnoActual.dia(),turnoActual.mes(),turnoActual.anio());
					Turnos nuevosTurnos=new Turnos();
					int cupo=this.capacidadDiaria;
					ArrayList<Persona> ordenadas=new ArrayList<Persona>();//personas ordenadas por prioridad
					for(Persona p1:this.listaDeEspera) {
						if(p1.damePrioridad()==1){
							ordenadas.add(p1);
						}
					}
					for(Persona p2:this.listaDeEspera) {
						if(p2.damePrioridad()==2){
							ordenadas.add(p2);
						}
					}
					for(Persona p3:this.listaDeEspera) {
						if(p3.damePrioridad()==3){
							ordenadas.add(p3);
						}
					}
					for(Persona p4:this.listaDeEspera) {
						if(p4.damePrioridad()==4){
							ordenadas.add(p4);
						}
					}
				
					for(Persona p:ordenadas) {
						if(cupo>0 && this.vacunasDisponibles()>0) {
							int edad=p.darEdad();
							Vacuna asignada=this.vacunatorio.asignarVacuna(p);
							if(asignada!=null) {
								Persona personaAgregar=p;
								nuevosTurnos.agregarTurno(personaAgregar, asignada);
								this.listaDeEspera.remove(p);
								cupo--;
								}
							else {
								noSeLesPuedeAsignarTurno++;
								}
							}
						}
					this.fechaConTurnos.put(nuevaFecha, nuevosTurnos);
					turnoActual.avanzarUnDia(); //avanzo un dia en la fecha para que la proxima vez que se
					}							//asignen turnos sea el dia siguiente a los turnos asignados actual
				}
			}
	}
		
		public ArrayList<Integer> listaDeEspera(){
			ArrayList<Integer> dnis=new ArrayList<Integer>();
			for(Persona persona:this.listaDeEspera) {
				dnis.add(persona.darDni());
			}
			return dnis;
		}
		
		public int vacunasDisponibles() { //todas las vacunas
			return this.vacunatorio.darCantidadDeVacunas();
		}
		
		public int vacunasDisponibles(String vacuna) {  //vacuna especifica
			return this.vacunatorio.darCantidadDeVacunas(vacuna);
		}
		
		public ArrayList<Integer> turnosConFecha(Fecha fecha){ 
			ArrayList<Integer> lista=new ArrayList<Integer>();
			for(Fecha f:this.fechaConTurnos.keySet()) {
				if(f.equals(fecha)) {
					lista=this.fechaConTurnos.get(f).darListaPersona();
				}
			}
			return lista;
		}
		
		//metodo para facilitar la informacion sobre las vacunas que se asignaron al dar los turnos
		public ArrayList<Vacuna> infoVacunasAsignadas(Fecha fecha){
			return this.fechaConTurnos.get(fecha).devolverVacunas();
		}

		//USO FOR EACH
		public HashMap<Integer,String> reporteVacunacion() {
			HashMap<Integer,String> reporte=new HashMap<Integer,String>(); //hago un map reporte donde guardar los vacunados
			for(Fecha f:this.fechaConTurnos.keySet()) { //itero las fechas
				HashMap<Integer,String> reporte1=this.fechaConTurnos.get(f).darListaPersonasVacunadas(); //obtengo las personas que fueron vacunadas
				for(Integer dni:reporte1.keySet()) { //itero las personas que fueron vacunadas
					reporte.put(dni, reporte1.get(dni)); //las agrego al reporte a devolver.
				}
			}
			return reporte;
		}
		
		public int cantidadTurnosAsignados() {
			int turnosAsignados=0;
			for(Fecha f:this.fechaConTurnos.keySet()) {
				turnosAsignados=turnosAsignados + this.fechaConTurnos.get(f).darListaPersona().size();
			}
			return turnosAsignados;
		}
		
		public HashMap<String,Integer> reporteVacunasVencidas(){
			return this.vacunatorio.darVacunasVencidas();
		}	
		
		public void vacunarInscripto(int dni, Fecha fecha) {
			if(this.fechaConTurnos.containsKey(fecha) && this.fechaConTurnos.get(fecha).darListaPersona().contains(dni)) {
				this.fechaConTurnos.get(fecha).sePresento(dni);
			}
			else {
				throw new RuntimeException("La fecha no es valida, o la persona no tiene turno en esa fecha");
			}
		}
		//USO STRING BUILDER
		@Override
		public String toString() {
			StringBuilder sb=new StringBuilder();
			sb.append("CentroVacunacion [Nombre: ");
			sb.append(this.nombre);
			sb.append(", capacidad diaria=");
			sb.append(capacidadDiaria);
			sb.append(",\n Vacunas disponibles:");
			sb.append(this.vacunasDisponibles());
			sb.append(", personas en Lista de espera:");
			sb.append(this.listaDeEspera.size());
			sb.append(",\n vacunas aplicadas/personas vacunadas:");
			sb.append(this.reporteVacunacion().size());
			sb.append(", turnos asignados/personas con turnos sin vacunar: ");
			sb.append(this.cantidadTurnosAsignados());
			sb.append("]");
			return sb.toString();
		}
}
