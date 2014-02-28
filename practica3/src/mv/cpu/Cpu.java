package mv.cpu;

import mv.ExecutionMode;
import mv.exceptions.IncorrectProgramCounterException;
import mv.exceptions.InsufficientOperandsException;
import mv.instructions.Instruction;
import mv.program.ProgramMv;
import mv.reading.InputMethod;
import mv.writing.OutputMethod;

public class Cpu {
	private Memory memoria;
	private OperandStack pila;
	private boolean fin;
	private ProgramMv program;
	private int pc;
	private boolean correctPc;
	private InputMethod input;
	private OutputMethod output;
	public ExecutionMode mode;
	
	public Cpu(ExecutionMode mode, InputMethod input, OutputMethod output, ProgramMv program){
		this.memoria 	= new Memory ();
		this.pila 		= new OperandStack ();
		this.fin 		= false;
		this.pc 		= 0;
		this.correctPc 	= true;
		this.input 		= input;
		this.output 	= output;
		this.program 	= program;
		this.mode 		= mode;
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
	
	//Operaciones E/S
	
	public void writeChar(char c) {
		this.output.writeChar(c);
	}
	
	public int readChar() {
		int lectura = this.input.readChar();
		
		return lectura;
	}
	
	//Operaciones CPU
	
	/**
	 * Realiza la misma función que a instrucción halt.
	 */
	public void exit () {
		this.fin = true;
		this.correctPc = false;
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
	 * @throws IncorrectProgramCounterException 
	 */
	public Instruction getCurrentInstruction () throws IncorrectProgramCounterException{
		Instruction inst = null;
		this.correctPc = false;
		
		//si el contador del programa es menor que el tamaño
		if (this.program.getSizeProgram() > this.pc) {
			//devuelvo la instrución
			this.correctPc = true;
			inst = this.program.get(this.pc);
		} else {
			//si no entonces ya no tengo contador del programa
			throw new IncorrectProgramCounterException("Error: el contador de programa no es correcto.");
		}
		
		return inst;
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
	 * @throws Exception 
	 */
	public boolean step () {
		boolean execute = false;
		
		//obtengo una instruccion
		try {
			Instruction inst = this.getCurrentInstruction();
			if(mode == ExecutionMode.INTERACTIVE){
				System.out.println("Comienza la ejecución de "+inst.toString());
			}
			//retorno cómo ha ido la ejecución
			try {
				if (inst.execute(this)){
					execute = true;
				}
			}
			catch (InsufficientOperandsException e) {
				System.err.println(e.getMessage());
			}
		}
		catch (IncorrectProgramCounterException e) {
			System.err.println(e.getMessage());
			this.exit();
		}
		
		return execute;
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
		int prueba = this.program.getSizeProgram();
		if(pos >= prueba){
			System.err.println("Error: Contador de programa fuera de rango.");
		}
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