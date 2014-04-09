package mv.instructions;
import mv.cpu.Cpu;
import mv.exceptions.DivisionByZeroException;
import mv.exceptions.EmptyStackException;
import mv.exceptions.InsufficientOperandsException;

abstract public class Arithmetics implements Instruction {
	protected int result;
	
	/**
	 * Método abstracto que ejecuta una instrucción con 2 operandos
	 * @param n1
	 * @param n2
	 * @return boolean
	 * @throws DivisionByZeroException 
	 */
	abstract protected void execute (int n1, int n2) throws DivisionByZeroException;
	
	/**
	 * Método que ejecuta una instrucción de la cpu
	 * @return resultado
	 * @param cpu
	 * @throws DivisionByZeroException 
	 * @throws EmptyStackException 
	 */
	public void execute (Cpu cpu) throws InsufficientOperandsException, EmptyStackException, DivisionByZeroException{
		//si la pila tiene 2 o más operandos	
			if (cpu.getSizeStack () >= 2){
				//saco 2 operandos
				int n2 = cpu.pop();
				int n1 = cpu.pop();
				//ejecuto la operación
				this.execute (n1, n2);
				//meto el resultado en la cpu
				cpu.push (this.result);
				//incremento el contador de programa
				cpu.increaseProgramCounter ();
			}//si no lo tiene retorno falso
			else {
				throw new InsufficientOperandsException("Error: Faltan operandos para realizar la operación.\n");
			}
	}
	
	/**
	 * Método abstracto que parsea una instrucción arithmética
	 * @return instruccion
	 * @param s
	 */
	abstract public Instruction parse (String[] s);
	
	/**
	 * Método que pasa a string la instrucción
	 */
	abstract public String toString ();
}
