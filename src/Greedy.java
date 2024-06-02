package tpe;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class Greedy {
	private Servicios servicios;
	private HashMap<String,Boolean> visitados;
	private LinkedList<Procesador> lista;
	private int tiempoFinal;
	public static final int MAXCRITICAS = 2;
	
	public Greedy(Servicios servicios){
		this.servicios = servicios;
		this.tiempoFinal = 0;
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
	
	private void rellenarAll(){
		for(Tarea t : servicios.getListaTareas()){
			visitados.put(t.getId(), false);
		}
	}
	
	public LinkedList<Procesador> asignarTareas(int tiempoMaximo){
		this.rellenarAll();
		int indice = 0;
		for(Procesador pp : servicios.getListaProcesadores()){
			asignarTareas(pp,indice,tiempoMaximo);
		}
		for (Procesador pp : this.servicios.getListaProcesadores()) {
               this.lista.add(pp.copiar());
         }
		tiempoFinal = mayorTiempo(lista);
		return lista;
	
	}
	
	private void asignarTareas(Procesador p,int indice, int tiempoMaximo){
		LinkedList<Tarea> lista = new LinkedList<Tarea>(servicios.getListaTareas());
		Collections.sort(lista);
			for(Tarea tt : lista){
				if(!visitados.get(tt.getId())){
					if(validarTarea(p,tt, tiempoMaximo)){
						p.agregarTareas(tt);
						visitados.put(tt.getId(), true);
					}
				}
			}
	}
	
	
	private boolean validarTarea(Procesador pp, Tarea tt, int tiempoMax) {
		if(pp.getCantidadCriticas() < MAXCRITICAS){
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
		return this.tiempoFinal;
	}

}
