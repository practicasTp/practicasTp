package mv.commands;

import mv.ExecutionMode;
import mv.cpu.Cpu;
import mv.exceptions.InsufficientOperandsException;
import mv.exceptions.EmptyStackException;
import mv.exceptions.NegativeNumberIntoMemoryException;

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
		//inicializo la cpu con una cpu con un programa cargado
		CommandInterpreter.cpu = cpu;
	}
	
	/**
	 * Método abstracto que debe implementar la ejecución de las subclases.
	 * @return true si todo ha ido bien o false en caso contrario.
	 * @throws InsufficientOperandsException 
	 * @throws EmptyStackException 
	 * @throws NegativeNumberIntoMemoryException 
	 */
	public abstract boolean executeCommand() throws InsufficientOperandsException, EmptyStackException, NegativeNumberIntoMemoryException;
	
	/**
	 * Indica si el programa ha terminado y por lo tanto no se pueden ejecutar más comandos.
	 * @return boolean
	 */
	public boolean isFinished () {
		//devuelvo true o false en función del valor de la variable 
		if (this.isFinished)
			return true;
		else return false;
	}
	
	/**
	 * Método que printa el estado de la máquina
	 */
	public static void printStateMachine () {
		System.out.println(CommandInterpreter.cpu.toString());
	}
}
