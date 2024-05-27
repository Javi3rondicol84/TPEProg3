package tpe;

import java.util.ArrayList;
import java.util.LinkedList;

public class Procesador {
	private String id;
	private String codigo;
	private boolean refrigerado;
	private Integer anio;
	private LinkedList<Tarea> tareas;
	private Integer cantCriticas;
	private Integer tiempoTarea;
	public static final int MAXCRITICAS = 2;
	
	public Procesador(String id, String codigo, boolean refrigerado,Integer anio) {
		this.id = id;
		this.codigo = codigo;
		this.refrigerado = refrigerado;
		this.anio = anio;
		this.cantCriticas = 0;
		this.tiempoTarea = 0;
		this.tareas = new LinkedList<>();
	}
	
	public LinkedList<Tarea> getTareas() {
		return new LinkedList<Tarea>(this.tareas);
	}
	
	public Procesador(){};
	
	public int getSumas(){
		int suma = 0;
		for(int i = 0; i < tareas.size(); i++){
			suma = suma +tareas.get(i).getTiempo();
		}
		return suma;
	}
	
	public void agregarTareas(Tarea tt, Integer tiempoMax){
		if(tareas.contains(tt)){
			return;
		}
		if(tt.isCritica() == true){
			cantCriticas++;
		}
		if(cantCriticas < MAXCRITICAS){
			if(!this.refrigerado){
				if(tiempoTarea + tt.getTiempo() < tiempoMax){
					this.tiempoTarea += tt.getTiempo();
				}else{
					return;
				}
			}
			tareas.add(tt);
		}
	}
	
	public String toString(){
		return " id: "+ id + " codigo: " + codigo + " refrigerado: " + refrigerado + " año: " + anio + "\n";
	}
	
	/*public void traerTareas(){
		for(int i = 0; i < tareas.size(); i++){
			System.out.println(tareas.get(i).getId() + " " + tareas.get(i).getNombre() + " " + tareas.get(i).getPrioridad()
					+ " " + tareas.get(i).getTiempo() + " " + tareas.get(i).isCritica());
		}
	}*/


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public boolean isRefrigerado() {
		return refrigerado;
	}

	public void setRefrigerado(boolean refrigerado) {
		this.refrigerado = refrigerado;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	
	
}
