package pr1;

public class Alu {

	/**
	 * Funcíon que realiza la suma de dos operandos
	 * @param operando1
	 * @param operando2
	 * @return resultado
	 */
	public Integer add(Integer operando1, Integer operando2){
		Integer resultado = null;
		
		//si los dos operandos tienen valor, los sumo
		if(operando1!=null && operando2 !=null){
			resultado = operando1.intValue() + operando2.intValue();
		}else{
			System.out.println("No hay operadores para sumar");
		}
		
		return resultado;
	}
	
	/**
	 * Funcíon que realiza la resta de dos operandos
	 * @param operando1
	 * @param operando2
	 * @return resultado
	 */
	public Integer sub(Integer operando1, Integer operando2){
		Integer resultado = null;
		
		//si los dos operandos tienen valor, los sumo
		if(operando1!=null && operando2 !=null){
			resultado = operando1.intValue() - operando2.intValue();
		}else{
			System.out.println("No hay operadores para restar");
		}
		
		return resultado;
	}
	
	/**
	 * Funcíon que realiza la multiplicación de dos operandos
	 * @param operando1
	 * @param operando2
	 * @return resultado
	 */
	public Integer mul(Integer operando1, Integer operando2){
		Integer resultado = null;
		
		//si los dos operandos tienen valor, los sumo
		if(operando1!=null && operando2 !=null){
			resultado = operando1.intValue() * operando2.intValue();
		}else{
			System.out.println("No hay operadores para multiplicar");
		}
		
		return resultado;
	}
	
	/**
	 * Funcíon que realiza la división de dos operandos
	 * @param operando1
	 * @param operando2
	 * @return resultado
	 */
	public Integer div(Integer operando1, Integer operando2){
		Integer resultado = null;
		
		//si los dos operandos tienen valor, los sumo
		if( operando2  != 0  && operando2 != null && operando1 != null ){
			resultado = operando1.intValue() / operando2.intValue(); 
		}else{
			System.out.println("El denominador usado es 0 o no existe");
		}
		
		return resultado;
	}
}
