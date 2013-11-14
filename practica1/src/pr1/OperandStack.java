package pr1;

public class OperandStack {
	private int cima;
	private Integer [] stack;
	public final int TAMANIO = 2;

	public OperandStack(){
		this.stack = this.inicializaPila(TAMANIO);
		this.cima = -1;
	}

	private Integer[] inicializaPila(int tamanio){
		
		//creo un auxiliar con el tamaño que me indican
		Integer [] r = new Integer[tamanio];
		
		//recorro ese array y lo voy inicializando
		for(int i=0; i < tamanio; i++){
			r[i] = null;
		}
		
		//devuelvo el array inicializado
		return r;
		
	}
	
	public boolean isEmpty(){
		boolean resultado;
		
		//si la cima es menos 1, considero que está vacía
		if( this.cima == -1){
			resultado = true;
		}else{
			//si no, entonces tiene valores
			resultado = false;
		}
		
		return resultado;
	}
	
	public void stackData (int value){
		//compruebo si la pila est� llena
		if(this.cima < (stack.length - 1)){
			this.cima++;
			//si no lo est�, la relleno 
			stack[this.cima]=value;
					
		}else{
			//doblo el tama�o de la pila
			int nuevoTamanio = this.stack.length*2;
			this.stack = this.redimensionaPila(nuevoTamanio);
			this.cima++;
			//relleno el dato
			stack[this.cima]=value;
		}
	}
	
	public boolean unstackData (){
		//si la pila no est� vac�a, vac�o la posicion m�s alta, y bajo el nivel en una posici�n 
		if (!isEmpty()){
			stack[this.cima] = null;
			this.cima--;
					
			return true;
		} else return false;
	}
	
	private Integer[] redimensionaPila(int tamanio){
		//creo un auxiliar con el tamaño que me pasan
		Integer [] aux = new Integer[tamanio];
		
		//copio el contenido de la pila al axuliar
		for(int i=0;i< this.stack.length;i++){
			aux[i] = this.stack[i];
		}
		
		//devuelvo el auxiliar con el nuevo tamaño y el contenido copiado de la pila
		return aux;
	}
	
	public String toString(){
		//devuelve el contenido de la pila
		String contenidoPila="";
		if(this.cima == -1){
			return "Pila de operandos: <vacia>\n";
		}
		else{
			for (int i=0; i<=this.cima; i++){
				contenidoPila += this.stack[i] +" ";
			}
			return "Pila de operandos: "+contenidoPila+"\n";
		}
	}
	
	public int getCima () {
		return this.cima;
	}
	
	public Integer getDato (int pos) {
		if(!this.isEmpty()){
			return this.stack[pos];
		}else{
			return null;
		}
	}
}
