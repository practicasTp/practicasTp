package mv.commands;

import mv.exceptions.InsufficientOperandsException;

public class Run extends Step {
	public Run(){
		super();
	}
	
	/**
	 * Metodo que se encarga de ejecutar todas las instrucciones
	 * @return resultado
	 * @throws InsufficientOperandsException 
	 */
	public boolean executeCommand() throws InsufficientOperandsException{
		//cuando hago un run, reseteo la máquina
		CommandInterpreter.cpu.resetCpu();
		boolean resultado = false;
		
		do{
			//si la instrucción se ejecuta correctamente
			if (CommandInterpreter.cpu.step()){
				resultado = true;
				//muestro el estado de la máquina
				CommandInterpreter.printStateMachine();
			}else{
				//si no, paro ejecución
				resultado = false;
			}
		//repito hasta que la cpu me diga que no hay más instrucciones a ejecutar	
		}while(resultado!=false);
			
		//si la cpu me dice que ha terminado, paro la máquina (del bucle se puede salir por fallo de instrucción)
		if(CommandInterpreter.cpu.finished()  || CommandInterpreter.cpu.abortComputation()){
			this.isFinished = true;
		}
		
		return resultado;
	}
	
	/**
	 * Método que pasa a string el comando ejecutado
	 * @return RUN
	 */
	public String toString () {
		return "RUN ";
	}
}
