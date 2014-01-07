package mv.instructions;

import mv.cpu.Cpu;

public class Push extends SystemMv {

	public Push (int operando) {
		super (TipoInstruction.PUSH);
		this.operando = operando;
	}
	
	/**
	 * Introcude un nuevo valor en la pila.
	 * @param cpu
	 */
	public boolean executeAux (Cpu cpu) {
		cpu.push(this.operando);
		return true;
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción push
	 * @return new Push o null
	 */
	public Instruction parse (String[] s) {
		if (s.length == 2 && s[0].equalsIgnoreCase("PUSH")) {
			int operando = Integer.parseInt(s[1]);
			return new Push (operando);
		} else return null;
	}
	
	/**
	 * método que pasa a string la instrucción push
	 * @return "PUSH"
	 */
	public String toString () {
		return "PUSH " + this.operando;
	}
}
