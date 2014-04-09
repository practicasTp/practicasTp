package mv.commands;

import mv.exceptions.EmptyStackException;
import mv.exceptions.InsufficientOperandsException;
import mv.exceptions.NegativeNumberIntoMemoryException;

public class Run extends Step {
	public Run(){
		super();
	}
	
	/**
	 * Metodo que se encarga de ejecutar todas las instrucciones
	 * @return resultado
	 * @throws InsufficientOperandsException 
	 */
	public boolean executeCommand(){
		//cuando hago un run, reseteo la máquina
		CommandInterpreter.cpu.resetCpu();
		boolean resultado = false;
		
		try {
			CommandInterpreter.cpu.run();
		} catch (EmptyStackException e) {
			System.err.println(e.getMessage());
			this.isFinished = true;
		} catch (NegativeNumberIntoMemoryException e) {
			System.err.println(e.getMessage());
			this.isFinished = true;
		}catch (InsufficientOperandsException e) {
			System.err.println(e.getMessage());
			this.isFinished = true;
		}
			
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
