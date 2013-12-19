package mv.commands;

public class Quit extends CommandInterpreter {
	
	public Quit(){
		super();
	}
	
	public boolean executeCommand(){
		this.isFinished = true;
		return true;
	}
}
