package mv;

public class Alu {

	/**
	 * Metodo que realiza la suma de dos operandos
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
	 * Metodo que realiza la resta de dos operandos
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
	 * Metodo que realiza la multiplicacion de dos operandos
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
	 * Metodo que realiza la division de dos operandos
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
			System.out.println("El denominador usado es 0.");
		}
		
		return resultado;
	}
}
