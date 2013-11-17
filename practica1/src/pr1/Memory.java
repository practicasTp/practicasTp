package pr1;

public class Memory {
	private Integer[] registros;
	public final int TAMANIO = 50;
	
	public Memory(){	
		//inicializo los atributos
		this.registros =  inicializarMemory(TAMANIO);
	}

	/**
	 * Metodo que inicializa la memoria a null con el tamaño deseado
	 * @param tamanio
	 * @return memoria inicializada
	 */
	private Integer[] inicializarMemory(int tam){
		
		//creo un auxiliar con el tamaño que me indican
		Integer[] r = new Integer[tam];
		
		//recorro ese array y lo voy inicializando
		for(int i=0; i < tam; i++){
			r[i] = null;
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
		
		//si la posicion donde deseas guardar es menor que la posicion de mayor tama�o que tengo lo guardo
		if(pos < this.registros.length){
			
			//almaceno el dato en la posici�n que me han indicado
			registros[pos] = dato;
			
		}else{
			int nuevo_tamanio = this.registros.length;
			
			//si no, mientras la posicion sea mayor que la posicion de mayor tama�o redimensiono mi tama�o de memoria
			do{
				//creo nuevo tama�o con el doble de lo que tengo ahora
				nuevo_tamanio = nuevo_tamanio * 2;
				
			}while(pos > nuevo_tamanio);
			
			//inicializo una memoria auxiliar que posteriormente pasar� a ser la del sistema
			Integer[] aux = this.inicializarMemory(nuevo_tamanio);
			
			//redimensiono el numero de tamaño de memoria
			this.registros = this.redimensionar(aux);
			
			//almaceno el dato en la posición que me han indicado
			this.registros[pos] = dato;
		}

	}
	
	/**
	 * Metodo que redimensiona la memoria a traves de una memoria auxiliar previamente inicializada
	 * @param aux
	 * @return memoria redimensionada
	 */
	private Integer[] redimensionar(Integer[] aux){
		
		//recorro todos los registros que tengo y los copio al auxiliar, el cual está previamente inicializado
		for(int i=0;i< this.registros.length;i++){
			aux[i] = this.registros[i];
		}
	
		return aux;
	}
	
	/**
	 * Metodo que se encarga de determinar si la memoria esta vacia o no
	 * @return true/false
	 */
	public boolean isEmpty () {
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
	public Integer getDato (int pos){
		if(this.registros[pos]!=null){
			return this.registros[pos];
		}else{
			return null;
		}
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
