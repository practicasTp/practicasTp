package mv.cpu;

import java.util.ArrayList;

public class OperandStack {
	private int cima;
	private ArrayList<Integer> stack;

	public OperandStack(){
		this.stack = this.inicializaPila();
		this.cima = -1;
	}

	/**
	 * Metodo que inicializa la pila a null con el tamaño deseado
	 * @param tamanio
	 * @return pila inicializada
	 */
	private ArrayList<Integer> inicializaPila(){
		
		ArrayList<Integer> r = new ArrayList<Integer>();
		
		//recorro ese array y lo voy inicializando
		for(int i=0; i < r.size(); i++){
			r.add(null);
		}
		
		//devuelvo el array inicializado
		return r;
		
	}
	
	/**
	 * Metodo que determina si la pila esta vacia o no
	 * @return boolean
	 */
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
	
	/**
	 * Metodo que apila valores en la cima de la pila
	 * @param value
	 */
	public void stackData (int value){
		this.cima++;
		//si no lo está, la relleno 
		stack.set(this.cima, value);
	}
	
	/**
	 * Metodo que desapila valores de la cima de la pila
	 * @return true/false
	 */
	public boolean unstackData (){
		//si la pila no está vacía, vacío la posicion más alta, y bajo el nivel en una posición 
		if (!isEmpty()){
			stack.set(this.cima, null);
			this.cima--;
					
			return true;
		} else return false;
	}
	
	/**
	 * Metodo que devuelve el estado de la pila en formato string
	 */
	public String toString(){
		//devuelve el contenido de la pila
		String contenidoPila="";
		if(this.cima == -1){
			return "Pila de operandos: <vacia>\n";
		}
		else{
			for (int i=0; i<=this.cima; i++){
				contenidoPila += this.stack.get(i) +" ";
			}
			return "Pila de operandos: "+contenidoPila+"\n";
		}
	}
	
	/**
	 * Metodo que devuelve la posicion de la cima de la pila
	 * @return cima
	 */
	public int getCima () {
		return this.cima;
	}
	
	/**
	 * Metodo que devuelve el valor de una posicion de la pila
	 * @param pos
	 * @return dato
	 */
	public Integer getDato (int pos) {
		if(!this.isEmpty()){
			return this.stack.get(pos);
		}else{
			return null;
		}
	}
	
	/**
	 * Reinicia la pila limpiando el array y reiniciando el contador de la cima.
	 */
	public void clean () {
		this.stack.clear();
		this.cima = -1;
	}
}
