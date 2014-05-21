package mv.cpu;

import java.util.ArrayList;

import mv.exceptions.IncorrectMemoryPositionException;
import observers.MemoryObserver;
import observers.Observable;

public class Memory<T> implements Observable<MemoryObserver<T>>{
	private Object[] registros;
	public final int TAMANIO = 50;
	private ArrayList<MemoryObserver<T>> observers;
	
	public Memory(){	
		//inicializo los atributos
		this.observers	= new ArrayList<MemoryObserver<T>>();
		this.registros =  inicializarMemory(TAMANIO);
	}

	/**
	 * Metodo que inicializa la memoria a null con el tamaÃ±o deseado
	 * @param tamanio
	 * @return memoria inicializada
	 */
	@SuppressWarnings("all") private T[] inicializarMemory(int tam){
		
		//creo un auxiliar con el tamaÃ±o que me indican
		Object[] r = new Object[tam];
		
		//recorro ese array y lo voy inicializando
		for(int i=0; i < tam; i++){
			r[i] = null;
		}
		
		//devuelvo el array inicializado
		return (T[])r;
		
	}
	
	/**
	 * Metodo que almacena un dato en una posicion de memoria
	 * @param pos
	 * @param dato
	 */
	public void storeData(int pos, T dato){		
		
		//si la posicion donde deseas guardar es menor que la posicion de mayor tamaï¿½o que tengo lo guardo
		if(pos < this.registros.length){
			
			//almaceno el dato en la posiciï¿½n que me han indicado
			registros[pos] = dato;
			
		}else{			
			
			//creo nuevo tamaï¿½o con el doble de lo que tengo ahora
			int nuevo_tamanio = pos * 2;			
			
			//inicializo una memoria auxiliar que posteriormente pasarÃ¡ a ser la del sistema
			Object[] aux = this.inicializarMemory(nuevo_tamanio);
			
			//redimensiono el numero de tamaÃ±o de memoria
			this.registros = this.redimensionar(aux);
			
			//almaceno el dato en la posiciÃ³n que me han indicado
			this.registros[pos] = dato;
		}
		
		//aviso a los observers
		for(MemoryObserver<T> o: this.observers){
			o.onWrite(pos,dato);
		}

	}
	
	/**
	 * Metodo que redimensiona la memoria a traves de una memoria auxiliar previamente inicializada
	 * @param aux
	 * @return memoria redimensionada
	 */
	@SuppressWarnings("all") private T[] redimensionar(Object[] aux){
		
		//recorro todos los registros que tengo y los copio al auxiliar, el cual estÃ¡ previamente inicializado
		for(int i=0;i< this.registros.length;i++){
			aux[i] = (T) this.registros[i];
		}
	
		return (T[])aux;
	}
	
	/**
	 * Metodo que se encarga de determinar si la memoria esta vacia o no
	 * @return true/false
	 */
	private boolean isEmpty () {
		boolean empty = true;
		
		for(int i = 0; i <= (this.registros.length-1); i++){
			if(this.registros[i] != null){
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
	 * @throws IncorrectMemoryPositionException 
	 */
	@SuppressWarnings("all") public T getDato (int pos) throws IncorrectMemoryPositionException{
		T dato = null;
		if (pos < this.registros.length) {
			if(this.registros[pos]!=null)
			        dato = (T)this.registros[pos];
			else throw new IncorrectMemoryPositionException("Error: no hay ningún dato asignado a esa posición de memoria.\n");
		} else throw new IncorrectMemoryPositionException("Error: la posición de memoria que has solicitado está fuera de rango.\n");
		
		return dato;
	}
	
	/**
	 * Reinicia la memoria vaciando el array de registros.
	 */
	public void clean () {
		this.registros =  inicializarMemory(TAMANIO);
	}
	
	/**
	 * Metodo que devuelve el estado de la memoria en formato String
	 */
	public String toString (){
		String contenidoMemoria = "";
		if(this.isEmpty()) contenidoMemoria = "<vacia>";
		else{
			for(int i = 0; i <= (this.registros.length - 1); i++){
				if (this.registros[i] != null) contenidoMemoria += "["+ i + "]: " + this.registros[i] + ", ";
			}
		}
		
		return "Memoria: "+contenidoMemoria+"\n";
	}
	
	/**
	 * Devuelve la tabla que almacena la memoria.
	 * @return int[][]
	 */
	public int[][] getMemory(){
		int[][] memTable = new int[this.getMaxLength()][2];
		int cont = 0;
		if(!this.isEmpty()){
			for(int i=0; i<=(this.registros.length - 1);i++){
				if (this.registros[i] != null){
					memTable[cont][0] = i;
					memTable[cont][1] = (Integer)this.registros[i];
					cont++;
				}
			}
		}
		
		return memTable;
	}
	
	/**
	 * Devuelve la longitud máxima de la tabla de la memoria.
	 * @return int
	 */
	public int getMaxLength(){
		int maxRealLength = 0;
		for(int i = 0; i <= (this.registros.length - 1); i++){
			if (this.registros[i] != null) maxRealLength++;
		}
		return maxRealLength;
	}

	@Override
	public void addObserver(MemoryObserver<T> o) {
		observers.add(o);	
	}

	@Override
	public void removeObserver(MemoryObserver<T> o) {
		observers.remove(o);		
	}
}
