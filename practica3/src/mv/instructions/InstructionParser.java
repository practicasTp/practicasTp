package mv.instructions;


public class InstructionParser {
	
	private static Instruction instructionSet[] = {
		new Add(), new And(), new RelativeBf(0), new RelativeBt(0), new RelativeJump(0), new BranchIfFalse(0), 
		new BranchIfTrue(0), new Div(), new Dup(), new Flip(), new GreaterThan(), new Halt(), new LessOrEqual(), new LessThan(), new Load(0), new Mult(), new Neg(),
		new Not(), new Or(), new Out(), new Pop(), new Push(0), new Store(0), new Sub(), new InconditionalJump(0), new Equals(), new JumpInd(), new LoadInd(), new StoreInd()
	};
	
	/**
	 * Método estático que identifica la instrucción parseando un string
	 * @param instruccionSinParsear
	 * @return Instruction
	 */
	public static Instruction parser(String[] instruccionSinParsear){
		int i = 0;
		boolean stop = false;
		boolean operando_validado = true;
		Instruction instruccion_parseada = null;		
		
		if(instruccionSinParsear.length > 1){
			operando_validado = InstructionParser.validarOperando(instruccionSinParsear[1]);
		}
		
		if(operando_validado){
			while (i<InstructionParser.instructionSet.length && !stop){
				instruccion_parseada = InstructionParser.instructionSet[i].parse(instruccionSinParsear);
				if (instruccion_parseada!=null){
					stop = true;
				}else{
					i++;
				}
			}
		}else{
			System.err.println("El operando debe ser un número.");
			instruccion_parseada = null;
		}
		
		return instruccion_parseada;
	}
	
	/**
	 * Metodo que determina si el operando de la instruccion es un numero o no
	 * @param operando
	 * @return boolean
	 */
	private static boolean validarOperando(String operando){
		return operando.matches("[-+]?\\d*\\.?\\d+");
	}

}
