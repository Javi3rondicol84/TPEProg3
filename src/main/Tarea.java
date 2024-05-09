package main;

public class Tarea {
	private String id;
	private String nombre;
	private Integer tiempo;
	private Boolean critica;
	private Integer prioridad;
	
	public Tarea(String id, String nombre, Integer tiempo, Boolean critica,
			Integer prioridad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tiempo = tiempo;
		this.critica = critica;
		this.prioridad = prioridad;
	}
	
	@Override
	public String toString() {
		return "Id: " + this.getId() + " Nombre: " + this.getNombre() + "  Tiempo: " + this.getTiempo() + "  Critica: " + this.getCritica() + " Prioridad " + this.getPrioridad();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getTiempo() {
		return tiempo;
	}
	public void setTiempo(Integer tiempo) {
		this.tiempo = tiempo;
	}
	public Boolean getCritica() {
		return critica;
	}
	public void setCritica(Boolean critica) {
		this.critica = critica;
	}
	public Integer getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	} 
	
	
}
