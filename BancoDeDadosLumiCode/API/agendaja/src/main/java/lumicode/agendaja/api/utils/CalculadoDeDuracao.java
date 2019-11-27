package lumicode.agendaja.api.utils;

public class CalculadoDeDuracao {
	
	
	
	public String calcularDuracao(String data, Integer duracao) {
		
		String[] horario = data.split(":");
		
		String hora = "";
		
		int horas = Integer.parseInt(horario[0]);
		
		int minutos = Integer.parseInt(horario[1]);
		
		String casoZero = "";
		
		int cont = 0;
		
		
		if(duracao>=60) {
			while(duracao>=60) {
				duracao = duracao - 60;
				cont++;
			}
			if(duracao < 60) {
				int soma = minutos + duracao;
				
				if(soma >=60) {
					while(soma>=60) {
						soma = soma -60;
						cont++;
					}
				}
				if(String.valueOf(soma).length() == 1){
					if(soma < 10) {
						casoZero = 0+""+soma;
					}
				
					hora = String.valueOf(horas+cont) + ":" + String.valueOf(casoZero) +":"+"00";

				}else {
					hora = String.valueOf(horas+cont) + ":" + String.valueOf(soma) +":"+"00";

				}
			}
			
		}else if(duracao<60) {
			int soma = duracao + minutos;
			
			if(soma>=60) {
				while(soma>=60) {
					soma = soma-60;
					cont++;
				}
				
				if(soma<60) {
					if(String.valueOf(soma).length() == 1){
						if(soma < 10) {
							casoZero = 0+""+soma;
						}
						hora = String.valueOf(horas+cont) + ":" + String.valueOf(casoZero)+":"+"00";
					}else {
						hora = String.valueOf(horas+cont) + ":" + String.valueOf(soma)+":"+"00";
					}
				
				}
				
			}else if(soma<60) {
				if(String.valueOf(soma).length() == 1){
					if(soma < 10) {
						casoZero = 0+""+soma;
					}
						hora = String.valueOf(horas+cont) + ":" + String.valueOf(casoZero)+":"+"00";
				}else {
						hora = String.valueOf(horas) + ":" + String.valueOf(soma)+":"+"00";
					}
				
				}
			}
		return hora;
			
	}
}