package mv.cpu;

import mv.exceptions.DivisionByZeroException;
import mv.exceptions.EmptyStackException;
import mv.exceptions.IncorrectMemoryPositionException;
import mv.exceptions.IncorrectProgramCounterException;
import mv.exceptions.InsufficientOperandsException;
import mv.exceptions.MvError;
import mv.exceptions.NegativeNumberIntoMemoryException;
import mv.instructions.Instruction;
import mv.program.ProgramMv;
import mv.reading.InputMethod;
import mv.writing.OutputMethod;

public class Cpu {
	private Memory<Integer> memoria;
	private OperandStack<Integer> pila;
	private boolean fin;
	private ProgramMv program;
	private int pc;
	private boolean correctPc;
	private InputMethod input;
	private OutputMethod output;
	
	public Cpu(InputMethod input, OutputMethod output, ProgramMv program){
		this.memoria 	= new Memory<Integer> ();
		this.pila 		= new OperandStack<Integer> ();
		this.fin 		= false;
		this.pc 		= 0;
		this.correctPc 	= true;
		this.input 		= input;
		this.output 	= output;
		this.program 	= program;
	}
	
	/**
	 * Devuelve la pila
	 * @return OperandStack
	 */
	public OperandStack<Integer> getOperandStack(){
		return this.pila;
	}
	
	/**
	 * Devuelve el programa cargado
	 * @return program
	 */
	public ProgramMv getProgram(){
		return this.program;
	}
	
	/**
	 * Cambia el input
	 * @param s
	 * @throws MvError
	 */
	public void setInStream(InputMethod s) throws MvError { 
		if (s == null) throw new MvError("Cannot set inStream to null");
		else input = s;
	}
	
	/**
	 * Cambia el output
	 * @param s
	 * @throws MvError
	 */
	public void setOutStream(OutputMethod s) throws MvError {
		if (s == null) throw new MvError("Cannot set inStream to null");
		else output = s;
	}
	
	/**
	 * Devuelve el input
	 * @return input
	 */
	public InputMethod getInStream() { return input; }
	
	/**
	 * Devuelve el output
	 * @return output
	 */
	public OutputMethod getOutStream() { return output; }
	
	/**
	 * Método que ejecuta todas las instrucciones del programa cargado en la cpu
	 * @throws IncorrectMemoryPositionException 
	 * @throws IncorrectProgramCounterException 
	 * @throws DivisionByZeroException 
	 */
	public void run() throws EmptyStackException, NegativeNumberIntoMemoryException, InsufficientOperandsException, DivisionByZeroException, IncorrectProgramCounterException, IncorrectMemoryPositionException{
		boolean resultado = false;
		this.resetCpu();
		
		do{
			//si la instrucción se ejecuta correctamente
			if (this.step()){
				resultado = true;
			}else{
				//si no, paro ejecución
				resultado = false;
				System.exit(1);
			}
		//repito hasta que la cpu me diga que no hay más instrucciones a ejecutar	
		}while(resultado!=false);
		
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
	 * @throws EmptyStackException 
	 */
	public int pop () throws EmptyStackException {
		//si no se apila hay error
		int value = 0;
		
		value = this.pila.getDato(this.pila.getCima());
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
	public Instruction getCurrentInstruction() {
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
	 * Retorna el contador de programa
	 * @return
	 */
	public int getPC(){
		return this.pc;
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
	 * @throws IncorrectMemoryPositionException 
	 * @throws NegativeNumberIntoMemoryException 
	 * @throws IncorrectProgramCounterException 
	 * @throws DivisionByZeroException 
	 * @throws EmptyStackException 
	 * @throws InsufficientOperandsException 
	 * @throws Exception 
	 */
	public boolean step () throws InsufficientOperandsException, EmptyStackException, DivisionByZeroException, IncorrectProgramCounterException, NegativeNumberIntoMemoryException, IncorrectMemoryPositionException {
		boolean execute = false;
		
		//obtengo una instruccion
		Instruction inst = this.getCurrentInstruction();
		if (inst != null) {			
			//retorno cómo ha ido la ejecución
			inst.execute(this);
			execute = true;
		} else{
			this.exit();
		}
		
		return execute;
	}
	
	/**
	 * Método que devuelve el tamaño de la pila.
	 * @return tamaño de la pila.
	 * @throws EmptyStackException 
	 */
	public int getSizeStack () throws EmptyStackException {
		if((this.pila.getCima() + 1) >= 1)
			return this.pila.getCima() + 1;
		else throw new EmptyStackException("Error: la pila está vacía.\n");
	}
	
	/**
	 * Método que consigue un dato de la memoria situado en la posición indicada.
	 * @param pos
	 * @return un entero
	 * @throws IncorrectMemoryPositionException 
	 */
	public int getMemoryValue (int pos) throws IncorrectMemoryPositionException {
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
	 * @throws IncorrectProgramCounterException 
	 */
	public void jumpProgramCounter(int pos) throws IncorrectProgramCounterException {
		int prueba = this.program.getSizeProgram();
		if(pos >= prueba){
			this.exit();
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