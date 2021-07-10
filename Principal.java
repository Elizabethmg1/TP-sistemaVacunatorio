package centroVacunacion;

import java.util.ArrayList;

public class Principal {
	
	public static void imprimirTurnos(CentroVacunacion centro,Fecha fecha) {
		ArrayList<Integer> turnosAsignados=centro.turnosConFecha(fecha);
		ArrayList<Vacuna> vacunas=centro.infoVacunasAsignadas(fecha);
		for(int i=0; i<turnosAsignados.size(); i++) {
			System.out.println("dni: " + turnosAsignados.get(i) +" " + fecha + " vacuna asignada: "+ vacunas.get(i) );
		}
	}

	public static void main(String[] args) {
		Fecha.setFechaHoy(15,7,2021);
		Fecha fTurnos = new Fecha(15, 7, 2021);
		CentroVacunacion centro = new CentroVacunacion("UNGS", 5);

		System.out.println("\n------------ Creacion -------------\n");
		System.out.println(centro);
		System.out.println("\n-----------------------------------\n");
		System.out.println();

		centro.ingresarVacunas("Moderna", 5, new Fecha(15,5,2021));
		centro.ingresarVacunas("AstraZeneca", 5, new Fecha(15,5,2021));
		centro.ingresarVacunas("Pfizer", 5, new Fecha(15,5,2021));
		centro.ingresarVacunas("Sinopharm", 5, new Fecha(15,5,2021));

		centro.inscribirPersona(34701000, new Fecha(1, 5, 1989), false, false);
		centro.inscribirPersona(29959000, new Fecha(20, 11, 1982), false, true);
		centro.inscribirPersona(24780201, new Fecha(1, 6, 1972), true, false);
		centro.inscribirPersona(29223000, new Fecha(2, 5, 1982), false, true);
		centro.inscribirPersona(13000000, new Fecha(1, 5, 1958), true, false);
		centro.inscribirPersona(13000050, new Fecha(20, 6, 1958), false, true);
		centro.inscribirPersona(40860713, new Fecha(7,7,1996), false, false);
		centro.inscribirPersona(44611568, new Fecha(17,12,2002), false, false);

		
		System.out.println(centro.vacunasDisponibles("Moderna") + " vacunas Moderna disponibles");
		System.out.println(centro.vacunasDisponibles("AstraZeneca")+ " vacunas AstraZeneca disponibles");
		System.out.println(centro.vacunasDisponibles("Pfizer")+ " vacunas Pfizer disponibles");
		System.out.println(centro.vacunasDisponibles("Sinopharm")+ " vacunas Sinopharm disponibles");
		
		centro.generarTurnos(fTurnos);

		System.out.println("\n-------------- Turnos asignados: " + fTurnos + " -------------\n");
		imprimirTurnos(centro,fTurnos);
		
		Fecha fecha2=new Fecha(fTurnos.dia(),fTurnos.mes(),fTurnos.anio());
		fecha2.avanzarUnDia();
		System.out.println("\n----------------Turnos asignados: " + fecha2 + "-------------------\n");
		imprimirTurnos(centro,fecha2);
		
		centro.vacunarInscripto(24780201, fTurnos);
		centro.vacunarInscripto(13000000, fTurnos);
		centro.vacunarInscripto(40860713, fecha2);
		centro.vacunarInscripto(44611568, fecha2);
		
		System.out.println("\n------------- informacion hasta la " + fecha2 + "--------------\n");
		System.out.println("Personas que se vacunaron: "+ centro.reporteVacunacion());
		System.out.println("Vacunas que se vencieron: " +centro.reporteVacunasVencidas());
		System.out.println(centro.vacunasDisponibles("Moderna") + " vacunas Moderna disponibles");
		System.out.println(centro.vacunasDisponibles("AstraZeneca")+ " vacunas AstraZeneca disponibles");
		System.out.println(centro.vacunasDisponibles("Pfizer")+ " vacunas Pfizer disponibles");
		System.out.println(centro.vacunasDisponibles("Sinopharm")+ " vacunas Sinopharm disponibles");
		System.out.println("\n------------- Centro --------------\n");
		System.out.println(centro);
		System.out.println("-------------------------------------------------------------------------");
		fTurnos.avanzarDias(30);
		Fecha.setFechaHoy(fTurnos.dia(),fTurnos.mes(),fTurnos.anio());
		centro.generarTurnos(fTurnos);
		System.out.println("\n------------- informacion hasta la " + fTurnos + "--------------\n");
		System.out.println("Personas que se vacunaron: "+ centro.reporteVacunacion());
		System.out.println("Vacunas que se vencieron: " +centro.reporteVacunasVencidas());
		System.out.println(centro.vacunasDisponibles("Moderna") + " vacunas Moderna disponibles");
		System.out.println(centro.vacunasDisponibles("AstraZeneca")+ " vacunas AstraZeneca disponibles");
		System.out.println(centro.vacunasDisponibles("Pfizer")+ " vacunas Pfizer disponibles");
		System.out.println(centro.vacunasDisponibles("Sinopharm")+ " vacunas Sinopharm disponibles");
		System.out.println("\n------------- Centro --------------\n");
		System.out.println(centro);
		System.out.println("-----------------------------------");

	}
}
