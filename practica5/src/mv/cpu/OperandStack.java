package mv.cpu;

import java.util.ArrayList;

import mv.exceptions.EmptyStackException;
import observers.CPUObserver;
import observers.Observable;
import observers.StackObserver;

public class OperandStack<T> implements Observable<StackObserver<T>>{
	private int cima;
	private Object[] stack;
	public final int TAMANIO = 30;
	private ArrayList<StackObserver<T>> observers;

	public OperandStack(){
		this.observers	= new ArrayList<StackObserver<T>>();
		this.stack = new Object[TAMANIO];
		this.cima = -1;
	}
	
	/**
	 * Metodo que inicializa la pila a null con el tamaño deseado
	 * @param tamanio
	 * @return pila inicializada
	 */
	@SuppressWarnings("all") private T[] inicializaPila(int tamanio){
		
		//creo un auxiliar con el tamaÃ±o que me indican
		Object [] r = new Object[tamanio];
		
		//recorro ese array y lo voy inicializando
		for(int i=0; i < tamanio; i++){
			r[i] = null;
		}
		
		//devuelvo el array inicializado
		return (T[])r;
		
	}
	
	/**
	 * Metodo que determina si la pila esta vacia o no
	 * @return boolean
	 */
	public boolean isEmpty(){
		boolean resultado;
		
		//si la cima es menos 1, considero que estÃ¡ vacÃ­a
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
	public void stackData (T value){
		//compruebo si la pila estÃ¡ llena
		if(this.cima < (stack.length - 1)){
			this.cima++;
			//si no lo estÃ¡, la relleno 
			stack[this.cima]=value;
					
		}else{
			//doblo el tamaÃ±o de la pila
			int nuevoTamanio = this.stack.length*2;
			this.stack = this.redimensionaPila(nuevoTamanio);
			this.cima++;
			//relleno el dato
			stack[this.cima]=value;
		}
		
		//aviso a los observers
		for(StackObserver<T> o: this.observers){
			o.onPush(value);
		}
		
	}
	
	/**
	 * Metodo que desapila valores de la cima de la pila
	 * @return true/false
	 */
	public boolean unstackData (){
		//si la pila no estÃ¡ vacÃ­a, vacÃ­o la posicion mÃ¡s alta, y bajo el nivel en una posiciÃ³n 
		if (!isEmpty()){
			T value = (T)this.stack[this.cima];
			stack[this.cima] = null;
			this.cima--;
			
			//aviso a los observers
			for(StackObserver<T> o: this.observers){
				o.onPop(value);
			}
			
			return true;
		} else return false;
	}
	
	/**
	 * Metodo que redimensiona la pila a un tamaÃ±o deseado
	 * @param tamanio
	 * @return auxiliar
	 */
	@SuppressWarnings("all") private T[] redimensionaPila(int tamanio){
		//creo un auxiliar con el tamaÃ±o que me pasan
		Object [] aux = new Object[tamanio];
		
		//copio el contenido de la pila al axuliar
		for(int i=0;i< this.stack.length;i++){
			aux[i] = this.stack[i];
		}
		
		//devuelvo el auxiliar con el nuevo tamaÃ±o y el contenido copiado de la pila
		return (T[])aux;
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
				contenidoPila += this.stack[i] +" ";
			}
			return "Pila de operandos: "+contenidoPila+"\n";
		}
	}
	
	/**
	 * Metodo que devuelve el valor de una posicion de la pila
	 */
	public int operandToString(int line){
		if(this.isEmpty()) return (Integer) null;
		else{
			return (Integer)this.stack[line];
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
	 * @throws EmptyStackException 
	 */
	@SuppressWarnings("all") public T getDato (int pos) throws EmptyStackException {
		T dato = null;
		if(!this.isEmpty()){
			dato = (T)this.stack[pos];
		}else
			throw new EmptyStackException("Error: la pila está vacía por lo que no se pueden extraer elementos.\n");
		
		return dato;
	}
	
	/**
	 * Reinicia la pila limpiando el array y reiniciando el contador de la cima.
	 */
	public void clean () {
		this.stack = this.inicializaPila(TAMANIO);
		this.cima = -1;
	}
	
	@Override
	public void addObserver(StackObserver<T> o) {
		observers.add(o);
	}
	
	@Override
	public void removeObserver(StackObserver<T> o) {
		observers.remove(o);
	}
}
