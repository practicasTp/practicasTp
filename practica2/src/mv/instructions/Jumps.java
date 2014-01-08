package mv.instructions;
import mv.cpu.Cpu;

abstract public class Jumps extends Instruction {
	protected int operando;
	
	public Jumps (TipoInstruction tipo, int operando) {
		super(tipo, operando);
		this.operando = operando;
	}
	
	/**
	 * Método abstracto que ejecutará la instrucción.
	 * @param cpu
	 * @return boolean
	 */
	abstract protected boolean executeAux (Cpu cpu);
	
	/**
	 * Comprueba que la pila tiene más de 0 componentes.
	 */
	public boolean execute (Cpu cpu) {
		if(executeAux(cpu)) 
			return true;
		else 
			return false;
	}
	
	/**
	 * Método que parseará un string para identificar la instrucción.
	 * @return Instruction
	 */
	abstract public Instruction parse (String[] s);
	
	/**
	 * Pasa a string la instrucción.
	 * @return string
	 */
	abstract public String toString ();
}
