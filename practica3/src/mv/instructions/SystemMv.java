package mv.instructions;

import mv.ExecutionMode;
import mv.cpu.Cpu;
import mv.exceptions.EmptyStackException;
import mv.exceptions.NegativeNumberIntoMemoryException;

abstract public class SystemMv implements Instruction {
	protected int operando;

	
	/**
	 * Método que ejecuta la instrucción
	 * @param cpu
	 * @return boolean
	 * @throws NegativeNumberIntoMemoryException 
	 * @throws EmptyStackException 
	 * @throws Exception 
	 */
	abstract protected boolean executeAux(Cpu cpu) throws NegativeNumberIntoMemoryException, EmptyStackException ;
	
	/**
	 * Se encarga de ejecutar las instrucciones propias del sistema, incrementando el 
	 * contador de programa en caso de que se ejecuten correctamente.
	 * @param cpu
	 * @return boolean
	 */
	public boolean execute(Cpu cpu){
		boolean resultado = false;
		try {
			if(this.executeAux(cpu)) {
				cpu.increaseProgramCounter();
				resultado = true;
			} 
		} catch (NegativeNumberIntoMemoryException e) {
			System.err.println(e.getMessage());
			if(cpu.mode == ExecutionMode.BACH){
				System.exit(1);
			}
		} catch (EmptyStackException e) {
			System.err.println(e.getMessage());
		}
		
		return resultado;
	}
	
	/**
	 * Parsea un string para identificar la instrucción.
	 * @param s
	 * @return instruction.
	 */
	abstract public Instruction parse (String[] s);
	
	/**
	 * Pasa a string la instrucción.
	 * @return string
	 */
	abstract public String toString ();
}
