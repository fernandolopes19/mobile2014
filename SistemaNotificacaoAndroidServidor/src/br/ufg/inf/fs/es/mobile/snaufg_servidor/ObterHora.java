package br.ufg.inf.fs.es.mobile.snaufg_servidor;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ObterHora {
	
	public String obterHora(){
		String hora;
        Calendar atual = GregorianCalendar.getInstance();
        hora = atual.get(GregorianCalendar.YEAR) + "-"
                + obterMes() + "-"
                + atual.get(GregorianCalendar.DATE) + " "
                + atual.get(GregorianCalendar.HOUR_OF_DAY) + ":"
                + atual.get(GregorianCalendar.MINUTE) + ":"
                + atual.get(GregorianCalendar.SECOND);
		
		return hora;
	}
	
	public String obterMes(){
		Calendar atual = GregorianCalendar.getInstance();
		int mes = atual.get(GregorianCalendar.MONTH);
		mes = mes + 1;
		if(mes<10){
			return "0" + mes;
		} else{
			return "" + mes;
		}
	}
	
	public String obterMinuto(){
		Calendar atual = GregorianCalendar.getInstance();
		int minuto = atual.get(GregorianCalendar.MINUTE);
		if(minuto<10){
			return "0" + minuto;
		} else{
			return "" + minuto;
		}
		
	}
	
	
}
