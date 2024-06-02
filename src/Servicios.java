package tpe;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


import tpe.utils.CSVReader;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {
	
	private LinkedList<Tarea> criticas;
	private LinkedList<Tarea> noCriticas;
	private LinkedList<Tarea> tareas;
	private LinkedList<Procesador> procesadores;
	private HashMap<String,Tarea> hashMap;
	private Tree arbol; 
	private HashMap<Integer,LinkedList<Tarea>> hashMapPrioridades; //integer de 1 a 100 (prioridad de la tarea)

	/*
     * Expresar la complejidad temporal del constructor.
     * la complejidad computacional del constructor es O(n) * O(h) donde n son los metodos que llama para rellenar las diferentes
     * estructuras (son 4 metodos, rellenarHashMap(),rellenarLista(),rellenarHashPrioridades() tienen complejidad computacion O(n)
     * y rellenarArbol() tiene complejidad computacional O(n) * O(h) en total seria O(n) * 4 * O(n) y nos termina quedando
     * O(n) * O(h)) y donde h es la altura del arbol que como maximo va a ser 100 ya que esta ordenado por prioridad
     */
	public Servicios(String pathProcesadores, String pathTareas)
	{
		CSVReader reader = new CSVReader();
		this.tareas = reader.readTasks(pathTareas);
		this.procesadores = reader.readProcessors(pathProcesadores);
		this.criticas = new LinkedList<>();
		this.noCriticas = new LinkedList<>();
		this.hashMap = new HashMap<>();
		this.arbol = new Tree();
		this.hashMapPrioridades = new HashMap<>();
		this.rellenarHashMap();
		this.rellenarLista();
		this.rellenarHashPrioridades();
		this.rellenarArbol();
		
	}
	
	public LinkedList<Tarea> getListaTareas(){
		return new LinkedList<Tarea>(this.tareas);
	}
	
	public LinkedList<Procesador> getListaProcesadores(){
		return new LinkedList<Procesador>(procesadores);
	}
	

	private void rellenarHashPrioridades(){
		for(int i = 1; i <= 100; i++){
			hashMapPrioridades.put(i, new LinkedList<Tarea>());
		}
	}
	
	
	private void rellenarHashMap(){
		for(int i = 0; i < tareas.size(); i++){
			this.hashMap.put(tareas.get(i).getId(), tareas.get(i));
		}
	}
	
	private void rellenarArbol(){
		for(int i = 0; i < tareas.size(); i++){
			this.arbol.add(tareas.get(i));
			hashMapPrioridades.get(tareas.get(i).getPrioridad()).add(tareas.get(i));
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
	 
	private LinkedList<Tarea> buscarMinMax(TreeNode actual, int prioridadInferior, int prioridadSuperior, LinkedList<Tarea> lista){
		if(actual == null){
			return lista;
		}
			if(actual.getValue().getPrioridad() > prioridadInferior && actual.getValue().getPrioridad() < prioridadSuperior){
				LinkedList<Tarea> tareas = hashMapPrioridades.get(actual.getValue().getPrioridad());
				lista.addAll(tareas);
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
     * La complejidad computacional de este servicio en el peor de los casos es O(n) * O(t) donde n son las prioridades que van de 1 a 100 
     * como maximo y donde t son las lista de factoreo asociada a cada prioridad, debido a que los valores maximo y minimo que nos
     * pasa el usuario podrian encontrarse entre la hoja mas izquierda y la hoja mas derecha del arbol, pero en promedio este servicio 
     * baja su complejidad a O(log n) * O(t), porque en muchas ocaciones no es necesario recorrer todo el arbol, ya que este corta antes
     * cuando encuentra un valor contemplado dentro del rango (de esta forma nos aseguramos que en el caso promedio hagamos menos accesos
     * a memoria, pero en el peor de los casos es como recorrer todas las tarea).
     */
	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
		if(this.arbol.getRaiz() == null){
			return new LinkedList<>();
		}
		return buscarMinMax(this.arbol.getRaiz(),prioridadInferior,prioridadSuperior, new LinkedList<Tarea>());
	}

}
