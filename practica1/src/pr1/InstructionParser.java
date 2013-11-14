package pr1;

public class InstructionParser {
	
	/**
	 * Método que interpreta un string y devuelve una instrucción
	 * @param instruccionSinParsear
	 * @return
	 */
	public static Instruction parser(String instruccionSinParsear){
		String operando;
		boolean continuar;
		Instruction miInstruction;
		String [] cadenaInstruccion;
		String literal;
		
		//divido la cadena obtenida en el prompt
		cadenaInstruccion = instruccionSinParsear.split(" +");
		
		//si hay dos elementos en la línea
		if( cadenaInstruccion.length == 2){
			
			//separo las variables
			operando = cadenaInstruccion[1];
			//valido el dato operando
			continuar = validarOperando(operando);
			
		//si hay más de dos elementos, considero la instrucción como erronea
		}else if(cadenaInstruccion.length > 2){
			//esto es un error
			continuar = false;
			operando	= null;
		}else{
			//no valido el operando
			operando	= null;
			continuar 	= true;
			
		}
		
		//si el operando está validado, valido la instruccion
		if(continuar){
			int number = 0;
			
			if(operando!=null){
				number = Integer.parseInt(operando);
			}
			
			//guardo el literal
			literal = cadenaInstruccion[0];
			
			//fuerzo el literal a mayúscula para aceptar cualquier forma
			literal = literal.toUpperCase();
			
			//miro si hay operando o no, para que haya más velocidad de código
			if(operando == null){
				if (literal.equals(TipoInstruction.HALT.toString())){
					miInstruction = new Instruction(TipoInstruction.HALT);
				}else if (literal.equals(TipoInstruction.ADD.toString())){
					miInstruction = new Instruction(TipoInstruction.ADD);
				}else if (literal.equals(TipoInstruction.DIV.toString())){
					miInstruction = new Instruction(TipoInstruction.DIV);
				}else if (literal.equals(TipoInstruction.DUP.toString())){
					miInstruction = new Instruction(TipoInstruction.DUP);
				}else if (literal.equals(TipoInstruction.FLIP.toString())){
					miInstruction = new Instruction(TipoInstruction.FLIP);
				}else if (literal.equals(TipoInstruction.MUL.toString())){
					miInstruction = new Instruction(TipoInstruction.MUL);
				}else if (literal.equals(TipoInstruction.OUT.toString())){
					miInstruction = new Instruction(TipoInstruction.OUT);
				}else if (literal.equals(TipoInstruction.POP.toString())){
					miInstruction = new Instruction(TipoInstruction.POP);
				}else if (literal.equals(TipoInstruction.SUB.toString())){
					miInstruction = new Instruction(TipoInstruction.SUB);
				}else{//si no hay ningúna instruccion identificada, devuelvo null
					miInstruction = null;
				}
			}else{
				if (literal.equals(TipoInstruction.PUSH.toString())){
					miInstruction = new Instruction(TipoInstruction.PUSH,number);
				}else if (literal.equals(TipoInstruction.STORE.toString())){
					miInstruction = new Instruction(TipoInstruction.STORE,number);
				}else if (literal.equals(TipoInstruction.LOAD.toString())){
					miInstruction = new Instruction(TipoInstruction.LOAD,number);
				}else{//si no hay ningúna instruccion identificada, devuelvo null
					System.out.println("Error: La instrucción insertada no existe.\n");
					miInstruction = null;
				}
			}
		}else{
			System.out.println("Error: El operando introducido no es un número.");
			//si el operando no es correcto, la instruccion no es correcta
			miInstruction = null;
		}
		
		return miInstruction;
	}
	
	/**
	 * Método que determina si el operando de la instrucción es un número o no
	 * @param operando
	 * @return boolean
	 */
	private static boolean validarOperando(String operando){
		return operando.matches("[-+]?\\d*\\.?\\d+");
	}

}
