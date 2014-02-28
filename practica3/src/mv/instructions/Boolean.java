package mv.instructions;

import mv.cpu.Cpu;
import mv.exceptions.InsufficientOperandsException;

abstract public class Boolean implements Instruction {
	private TipoInstruction tipo;
	
	/**
	 * Método que ejecuta la instrucción
	 * @param cpu
	 * @return boolean
	 */
	abstract protected boolean executeAux(Cpu cpu);
	
	/**
	 * Método que ejecuta una instrucción booleana
	 * @param cpu
	 * @return boolean
	 * @throws InsufficientOperandsException 
	 */
	public boolean execute(Cpu cpu) throws InsufficientOperandsException {
		boolean execute = false;
		//obtengo la instrucción
		TipoInstruction instruccionBooleana = this.tipo;
		
		//si tengo 2 operandos en la pila o tengo 1 y es una not
		if (cpu.getSizeStack() >= 2 || (instruccionBooleana.equals(TipoInstruction.NOT) && cpu.getSizeStack() >= 1)) {
			//ejecuto la instrucción y meto el resultado en la pila en función del retorno
			if (this.executeAux(cpu)) cpu.push(1);
			else cpu.push(0);
			//incremento el contador del programa
			cpu.increaseProgramCounter();
			execute = true;
		}
		//si no, retorno false
		else 
			throw new InsufficientOperandsException("Error: no hay operandos suficientes para realizar la operación.");
		
		return execute;
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción booleana
	 * @param s
	 * @return instrucción
	 */
	abstract public Instruction parse (String[] s);
	
	/**
	 * Método que pasa a string la instrucción booleana
	 */
	abstract public String toString ();
}
