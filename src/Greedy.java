package tpe;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class Greedy {
	private Servicios servicios;
	private HashMap<String,Boolean> visitados;
	private LinkedList<Procesador> lista;
	private int tiempoFinal;
	private boolean haySolucion;
	private int cantTareas;
	private int cantCandidatos;
	public static final int MAXCRITICAS = 2;
	
	public Greedy(Servicios servicios){
		this.servicios = servicios;
		this.tiempoFinal = 0;
		this.haySolucion = false;
		this.cantTareas = 0;
		this.cantCandidatos = 0;
		this.visitados = new HashMap<>();
		this.lista = new LinkedList<>();
	}
	
	private int mayorTiempo(LinkedList<Procesador> lista){
		int sumaMayor = 0;
		int suma = 0;
		for(Procesador pp : lista){
			suma = pp.getSumas();
			if(suma > sumaMayor){
				sumaMayor = suma;
			}
		}
		return sumaMayor;
	}
	
	public boolean haySolucion(){
		return haySolucion;
	}
	
	private void rellenarAll(){
		for(Tarea t : servicios.getListaTareas()){
			visitados.put(t.getId(), false);
		}
	}
	
	/* La estrategia utilizada para greedy fue ordenar las tareas por el tiempo de la tarea y luego por cada
	 * procesador irnos quedando con el que menor carga tiene hasta el momento (la suma del tiempo de las tareas que tiene hasta el
	 * momento) y que cumpla con los criterios. finalmente cuando el procesador cumple con los criterios, 
	 * añadimos la tarea con mayor tiempo. Ordenamos descendentemente las tareas,para distribuir primero las de mayor tiempo y luego
	 * poder balancear de mejor manera los procesadores con las de menor tiempo, de lo contrario el ordenamiento de forma ascendente
	 * de las tareas distribuye primero las tareas de menor tiempo y esto genera un desbalanceo al añadir las de mayor tiempo al final
	 * 
	 */
	
	public LinkedList<Procesador> asignarTareas(int tiempoMaximo){
		this.rellenarAll();
		int indice = 0;
		asignarTareas(indice,tiempoMaximo);
		for (Procesador pp : this.servicios.getListaProcesadores()) {
               this.lista.add(pp.copiar());
         }
		tiempoFinal = mayorTiempo(lista);
		if(cantTareas == servicios.getListaTareas().size()) {
			haySolucion = true;
		}
		return lista;
	
	}
	
	private void asignarTareas(int indice, int tiempoMaximo){
		LinkedList<Tarea> lista = new LinkedList<Tarea>(servicios.getListaTareas());
		Collections.sort(lista); 
		Collections.reverse(lista);
			for(Tarea tt : lista){
				if(!visitados.get(tt.getId())){
					Procesador p = validarTarea(tt, tiempoMaximo);
					if(p != null){
						cantTareas++;
						p.agregarTareas(tt);
						visitados.put(tt.getId(), true);
					}
				}
			}
	}
	
	
	private Procesador validarTarea(Tarea tt, int tiempoMax) {
		int tiempo = 0;
		int tiempoMenor = 0;
		Procesador pcMenorCarga = null;
		for(Procesador pc : servicios.getListaProcesadores()) {
			tiempo = tt.getTiempo() + pc.getSumas();
			cantCandidatos++;
			if(tiempo < tiempoMenor || tiempoMenor == 0) {
				if(pc.getCantidadCriticas() < MAXCRITICAS || !tt.isCritica()){
					if(!pc.isRefrigerado()){
						if(pc.getTiempoTareas() + tt.getTiempo() < tiempoMax){
							tiempoMenor = tiempo;
							pcMenorCarga = pc;
						}
					}else {
						tiempoMenor = tiempo;
						pcMenorCarga = pc;	
					}
				}
			}
		}
			return pcMenorCarga;
		
	}
	
	public int getTiempoFinal(){
		return this.tiempoFinal;
	}
	
	public int getCantCandidatos() {
		return cantCandidatos;
	}
}
