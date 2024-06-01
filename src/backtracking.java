package tpe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class backtracking {
	private Servicios servicios;
	private LinkedList<Procesador> mejorSolucion;
	private int menorTiempo;
	private int mayorTiempo;
	private HashMap<String,Boolean> visitados;
	public static final int MAXCRITICAS = 2;
	
	
	public backtracking(Servicios servicios){
		this.servicios = servicios;
		this.mejorSolucion = new LinkedList<Procesador>();
		this.menorTiempo = 0;
		this.mayorTiempo = 0;
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
	
	public LinkedList<Procesador> asignarTareas(int tiempoMaximo){
		this.rellenarAll();
		int indice = 0;
		asignarTareas(new Tarea(), new Procesador(),indice,tiempoMaximo);
		return mejorSolucion;
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
	
	private void asignarTareas(Tarea t, Procesador p,int indice, int tiempoMaximo){
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
						pp.agregarTareas(tt);
						indice++;
						asignarTareas(tt, pp, indice, tiempoMaximo);
						pp.remove(tt);
						indice--;
					}
					visitados.put(id,false);
				}
			}
		}
	}
	
	
	private boolean validarTarea(Procesador pp, Tarea tt, int tiempoMax) {
		if(pp.getCantidadCriticas() < MAXCRITICAS){
			if(!pp.isRefrigerado()){
				if(pp.getTiempoTareas() < tiempoMax){
					return true;
				}else{
					return false;
				}
			}
			return true;
		}
			return false;
	}
}