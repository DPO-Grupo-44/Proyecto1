package proyecto3;

import java.util.*;


public static void main()

{
	Scanner Input = new.Scanner(System.in)
	
	Calendar InicioCronometro = Calendar.getInstance()
	long HoraInicio = InicioCronometro.getTimeInMillis()
	
	System.Out.println("Se ha iniciado el cronometro. ")
	System.Out.print("Para finalizar el cronometro escriba Y . ")
	String Finalizar = Input.next();
	
	if (Finalizar == 'Y') 
	{
		Calendar FinCronometro = Calendar.getInstance()
		long HoraFinal = InicioCronometro.getTimeInMillis()
	}
	
	long tiempoMillis = HoraFinal - HoraInicio
			
			
	System.Out.println("Ha tardado: "+ tiempoMillis)
			
	
	
}
