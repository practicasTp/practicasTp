package mv.cpu;

import mv.instructions.Instruction;
import mv.program.ProgramMv;

public class Cpu {
	private Memory memoria;
	private OperandStack pila;
	private boolean fin;
	private ProgramMv program;
	private int pc;
	private boolean correctPc;
	
	public Cpu(){
		this.memoria = new Memory ();
		this.pila = new OperandStack ();
		this.fin = false;
		this.pc = 0;
		this.correctPc = true;
	}
	
	/**
	 * Metodo que devuelve si la cpu ha sido finalizada o no
	 * @return true/false
	 */
	public boolean finished(){
		return this.fin;
	}
	
	//Operaciones de la pila
	
	/**
	 * Metodo que almacena en la pila un valor
	 * @param value
	 * @return true/false
	 */
	public boolean push (int value) {
		this.pila.stackData(value);
		return true;
	}
	
	/**
	 * Metodo que elimina de la pila el valor de la cima
	 * @return true/false
	 */
	public int pop () {
		//si no se apila hay error
		int value = this.pila.getDato(this.pila.getCima());
		this.pila.unstackData();
		
		return value;
	}
	
	//Operaciones de la memoria.
	
	/**
	 * Metodo que almacena en una posicion de la memoria, un dato.
	 * @param pos
	 * @param dato
	 * @return true/false
	 */
	public boolean store (int pos, int dato) {
		this.memoria.storeData(pos, dato);
		return true;
	}
	
	/**
	 * Carga en la cpu un programa una vez haya sido completado.
	 * @param p contiene el programa
	 */
	public void loadProgram (ProgramMv p) {
		this.program = p;
	}
	
	public void exit () {
		
	}
	
	/**
	 * Inicializa todos los atributos de la Cpu para preparar una ejecución con run.
	 */
	public void resetCpu () {
		
	}
	
	/**
	 * Devuelve la instrucción a ejecutar en función del contador de programa,
	 * en caso de que esté bien. En otro caso devuelve null.
	 * @return
	 */
	public Instruction getCurrentInstruction () {
		if (this.program.getSizeProgram() < this.pc)
			return this.program.get(this.pc);
		else {
			this.correctPc = false;
			return null;
		}
	}
	
	/**
	 * Devuelve true si la ejecución debe detenerse, bien porque el contador de programa
	 * no es correcto, o bien por que se ha ejecutado la instrucción halt.
	 * @return
	 */
	public boolean abortComputation () {
		if (!this.correctPc) return true;
		else return false;
	}
	
	/**
	 * Ejecuta la siguiente instrucción, es decir, la situada en el contador de programa.
	 * @return
	 */
	public boolean step (Cpu cpu) {
		Instruction inst = this.getCurrentInstruction();
		if (inst != null) {
			if (inst.execute(cpu)) return true;
			else return false;
		} else return false;
	}
	
	/**
	 * Método que devuelve el tamaño de la pila.
	 * @return tamaño de la pila.
	 */
	public int getSizeStack () {
		return this.pila.getCima() + 1;
	}
	
	/**
	 * Método que consigue un dato de la memoria situado en la posición indicada.
	 * @param pos
	 * @return un entero
	 */
	public int getMemoryValue (int pos) {
		return this.memoria.getDato(pos);
	}
	
	/**
	 * Incrementa en uno el valor del contador de programa
	 */
	public void increaseProgramCounter() {
		this.pc++;
	}
	
	/**
	 * Actualiza el contador de programa a la posición indicada.
	 * @param pos
	 */
	public void jumpProgramCounter(int pos) {
		this.pc = pos;
	}
	
	/**
	 * Metodo que devuelve el estado de la cpu en formato string
	 */
	public String toString(){
		String contenidoCpu;
		System.out.println("El estado de la máquina tras ejecutar la instrucción es:");
		contenidoCpu = this.memoria.toString();
		contenidoCpu += this.pila.toString();
		
		return contenidoCpu;
	}
}