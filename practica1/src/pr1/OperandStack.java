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
	
	private boolean isEmpty(){
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
	
	public void push (int value){
		//compruebo si la pila está llena
		if(this.cima < stack.length){
			this.cima++;
			//si no lo está, la relleno 
			stack[this.cima]=value;
			
		}else{
			
			//doblo el tamaño de la pila
			int nuevoTamanio = this.stack.length*2;
			
			this.stack = this.redimensionaPila(nuevoTamanio);
			
			this.cima++;
			//relleno el dato
			stack[this.cima]=value;
		}
	
	}
	
	private Integer[] redimensionaPila(int tamanio){
		//creo un auxiliar con el tamaño que me pasan
		Integer [] aux = new Integer[tamanio];
		
		//copio el contenido de la pila al axuliar
		for(int i=0;i<this.stack.length;i++){
			aux[i] = this.stack[i];
		}
		
		//devuelvo el auxiliar con el nuevo tamaño y el contenido copiado de la pila
		return aux;
	}
	
	
	public boolean pop (){
		boolean resultado;
		//si la pila no está vacía, vacío la posicion más alta, y bajo el nivel en una posicion 
		if (isEmpty()==false){
			stack[this.cima] = null;
			this.cima--;
			
			resultado =  true;
		}else{
			System.out.println("No");
			
			resultado = false;
		}
		
		return resultado;
	}
	
	public boolean dup(){
		boolean resultado;
		
		//si la pila no está vacía, duplico el valor de la cima de la pila 
		if (isEmpty()==false){
			this.cima++;
			stack[this.cima] = stack[this.cima - 1];
			
			resultado =  true;
		}else{
			System.out.println("Error: La pila está vacía.");
			resultado = false;
		}
		
		return resultado;
	}
	
	public Character out (){
		Character resultado;
		
		//si la pila está vacía devuelvo null
		if (isEmpty()==true){
			resultado = null;
		//si la pila no lo está devuelvo lo almacenado en la cima de la pila
		}else{
			int intValue = stack[this.cima].intValue();
			resultado 	 = (char)intValue; 
		}
		
		return resultado;
	}
	
	public String toString(){
		//devuelve el contenido de la pila
		String contenidoPila="";
		if(this.cima == -1){
			return "<vacia>";
		}
		else{
			for (int i=0; i<=this.cima; i++){
				contenidoPila += this.stack[i] +" ";
			}
			return "Pila : "+contenidoPila+"\n";
		}
	}
	
	public boolean flip () {
		int aux;
		
		if (this.stack.length < 2) return false;
		else {
			aux = this.stack[this.cima];
			this.stack[this.cima] = this.stack[this.cima - 1];
			this.stack[this.cima - 1] = aux;
			return true;
		}
	}
	
	public int getCima () {
		return this.cima;
	}
	
	public Integer getDato (int pos) {
		return this.stack[pos];
	}
}
