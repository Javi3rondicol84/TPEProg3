package tpe;


import java.util.HashMap;
import java.util.LinkedList;

public class Backtracking {
	private Servicios servicios;
	private LinkedList<Procesador> mejorSolucion;
	private int menorTiempo;
	private int mayorTiempo;
	private HashMap<String,Boolean> visitados;
	private boolean haySolucion;
	private int cantTareas;
	private int cantEstados;
	public static final int MAXCRITICAS = 2;
	
	
	public Backtracking(Servicios servicios){
		this.servicios = servicios;
		this.mejorSolucion = new LinkedList<Procesador>();
		this.menorTiempo = 0;
		this.mayorTiempo = 0;
		this.haySolucion = false;
		this.cantEstados = 0;
		this.cantTareas = 0;
		this.visitados = new HashMap<>();
	}
	
	/*Asignación de tareas a procesadores. Se tienen m procesadores idénticos y n tareas con un tiempo
	de ejecución dado. Se requiere encontrar una asignación de tareas a procesadores de manera de
	minimizar el tiempo de ejecución del total de tareas.*/

	private void rellenarAll(){
		for(Tarea t : servicios.getListaTareas()){
			visitados.put(t.getId(), false);
		}
	}
	
	private int mayorTiempo(LinkedList<Procesador> procesadores){
		int sumaMayor = 0;
		int suma = 0;
		for(Procesador pp : procesadores){
			suma = pp.getSumas();
			if(suma > sumaMayor){
				sumaMayor = suma;
			}
		}
		return sumaMayor;
	}
	
	/* La estrategia de backtraking consiste en ir considerando una tarea por cada procesador (dentro de que se cumplan los 
	 * criterios de que esa tarea puede ser agregada en ese procesador) e ir llamando recursivamente por cada tarea para que
	 * vuelva a considerar nuevamente todas las opciones disponibles (no se considera la tarea agregada previamente) y para obtener 
	 * la solucion, llegamos a un estado final donde todas las tareas se asignaron, quedandonos asi el procesador con mayor carga 
	 * de esa solucion y comparandola con otro posible estado final, donde volvemos a comparar ambas cargas de los procesadores 
	 * y nos quedamos con el que tiene la menor carga.
	 */

	
	public LinkedList<Procesador> asignarTareas(int tiempoMaximo){
		this.rellenarAll();
		int indice = 0;
		asignarTareas(indice,tiempoMaximo);
		return mejorSolucion;
		
	}
	
	private void asignarTareas(int indice, int tiempoMaximo){
		cantEstados++;
		if(indice == servicios.getListaTareas().size()){
			mayorTiempo = mayorTiempo(servicios.getListaProcesadores());
			if(mayorTiempo < menorTiempo || menorTiempo == 0){
				menorTiempo = mayorTiempo;
				mejorSolucion.clear();
				for (Procesador pp : this.servicios.getListaProcesadores()) {
                    mejorSolucion.add(pp.copiar());
                }
			}
		}else{
			Tarea tt = servicios.getListaTareas().get(indice);
			for(Procesador pp : servicios.getListaProcesadores()){
				String id = tt.getId();
				if(!visitados.get(id)){
					visitados.put(id,true);
					if(validarTarea(pp,tt, tiempoMaximo)) {
						cantTareas++;
						pp.agregarTareas(tt);
						indice++;
						asignarTareas(indice, tiempoMaximo);
						pp.remove(tt);
						indice--;
						cantTareas--;
					}
					visitados.put(id,false);
				}
			}
		}
		if(cantTareas == servicios.getListaTareas().size()) {
			haySolucion = true;
		}
	}
	
	
	private boolean validarTarea(Procesador pp, Tarea tt, int tiempoMax) {
		if(pp.getCantidadCriticas() < MAXCRITICAS || !tt.isCritica()){
			if(!pp.isRefrigerado()){
				if(pp.getTiempoTareas() + tt.getTiempo() < tiempoMax){
					return true;
				}else{
					return false;
				}
			}
			return true;
		}
			return false;
	}
	
	public int getTiempoFinal(){
		return this.menorTiempo;
	}
	
	public int getCantEstados() {
		return cantEstados;
	} 
	
	
	public boolean haySolucion(){
		return haySolucion;
	}
}





