package mv.commands;

public class CommandParser {
	
	/**
	 * Este método se encarga de cortar y analizar el string e identificar el tipo de comando que es para crear un objeto de ese tipo.
	 * @param line es el string que contiene el comando completo.
	 * @return comando que contiene el objeto del tipo correspondiente.
	 */
	public static CommandInterpreter parseCommand (String line) {
		//divido la cadena e inicializo el comando a null
		String[] cadena = line.split(" +");
		CommandInterpreter comando = null;
		
		//proceso los comandos sin operando
		if (cadena.length == 1) {
			if (cadena[0].equalsIgnoreCase("STEP"))
				comando = new Step ();
			else if (cadena[0].equalsIgnoreCase("RUN"))
				comando = new Run ();
			else if (cadena[0].equalsIgnoreCase("QUIT"))
				comando = new Quit ();
			else if (cadena[0].equalsIgnoreCase("POP"))
				comando = new Pop ();
		//proceso los comandos con 1 operando y que el operando sea un número
		} else if (cadena.length == 2 && CommandParser.validarOperando(cadena[1])) {
			//si la cadena es un step
			if (cadena[0].equalsIgnoreCase("STEP")){
				//si el número es mayor a 0
				if( Integer.parseInt(cadena[1]) > 0){
					comando = new Steps (Integer.parseInt(cadena[1]));
				}else{
					System.err.println("No puedes ejecutar "+cadena[1]+" instrucciones.");
				}
			//cadena del push
			}else if(cadena[0].equalsIgnoreCase("PUSH")){
				comando = new Push (Integer.parseInt(cadena[1]));
			}
		//proceso los comandos que tengan 2 operandos
		} else if (cadena.length == 3 && CommandParser.validarOperando(cadena[1]) && CommandParser.validarOperando(cadena[2])) {
			if (cadena[0].equalsIgnoreCase("WRITE")){
				comando = new Write (Integer.parseInt(cadena[1]), Integer.parseInt(cadena[2])); 
			}
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
