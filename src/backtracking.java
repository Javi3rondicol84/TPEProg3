package tpe;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class backtracking {
	private Servicios servicios;
	private HashMap<String,Boolean> visitado;
	private int sumaMenor;
	private int sumaMayor;
	
	
	public backtracking(Servicios servicios){
		this.servicios = servicios;
		this.sumaMenor = 0;
		this.sumaMayor = 0;
		this.visitado = new HashMap<String,Boolean>();
		rellenarHashMapTareas();
	}
	
	private void rellenarHashMapTareas(){
		 LinkedList<Tarea> tareas = servicios.getListaTareas();
		 for(int i = 0; i < tareas.size(); i++){
			 visitado.put(tareas.get(i).getId(), false);
		 }
	}
	
	public LinkedList<Procesador> asignarTareas(){
		 LinkedList<Procesador> procesadores = servicios.getListaProcesadores();
		 LinkedList<Tarea> tareas = servicios.getListaTareas();
		 int suma = 0;
		 Iterator<Procesador> it = procesadores.iterator();
		 while(it.hasNext()){
			 Procesador pp = it.next();
			 asignarTareas(tareas,suma,pp,new Tarea());
		 }
		 return procesadores;
	}
	
	private void asignarTareas(LinkedList<Tarea> tareas,int suma,Procesador pa,Tarea tt){
		Iterator<Tarea> it = tareas.iterator();
		visitado.put(tt.getId(),true);
		while(it.hasNext()){
			Tarea tt1 = it.next();
			if(!visitado.get(tt1.getId())){
				pa.agregarTareas(tt1,10);
				asignarTareas(tareas,suma,pa,tt1);
			}
		}
		//visitado.put(tt.getId(),false);
	}
	
	
	/*private void asignarTareas(LinkedList<Tarea> tareas, LinkedList<Procesador> procesadores,boolean x,int suma){
		Iterator<Tarea> itt = tareas.iterator();
		Iterator<Procesador> itp = procesadores.iterator();
		if(sumaMayor < sumaMenor || sumaMenor == 0){
			sumaMenor = sumaMayor;
			//obtuvimos una mejor solucion
		}
			while(itp.hasNext()){
				Procesador pp = itp.next();
				suma = getSumaProc(pp);
				if(suma > sumaMayor){
					sumaMayor = suma;
				}
				while(itt.hasNext() && !x){
					Tarea tt = itt.next();
					if(!visitado.get(tt.getId())){
						visitado.put(tt.getId(),true);
						if(pp.getTareas().size() < 3){
							pp.agregarTareas(tt);
							//hacer dfs o arbol de estados
							asignarTareas(tareas,procesadores,x,suma);
						}else{
							x = true;
							visitado.put(tt.getId(),false);
						}
			//			suma = suma - tt.getTiempo();
					}
				}
				x = false;
		}
	}*/
	
	private int getSumaProc(Procesador pp){
		return pp.getSumas();
	}
}
