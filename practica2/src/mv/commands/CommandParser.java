package mv.commands;

public class CommandParser {
	
	/**
	 * Este método se encarga de cortar y analizar el string e identificar el tipo de comando que es para crear un objeto de ese tipo.
	 * @param line es el string que contiene el comando completo.
	 * @return comando que contiene el objeto del tipo correspondiente.
	 */
	public static CommandInterpreter parseCommand (String line) {
		String[] cadena = line.split(" +");
		CommandInterpreter comando = null;
		
		if (cadena.length == 1) {
			if (cadena[0].equalsIgnoreCase("STEP"))
				comando = new Step ();
			else if (cadena[0].equalsIgnoreCase("RUN"))
				comando = new Run ();
			else if (cadena[0].equalsIgnoreCase("QUIT"))
				comando = new Quit ();
		} else if (cadena.length == 2 && CommandParser.validarOperando(cadena[1])) {
			if (cadena[0].equalsIgnoreCase("STEPS")) 
				comando = new Steps (Integer.parseInt(cadena[1]));
		}
		
		return comando;
	}
	
	/**
	 * Garantiza que el operando de los comandos que lo necesitan sea correcto.
	 * @param operando es un String que contiene el operando.
	 * @return un booleano que indica si el operando es correcto o no.
	 */
	private static boolean validarOperando(String operando){
		return operando.matches("[-+]?\\d*\\.?\\d+");
	}
}
