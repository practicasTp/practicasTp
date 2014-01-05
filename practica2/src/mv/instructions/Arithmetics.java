package mv.instructions;
import mv.cpu.Cpu;

abstract public class Arithmetics extends Instruction {
	protected int result;
	
	public Arithmetics(TipoInstruction tipo) {
		super(tipo);
	}
	
	/**
	 * Método abstracto que ejecuta una instrucción con 2 operandos
	 * @param n1
	 * @param n2
	 * @return
	 */
	abstract protected boolean execute (int n1, int n2);
	
	/**
	 * Método que ejecuta una instrucción de la cpu
	 * @return resultado
	 */
	public boolean execute (Cpu cpu) {
		//si la pila tiene 2 o más operandos
		if (cpu.getSizeStack () >= 2){
			//saco 2 operandos
			int n1 = cpu.pop();
			int n2 = cpu.pop();
			//ejecuto la operación
			if (this.execute (n1, n2)) {
				//meto el resultado en la cpu
				cpu.push (this.result);
				//incremento el contador de programa
				cpu.increaseProgramCounter ();
				//retorno true
				return true;
			}//si no ha ido bien la ejecución
			else return false;
		}//si no lo tiene retorno falso
		else return false;
	}
	
	/**
	 * Método abstracto que parsea una instrucción arithmética
	 * @return instruccion
	 */
	abstract public Instruction parse (String[] s);
	
	/**
	 * Método que pasa a string la instrucción
	 */
	abstract public String toString ();
}
