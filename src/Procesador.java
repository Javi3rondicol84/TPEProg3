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
	private Integer tiempoTareas;

	
	public Procesador(String id, String codigo, boolean refrigerado,Integer anio) {
		this.id = id;
		this.codigo = codigo;
		this.refrigerado = refrigerado;
		this.anio = anio;
		this.cantCriticas = 0;
		this.tiempoTareas = 0;
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
	
	public Procesador copiar() {
        Procesador copia = new Procesador(this.getId() , this.getCodigo(), this.isRefrigerado(), this.anio);
        for (Tarea t : this.tareas) {
            copia.agregarTareas(t);
        }
        return copia;
    }
	
	public int getCantidadCriticas() {
		return this.cantCriticas;
	}
	
	public int getTiempoTareas() {
		return this.tiempoTareas;
	}
	
	public void agregarTareas(Tarea tt){
		if(!tareas.contains(tt)){
			tareas.add(tt);
			if(tt.isCritica() == true) {
				cantCriticas++;
			}
			tiempoTareas += tt.getTiempo();
		}
	}
	
	public String toString(){
		return " id: "+ id + " codigo: " + codigo + " refrigerado: " + refrigerado + " a�o: " + anio + "\n";
	}
	
	public void remove(Tarea tt){
		tareas.remove(tt);
		if(tt.isCritica() == true) {
			cantCriticas--;
		}
		tiempoTareas -= tt.getTiempo();
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
