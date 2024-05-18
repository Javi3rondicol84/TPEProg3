package tpe;
import java.util.ArrayList;

import tpe.utils.CSVReader;

public class Main {

	public static void main(String args[]) {
		
		Servicios servicios = new Servicios("./src/tpe/datasets/Procesadores.csv", "./src/tpe/datasets/Tareas.csv");
		System.out.println(servicios.servicio3(11,90));
	}
}
