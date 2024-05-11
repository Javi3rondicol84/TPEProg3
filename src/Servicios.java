package tpe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import tpe.utils.CSVReader;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {
	
	private LinkedList<Tarea> criticas;
	private LinkedList<Tarea> noCriticas;
	private ArrayList<Tarea> tareas;
	private HashMap<String,Tarea> hashMap;
	private Tree arbol; 

	/*
     * Expresar la complejidad temporal del constructor.
     */
	public Servicios(String pathProcesadores, String pathTareas)
	{
		CSVReader reader = new CSVReader();
		reader.readProcessors(pathProcesadores);
		this.tareas = reader.readTasks(pathTareas);
		this.criticas = new LinkedList<>();
		this.noCriticas = new LinkedList<>();
		this.hashMap = new HashMap<>();
		this.arbol = new Tree();
		this.rellenarHashMap();
		this.rellenarLista();
		this.rellenarArbol();
		
	}
	
	
	private void rellenarHashMap(){
		for(int i = 0; i < tareas.size(); i++){
			this.hashMap.put(tareas.get(i).getId(), tareas.get(i));
		}
	}
	
	private void rellenarArbol(){
		for(int i = 0; i < tareas.size(); i++){
			this.arbol.add(tareas.get(i));
		}
	}
	
	private void rellenarLista(){
		for(int i = 0; i < tareas.size(); i++){
			if(tareas.get(i).isCritica() == false){
				this.noCriticas.add(tareas.get(i));
			}else{
				this.criticas.add(tareas.get(i));
			}
		}
	}
	 
	private ArrayList<Tarea> buscarMinMax(TreeNode actual, int prioridadInferior, int prioridadSuperior, ArrayList<Tarea> lista){
		if(actual == null){
			return lista;
		}
			buscarMinMax(actual.getLeft(),prioridadInferior,prioridadSuperior,lista);
			buscarMinMax(actual.getRight(),prioridadInferior,prioridadSuperior,lista);
			if(actual.getValue().getPrioridad() > prioridadInferior && actual.getValue().getPrioridad() < prioridadSuperior){
				lista.add(actual.getValue());
			}
			return lista;
	}
	
	
	
	/*
     * Expresar la complejidad temporal del servicio 1.
     */
	public Tarea servicio1(String ID) {
		return this.hashMap.get(ID);
	}
    
    /*
     * Expresar la complejidad temporal del servicio 2.
     */
	public List<Tarea> servicio2(boolean esCritica) {
		if(esCritica){
			return criticas;
		}
		return noCriticas;
	}

    /*
     * Expresar la complejidad temporal del servicio 3.
     */
	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
		if(this.arbol.getRaiz() == null){
			return new ArrayList<>();
		}
		return buscarMinMax(this.arbol.getRaiz(),prioridadInferior,prioridadSuperior,new ArrayList<Tarea>());
	}

}
