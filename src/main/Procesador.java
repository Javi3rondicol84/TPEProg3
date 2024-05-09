package main;

public class Procesador {
	private String id;
	private String codigo;
	private Boolean refrigerado;
	private Integer anio;
	public Procesador(String id, String codigo, Boolean refrigerado,
			Integer anio) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.refrigerado = refrigerado;
		this.anio = anio;
	}
	
	@Override
	public String toString() {
		return this.getId() + " " + this.getCodigo() + " " + this.getRefrigerado() + " " + this.getAnio();
	}
	
	
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
	public Boolean getRefrigerado() {
		return refrigerado;
	}
	public void setRefrigerado(Boolean refrigerado) {
		this.refrigerado = refrigerado;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	
	
}
