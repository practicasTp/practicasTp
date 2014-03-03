package mv.commands;

import mv.exceptions.IncorrectProgramCounterException;
import mv.exceptions.InsufficientOperandsException;
import mv.instructions.Instruction;

public class Step extends CommandInterpreter {
	
	public Step () {
		super();
	}
	
	/**
	 * Método que ejecuta una sola instrucción
	 * @return resultado
	 * @throws InsufficientOperandsException 
	 */
	public boolean executeCommand() {
		boolean resultado = false;
		
		//si la ejecución ha ido correctamente, muestro el estado de la máquina
		if (CommandInterpreter.cpu.step()){
			resultado = true;
			CommandInterpreter.printStateMachine();
			//si hay fallo de ejecución ¿reinicio la máquina?
		}
		
		//si la cpu me dice que no hay más instrucciones entonces paro la máquina
		if(CommandInterpreter.cpu.finished() || CommandInterpreter.cpu.abortComputation()){
			this.isFinished = true;
		//si no, compruebo si esa ha sido la ultima instrucción para parar la máquina
		}else{
			try {
				Instruction nextInstruction = CommandInterpreter.cpu.getCurrentInstruction();
			}catch (IncorrectProgramCounterException e){
				this.isFinished = true;
				System.err.println(e.getMessage());
			}
		}
		
		return resultado;
	}
	
	/**
	 * Método que pasa a string el comando ejecutado
	 * @return step
	 */
	public String toString () {
		return "STEP";
	}
}
