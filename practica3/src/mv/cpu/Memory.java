package mv.cpu;

public class Memory<T> {
	private Object[] registros;
	public final int TAMANIO = 50;
	
	public Memory(){	
		//inicializo los atributos
		this.registros =  inicializarMemory(TAMANIO);
	}

	/**
	 * Metodo que inicializa la memoria a null con el tamaÃ±o deseado
	 * @param tamanio
	 * @return memoria inicializada
	 */
	private T[] inicializarMemory(int tam){
		
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
	public void storeData(int pos, int dato){		
		
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

	}
	
	/**
	 * Metodo que redimensiona la memoria a traves de una memoria auxiliar previamente inicializada
	 * @param aux
	 * @return memoria redimensionada
	 */
	private T[] redimensionar(Object[] aux){
		
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
	 */
	public T getDato (int pos){
		if (pos < this.registros.length) {
			if(this.registros[pos]!=null)
			        return (T)this.registros[pos];
			else return null;
		} else return null;
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
}
