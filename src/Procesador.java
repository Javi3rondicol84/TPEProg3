package tpe;

import java.util.ArrayList;

public class Procesador {
	private String id;
	private String codigo;
	private boolean refrigerado;
	private Integer anio;
	//private ArrayList<Tarea> tareas;
	
	public Procesador(String id, String codigo, boolean refrigerado,Integer anio) {
		this.id = id;
		this.codigo = codigo;
		this.refrigerado = refrigerado;
		this.anio = anio;
		//this.tareas = new ArrayList<>();
	}
	
	public Procesador() {
		//this.tareas = new ArrayList<>();
	}
	
	/*public void agregarTareas(Tarea tt){
		if(!tareas.contains(tt)){
			tareas.add(tt);
		}
	}*/
	
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
