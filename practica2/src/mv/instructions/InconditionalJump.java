package mv.instructions;

import mv.cpu.Cpu;

public class InconditionalJump extends Jumps{

	public InconditionalJump (int operando) {
		super (TipoInstruction.JUMP, operando);
		this.operando = operando;
	}
	
	/**
	 * Modifica el contador de programa de la cpu cambiando el valor por el indicado en el
	 * atributo operando.
	 * @param cpu
	 * @param boolean
	 */
	public boolean executeAux (Cpu cpu) {
		cpu.jumpProgramCounter(this.operando);
		return true;
	}
	
	/**
	 * método que parsea un string para identificarse como una instrucción jump
	 * @param s
	 * @return new InconditionalJump o null
	 */
	public Instruction parse (String[] s) {
		if (s.length == 2 && s[0].equalsIgnoreCase("JUMP")) {
			int operando = Integer.parseInt(s[1]);
			return new InconditionalJump (operando);
		} else return null;
	}
	
	/**
	 * método que pasa a string la instrucción jump
	 * @return "JUMP"
	 */
	public String toString () {
		return "JUMP " + this.operando;
	}
}
