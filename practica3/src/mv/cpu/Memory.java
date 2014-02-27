package mv.cpu;

import java.util.ArrayList;

public class Memory {
	private ArrayList<Integer> registros;
	
	public Memory(){	
		//inicializo los atributos
		this.registros =  inicializarMemory();
	}

	/**
	 * Metodo que inicializa la memoria a null con el tamaño deseado
	 * @param tamanio
	 * @return memoria inicializada
	 */
	private ArrayList<Integer> inicializarMemory(){
		
		//creo un auxiliar con el tamaño que me indican
		ArrayList<Integer> r = new ArrayList<Integer>();
		
		//recorro ese array y lo voy inicializando
		for(int i=0; i < r.size(); i++){
			r.add(null);
		}
		
		//devuelvo el array inicializado
		return r;
		
	}
	
	/**
	 * Metodo que almacena un dato en una posicion de memoria
	 * @param pos
	 * @param dato
	 */
	public void storeData(int pos, int dato){		
		//almaceno el dato en la posici�n que me han indicado
		registros.set(pos, dato);
	}
	
	/**
	 * Metodo que se encarga de determinar si la memoria esta vacia o no
	 * @return true/false
	 */
	private boolean isEmpty () {
		boolean empty = true;
		
		for(int i = 0; i < this.registros.size(); i++){
			if(this.registros.get(i) != null){
				empty = false;
				break;
			}
		}
		
		return empty;
	}
	
	/**
	 * Metodo que devuelve el dato de una posicion deseada
	 * @param pos
	 * @return dato
	 */
	public int getDato (int pos){
		if (pos < this.registros.size()) {
			if(this.registros.get(pos)!=null)
			        return this.registros.get(pos);
			else return 0;
		} else return 0;
	}
	
	/**
	 * Reinicia la memoria vaciando el array de registros.
	 */
	public void clean () {
		this.registros.clear();
	}
	
	/**
	 * Metodo que devuelve el estado de la memoria en formato String
	 */
	public String toString (){
		String contenidoMemoria = "";
		if(this.isEmpty()) contenidoMemoria = "<vacia>";
		else{
			for(int i = 0; i <= this.registros.size(); i++){
				if (this.registros.get(i) != null) contenidoMemoria += "["+ i + "]: " + this.registros.get(i) + ", ";
			}
		}
		
		return "Memoria: "+contenidoMemoria+"\n";
	}
}
