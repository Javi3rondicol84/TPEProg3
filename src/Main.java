package tpe;

import java.util.LinkedList;



public class Main {
	
	public static void main(String args[]) {
		
		Servicios servicios = new Servicios("./src/tpe/datasets/Procesadores.csv", "./src/tpe/datasets/Tareas.csv");
		Backtracking back = new Backtracking(servicios);
		LinkedList<Procesador> procesadores = back.asignarTareas(100);
		System.out.println("Solucion obtenida con backtracking: " + "\n");
		if(back.haySolucion()) {
			for(Procesador pp : procesadores) {
				System.out.println("esRefrigerado: " + pp.isRefrigerado() + " " + pp.getId()  + ": " +  pp.getTareas());
			}
			System.out.println("\n" + "Tiempo final: " + back.getTiempoFinal() + "\n");
			System.out.println("Metrica: " + back.getCantEstados()+ "\n");
		}else {
			System.out.println("no hay solucion" + "\n");
		}
		Greedy g = new Greedy(servicios);
		LinkedList<Procesador> procesadores1 = g.asignarTareas(100);
		System.out.println("Solucion obtenida con greedy: " + "\n");
		if(g.haySolucion()) {
			for(Procesador pp : procesadores1) {
				System.out.println("esRefrigerado: " + pp.isRefrigerado() + " " + pp.getId()  + ": " +  pp.getTareas());
			}
			System.out.println("\n" + "Tiempo final: " + g.getTiempoFinal() + "\n");
			System.out.println("Metrica: " + g.getCantCandidatos() + "\n");

		}else {
			System.out.println("no hay solucion" + "\n");
			}
		}
}
