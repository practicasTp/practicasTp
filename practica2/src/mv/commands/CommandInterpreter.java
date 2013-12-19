package mv.commands;

import mv.cpu.Cpu;

abstract public class CommandInterpreter {
	protected boolean isFinished;
	protected static Cpu cpu;
	
	public CommandInterpreter () {
		this.isFinished=false;
	}
	
	/**
	 * Inicializa el atributo cpu una vez tenga cargado el programa.
	 * @param cpu
	 */
	public static void configureCommandInterpreter (Cpu cpu) {
		CommandInterpreter.cpu = cpu;
	}
	
	/**
	 * Método abstracto que debe implementar la ejecución de las subclases.
	 * @return true si todo ha ido bien o false en caso contrario.
	 */
	public abstract boolean executeCommand();
	
	/**
	 * Indica si el programa ha terminado y por lo tanto no se pueden ejecutar más comandos.
	 * @return
	 */
	public boolean isFinished () {
		if (this.isFinished)
			return true;
		else return false;
	}
	
	public static void printStateMachine () {
		CommandInterpreter.cpu.toString();
	}
}
