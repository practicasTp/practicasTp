package mv.cpu;

import mv.instructions.Arithmetics;
import mv.instructions.Instruction;
import mv.instructions.TipoInstruction;
import mv.program.ProgramMv;

public class Cpu {
	private Memory memoria;
	private OperandStack pila;
	private Arithmetics alu;
	private boolean fin;
	private ProgramMv program;
	private int pc;
	private boolean correctPc;
	
	public Cpu(){
		this.memoria = new Memory ();
		this.pila = new OperandStack ();
		this.fin = false;
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
	 * Metodo que ejecuta todas las instrucciones que puede usar la cpu
	 * @param instr
	 * @return true/false
	 */
	public boolean execute (Instruction instr){
		TipoInstruction operation;
		int operando;
		boolean execute = true;
		
		//obtengo el operando y la operacion
		operando = instr.getOperando();
		operation = instr.getTipoInstruccion();

		//operaciones de la pila
		if (operation.equals(TipoInstruction.PUSH) || operation.equals(TipoInstruction.POP) || operation.equals(TipoInstruction.FLIP) || operation.equals(TipoInstruction.DUP) || operation.equals(TipoInstruction.OUT)){
			if (operation.equals(TipoInstruction.PUSH)) execute = this.push(operando);
			else if (operation.equals(TipoInstruction.POP)) execute = this.pop();
			else if (operation.equals(TipoInstruction.DUP)) execute = this.dup();
			else if (operation.equals(TipoInstruction.OUT)) {
				if (this.out() != null) {
					System.out.println(this.out());
					this.pop();
					execute = true;
				} else execute = false;
			}
			else execute = this.flip();
		//operaciones de memoria
		}else if (operation.equals(TipoInstruction.STORE)){
			
			//obtengo el dato
			Integer dato = this.pila.getDato(this.pila.getCima());
			
			//si el dato no es null, lo guardo
			if(dato!=null){
			
				//el operando (posicion) no puede ser negativo
				if(operando >= 0){
					execute = this.store(operando, dato.intValue());
				}else{
					System.out.println("Error: No se pueden obtener guardar datos en posiciones negativas.");
					execute = false;
				}
				
			//si el dato es null aviso de que no hay contenido en la pila
			}else{
				System.out.println("Error: No hay contenidos en la pila.");
				execute = false;
			}
	 		
		} else if (operation.equals(TipoInstruction.LOAD)) {
				
					//el operando (posicion) no puede ser negativo
					if (operando >= 0){
						//intento obtener el dato
						Integer dato = this.memoria.getDato(operando);
						//lo almaceno en la pila
						this.push(dato.intValue());
						//si no lo obtengo, aviso
						
					} else {
						execute = false;
						System.out.println("Error: No se pueden cargar posiciones negativas.");
					}
				
				
		
		//operacion de parada de la ejecución
		}else if (operation.equals(TipoInstruction.HALT)) {
			System.out.println("Se finaliza la ejecución.");
			this.fin = true;
			execute = true;
		//operaciones aritmetico-lógicas
		}else if (operation.equals(TipoInstruction.ADD) || operation.equals(TipoInstruction.DIV) || operation.equals(TipoInstruction.MUL) || operation.equals(TipoInstruction.SUB)){
			//si hay más de dos operandos en la pila
			if (this.pila.getCima() >= 1) {
				
				//obtengo los dos operandos
				Integer cima 		= this.pila.getDato (this.pila.getCima());
				Integer subcima 	= this.pila.getDato (this.pila.getCima() - 1);
				Integer resultado 	= null;
				
				//realizo las operaciones
				if (operation.equals(TipoInstruction.ADD)) {
					resultado = alu.add(subcima,cima);
				} else if (operation.equals(TipoInstruction.SUB)) {
					resultado = alu.sub(subcima,cima);
				} else if (operation.equals(TipoInstruction.DIV)) {
					resultado = alu.div(subcima,cima);
				} else if (operation.equals(TipoInstruction.MUL)) {
					resultado = alu.mul(subcima,cima);
				}
				
				//Elmino de la pila los valores usados en la operación.
				this.pop();
				this.pop();
				
				//si la ejecución ha ido bien
				if(resultado != null){
					
					//almaceno el resultado en la pila
					int result = resultado.intValue();
					this.push(result);
					execute = true;
				}else{
					//si no se ha ejecutado bien, todo se queda como estaba
					execute = false;
				}
			//si no hay 2 operandos, lo aviso	
			} else{
				
				System.out.println("Error: No hay operandos suficientes en la pila.");
				execute = false;
			}
			
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