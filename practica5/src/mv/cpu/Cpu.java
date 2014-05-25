package mv.cpu;

import java.util.ArrayList;

import observers.CPUObserver;
import observers.Observable;
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

public class Cpu implements Observable<CPUObserver> {
	private Memory<Integer> memoria;
	private OperandStack<Integer> pila;
	private boolean fin;
	private ProgramMv program;
	private int pc;
	private boolean correctPc;
	private InputMethod input;
	private OutputMethod output;
	private ArrayList<CPUObserver> observers;
	private boolean pauseRun = false;
	private int delay = 200;
	
	public Cpu(InputMethod input, OutputMethod output, ProgramMv program, int delay){
		this.memoria 	= new Memory<Integer> ();
		this.pila 		= new OperandStack<Integer> ();
		this.fin 		= false;
		this.pc 		= 0;
		this.correctPc 	= true;
		this.input 		= input;
		this.output 	= output;
		this.program 	= program;
		this.observers	= new ArrayList<CPUObserver>();
		if(delay!=0){
			this.delay = delay;
		}
	}
	
	/**
	 * Devuelve la memoria
	 * @return OperandStack
	 */
	public Memory<Integer> getMemory(){
		return this.memoria;
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
		
		//aviso que la ejecucion del run ha empezado
		for(CPUObserver o: this.observers){
			o.onStartRun();
		}
		
		do{
			//si la instrucción se ejecuta correctamente
			if (this.step()){
				resultado = true;
				this.sleepabit();
			}else{
				//si no, paro ejecución
				resultado = false;
			}
		//repito hasta que la cpu me diga que no hay más instrucciones a ejecutar	
		}while(resultado!=false && !this.pauseRun);
		
		//avisar que la ejecución del run ha terminado
		for(CPUObserver o: this.observers){
			o.onEndRun();
		}
		
		if(!this.pauseRun){
			//finalizo la ejecución
			this.exit();
		}else{
			this.pauseRun = false;
		}
		
	}
	
	 public void pause() {       
		 this.pauseRun = true;  
	 }
	 
	 public void loadProgram(ProgramMv p) {      
		this.program = p;
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
	
	/**
	 * Llama al método de escritura del output para que escriba un caracter.
	 * @param char c
	 */
	public void writeChar(char c) {
		this.output.writeChar(c);
	}
	
	/**
	 * Llama al método de lectura del input para que lea el siguiente caracter y
	 * lo devuelva.
	 * @return int
	 */
	public int readChar() {
		int lectura = this.input.readChar();
		
		return lectura;
	}
	
	//Operaciones CPU
	
	/**
	 * Realiza la misma función que la instrucción halt.
	 */
	public void exit () {
		for(CPUObserver o: this.observers){
			o.onHalt();
		}
		this.fin = true;
		this.correctPc = false;
	}
	
	/**
	 * Inicializa todos los atributos de la Cpu para preparar una ejecución con run.
	 * @param newIn ->determina si previamente se ha cargado un nuevo método de entrada
	 * @throws MvError 
	 */
	public void resetCpu (boolean newIn) throws MvError {
		this.input.reset();
		this.output.reset();
		this.fin = false;
		this.pc = 0;
		this.correctPc = true;
		this.pauseRun = false;
		pila.clean();
		memoria.clean();
		
		//llamo a los observers
		for(CPUObserver o: this.observers){
			o.onReset(this.program);
		}
		
		//si se ha cargado un nuevo in, me avisan por lo que tengo que avisar al observador
		if(newIn){
			for(CPUObserver o: this.observers){
				o.onNewIn();
			}
		}
	}
	
	/**
	 * Devuelve la instrucción a ejecutar en función del contador de programa,
	 * en caso de que esté bien. En otro caso devuelve null.
	 * @return Instruction
	 * @throws IncorrectProgramCounterException 
	 */
	public Instruction getCurrentInstruction() {
		//si el contador del programa es menor que el tamaño
		if (this.program.getSizeProgram() > this.pc){
			//devuelvo la instrución
			return this.program.get(this.pc);
		}else {
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
		if (!this.correctPc){
			for(CPUObserver o: this.observers){
				o.onHalt();
			}
			return true;
		}
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
			
			//aviso a los observers que comienza la ejecución
			for(CPUObserver o: this.observers){
				o.onStartInstrExecution(this.program.get(this.pc));
			}
			
			//retorno cómo ha ido la ejecución
			try {
				inst.execute(this);
			} catch (InsufficientOperandsException e) {
				for(CPUObserver o: this.observers){
					o.onError(e.getMessage());
				}
				throw new InsufficientOperandsException(e.getMessage());
			} catch (EmptyStackException e) {
				for(CPUObserver o: this.observers){
					o.onError(e.getMessage());
				}
				throw new EmptyStackException(e.getMessage());
			} catch (DivisionByZeroException e) {
				for(CPUObserver o: this.observers){
					o.onError(e.getMessage());
				}
				throw new DivisionByZeroException(e.getMessage());
			} catch (IncorrectProgramCounterException e) {
				for(CPUObserver o: this.observers){
					o.onError(e.getMessage());
				}
				throw new IncorrectProgramCounterException(e.getMessage());
			} catch (NegativeNumberIntoMemoryException e) {
				for(CPUObserver o: this.observers){
					o.onError(e.getMessage());
				}
				throw new NegativeNumberIntoMemoryException(e.getMessage());
			} catch (IncorrectMemoryPositionException e) {
				for(CPUObserver o: this.observers){
					o.onError(e.getMessage());
				}
				throw new IncorrectMemoryPositionException(e.getMessage());
			}
			execute = true;
			
			//aviso a los observers que comienza la ejecución
			for(CPUObserver o: this.observers){
				o.onEndInstrExecution(pc,this.getMemory(),this.getOperandStack(), this.getProgram());
			}
			
		} else{
			this.exit();
		}
		
		return execute;
	}
	
	private void sleepabit() {
		try {
			Thread.sleep(this.delay);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
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
	
	/**
	 * Incrementa el program counter tantas veces como indique el parámetro de entrada.
	 * @param n
	 */
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

	/**
	 * Método que añade un observador de la cpu
	 */
	public void addObserver(CPUObserver o) {
		observers.add(o);		
	}

	/**
	 * Método que elimina un observador de la cpu
	 */
	@Override
	public void removeObserver(CPUObserver o) {
		observers.remove(o);
		
	}

	/**
	 * Método que avisa a los observadores que el programa ha sido cargado
	 */
	public void programLoaded() {
		for(CPUObserver o: this.observers){
			o.onReset(program);
		}
	}
}