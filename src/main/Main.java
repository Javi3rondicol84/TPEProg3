package main;

import java.util.ArrayList;
import java.util.List;


public class Main {

	public static void main(String args[]) {
		Servicios servicios = new Servicios("src/datasets/Procesadores.csv", "src/datasets/Tareas.csv");
		List<Tarea> tareas = servicios.servicio2(true);
		
		System.out.println(tareas);
	}
}
