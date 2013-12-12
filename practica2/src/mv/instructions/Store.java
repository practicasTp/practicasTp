package mv.instructions;

import mv.cpu.Cpu;

public class Store extends SystemMv{

	public Store (int operando) {
		super (TipoInstruction.STORE);
		this.operando = operando;
	}
	
	/**
	 * Metodo que almacena en una posicion de la memoria, un dato.
	 * @param operando que actua como la posición
	 * @param cima que actua como el dato a almacenar
	 * @return true/false
	 */
	public boolean executeAux (Cpu cpu) {
		if (cpu.getSizeStack() >= 1) {
			int cima = cpu.pop();
			cpu.store(this.operando, cima);
			return true;
		} else return false;
	}
	
	public Instruction parse (String[] s) {
		if (s.length == 2 && s[0].equalsIgnoreCase("STORE")) {
			int operando = Integer.parseInt(s[1]);
			return new Store (operando);
		} else return null;
	}
	
	public String toString () {
		return "STORE " + this.operando;
	}
}
