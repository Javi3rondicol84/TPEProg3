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
			if(actual.getValue().getPrioridad() > prioridadInferior && actual.getValue().getPrioridad() < prioridadSuperior){
				lista.add(actual.getValue());
				buscarMinMax(actual.getLeft(),prioridadInferior,prioridadSuperior,lista);
				buscarMinMax(actual.getRight(),prioridadInferior,prioridadSuperior,lista);
			}
			if(actual.getValue().getPrioridad() >= prioridadSuperior){
				buscarMinMax(actual.getLeft(),prioridadInferior,prioridadSuperior,lista);
			}
			if(actual.getValue().getPrioridad() <= prioridadInferior){
				buscarMinMax(actual.getRight(),prioridadInferior,prioridadSuperior,lista);
			}
			return lista;
	}
	
	
	
	/*
     * Expresar la complejidad temporal del servicio 1.
     * La complejidad computacional de este servicio es O(1), porque utilizamos la estructura de hashMap que contiene indices asociados 
     * como valor clave, en este caso un String (id de la tarea) y un puntero al objeto Tarea con todos sus atributos, de esta forma acceder 
     * a la tarea solicitada por el usuario a traves del id resulta en un tiempo constante. 
     */
	public Tarea servicio1(String ID) {
		return this.hashMap.get(ID);
	}
    
    /*
     * Expresar la complejidad temporal del servicio 2.
     * La complejidad computacional de este servicio tambien es O(1), porque utilizamos un metodo que nos rellena dos listas
     * (una con las tareas criticas y la otra con las tareas no criticas), asi cuando el ususario nos pasa true o false, directamente
     * le retornamos la lista previamente armada en el contructor (este llenado de lista se hace una unica vez en el constructor
     * mientras que al servicio lo solicitan muchas veces)
     */
	public List<Tarea> servicio2(boolean esCritica) {
		if(esCritica){
			return criticas;
		}
		return noCriticas;
	}

    /*
     * Expresar la complejidad temporal del servicio 3.
     * La complejidad computacional de este servicio en el peor de los casos es O(n), debido a que los valores maximo y minimo que nos
     * pasa el usuario podrian encontrarse entre la hoja mas izquierda y la hoja mas derecha del arbol, pero en promedio este servicio 
     * baja su complejidad a O(log n), porque en muchas ocaciones no es necesario recorrer todo el arbol, ya que este corta antes cuando 
     * encuentra un valor contemplado dentro del rango.
     */
	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
		if(this.arbol.getRaiz() == null){
			return new ArrayList<>();
		}
		return buscarMinMax(this.arbol.getRaiz(),prioridadInferior,prioridadSuperior,new ArrayList<Tarea>());
	}

}
