package main;


import java.util.ArrayList;
import java.util.List;

import utils.CSVReader;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {
	private CSVReader reader = new CSVReader();
	private List<Tarea> tareas;
	private List<Procesador> procesadores;
	/*
     * Expresar la complejidad temporal del constructor.
     */
	public Servicios(String pathProcesadores, String pathTareas)
	{	
		this.procesadores = this.reader.readProcessors(pathProcesadores);
		this.tareas = this.reader.readTasks(pathTareas);
	}
	
	/*
     * Expresar la complejidad temporal del servicio 1.
     */
	public Tarea servicio1(String ID) {	
		
		for(int i = 0; i < this.tareas.size(); i++) {
			if(this.tareas.get(i).getId().equals(ID)) {
				return this.tareas.get(i);
			}
		}
		
		return null;
	}
    
    
     //* Expresar la complejidad temporal del servicio 2.
     
	public List<Tarea> servicio2(boolean esCritica) {
		ArrayList<Tarea> tareasEncontradas = new ArrayList<>();
		for(int i = 0; i < this.tareas.size(); i++) {
			if(this.tareas.get(i).getCritica() == esCritica) {
				tareasEncontradas.add(this.tareas.get(i));
			}
		}
		
		return tareasEncontradas;
     }

    
     
	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
		ArrayList<Tarea> tareasEncontradas = new ArrayList<>();
		for(int i = 0; i < this.tareas.size(); i++) {
			if(this.tareas.get(i).getPrioridad() > prioridadInferior && this.tareas.get(i).getPrioridad() < prioridadSuperior) {
				tareasEncontradas.add(this.tareas.get(i));
			}
		}
		
		return tareasEncontradas;
	}

}
