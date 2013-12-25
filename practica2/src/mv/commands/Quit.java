package mv.commands;

public class Quit extends CommandInterpreter {
	
	public Quit(){
		super();
	}
	
	public boolean executeCommand(){
		System.out.println("Se termina la ejecución de la máquina.");
		CommandInterpreter.printStateMachine();
		this.isFinished = true;
		return true;
	}
}
