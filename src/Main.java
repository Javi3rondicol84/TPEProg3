package tpe;
import java.util.ArrayList;
import java.util.LinkedList;

import tpe.utils.CSVReader;

public class Main {

	public static void main(String args[]) {
		
		Servicios servicios = new Servicios("./src/tpe/datasets/Procesadores.csv", "./src/tpe/datasets/Tareas.csv");
		//System.out.println(servicios.servicio3(11,90));
		backtracking back = new backtracking(servicios);
		LinkedList<Procesador> pp = back.asignarTareas();
		for(int i = 0; i < pp.size(); i++){
			System.out.println(pp.get(i).getTareas());
		}
		
	}
}
