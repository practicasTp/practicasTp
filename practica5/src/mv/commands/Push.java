package mv.commands;

public class Push extends CommandInterpreter {
	private int pushedNumber;
	
	public Push (int pushedNumber) {
		this.pushedNumber = pushedNumber;
	}
	
	/**
	 * Metodo que se encarga de apilar un número en la pila
	 * @return resultado
	 */
	public boolean executeCommand() {
		boolean resultado = true;
		
		//apilo el número
		CommandInterpreter.cpu.push(this.pushedNumber);
		//muestro estado de la máquina
		CommandInterpreter.printStateMachine();
		return resultado;
	}
	
	/**
	 * Método que pasa a string el comando ejecutado
	 * @return push + numero que se apila en la pila de cpu
	 */
	public String toString () {
		return "PUSH "+this.pushedNumber;
	}
}