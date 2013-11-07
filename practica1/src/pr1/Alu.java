package pr1;

public class Alu {

	public Alu() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer add(Integer operando1, Integer operando2){
		Integer resultado = null;
		
		if(operando1!=null && operando2 !=null){
			resultado = operando1 + operando2;
		}else{
			System.out.println("No hay operadores para sumar");
		}
		
		return resultado;
	}
	
	public Integer sub(Integer operando1, Integer operando2){
		Integer resultado = null;
		
		if(operando1!=null && operando2 !=null){
			resultado = operando1 - operando2;
		}else{
			System.out.println("No hay operadores para restar");
		}
		
		return resultado;
	}
	
	public Integer div(Integer operando1, Integer operando2){
		Integer resultado = null;
		
		if( operando2  != 0  || operando2 != null ){
			resultado = operando1 / operando2; 
		}else{
			System.out.println("El denominador usado es 0 o no existe");
		}
		
		return resultado;
	}
}
