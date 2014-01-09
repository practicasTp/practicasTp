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
	
	/**
	 * Realiza la misma función que a instrucción halt.
	 */
	public void exit () {
		this.fin = true;
	}
	
	/**
	 * Inicializa todos los atributos de la Cpu para preparar una ejecución con run.
	 */
	public void resetCpu () {
		this.fin = false;
		this.pc = 0;
		this.correctPc = true;
		pila.clean();
		memoria.clean();
	}
	
	/**
	 * Devuelve la instrucción a ejecutar en función del contador de programa,
	 * en caso de que esté bien. En otro caso devuelve null.
	 * @return Instruction
	 */
	public Instruction getCurrentInstruction () {
		//si el contador del programa es menor que el tamaño
		if (this.program.getSizeProgram() > this.pc)
			//devuelvo la instrución
			return this.program.get(this.pc);
		else {
			//si no entonces ya no tengo contador del programa
			this.correctPc = false;
			return null;
		}
	}
	
	/**
	 * Devuelve true si la ejecución debe detenerse, bien porque el contador de programa
	 * no es correcto, o bien por que se ha ejecutado la instrucción halt.
	 * @return boolean
	 */
	public boolean abortComputation () {
		if (!this.correctPc) return true;
		else return false;
	}
	
	/**
	 * Ejecuta la siguiente instrucción, es decir, la situada en el contador de programa.
	 * @return boolean
	 */
	public boolean step () {
		//obtengo una instruccion
		Instruction inst = this.getCurrentInstruction();
		//si obtengo una
		if (inst != null) {
			//la ejecuto
			System.out.println("Comienza la ejecución de "+inst.toString());
			//retorno cómo ha ido la ejecución
			if (inst.execute(this)){
				return true;
			}else{
				System.out.println("Error en la ejecución.");
				return false;
			}
		//si no, finalizo la ejecución
		} else{
			this.exit();
			return false;
		}
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
	
	public void increaseProgramCounter(int n){
		this.pc = this.pc + n;
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