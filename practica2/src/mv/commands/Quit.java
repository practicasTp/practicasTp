package mv.commands;

public class Quit extends CommandInterpreter {
	
	public Quit(){
		super();
	}
	
	/**
	 * Método que se encarga de parar la máquina
	 * @return true
	 */
	public boolean executeCommand(){
		this.isFinished = true;
		return true;
	}
	
	/**
	 * Método que pasa a string el comando ejecutado
	 * @return QUIT
	 */
	public String toString () {
		return "QUIT ";
	}
}
