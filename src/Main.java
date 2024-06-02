package tpe;

import java.util.LinkedList;



public class Main {

	public static void main(String args[]) {
		
		Servicios servicios = new Servicios("./src/tpe/datasets/Procesadores.csv", "./src/tpe/datasets/Tareas.csv");
		//System.out.println(servicios.servicio3(11,90));
		/*Backtracking back = new Backtracking(servicios);
		LinkedList<Procesador> procesadores = back.asignarTareas(0);
		System.out.println("Solucion obtenida con backtracking: ");
		System.out.println("Tiempo final: " + back.getTiempoFinal());
		for(Procesador pp : procesadores) {
			System.out.println("esRefrigerado: " + pp.isRefrigerado() + " " + pp.getId()  + ": " +  pp.getTareas());
		}*/
		Greedy g = new Greedy(servicios);
		LinkedList<Procesador> procesadores1 = g.asignarTareas(50);
		System.out.println("Solucion obtenida con greedy: ");
		System.out.println("Tiempo final: " + g.getTiempoFinal());
		for(Procesador pp : procesadores1) {
			System.out.println("esRefrigerado: " + pp.isRefrigerado() + " " + pp.getId()  + ": " +  pp.getTareas());
		}
	}
}
