package lumicode.agendaja.api.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConverterDatas {
	
	//coveter data para Portugues
	
	// metodo de converção de data para inglesa para portuguesa
	public String dataPt(Date dataEn) {
		
		//SimpleDateFormat formatoPt = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat formatoPt = new SimpleDateFormat("dd/MM/yyyy");
	
		String dataFormatadaPt = formatoPt.format(dataEn);
	
		return dataFormatadaPt;
	}
	
	//metodo de tranforma string para Date
	public Date stringToDatePt(String data) {
		
		DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = null;
		try {
			date = formato.parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return date;	
	}

	
	
	//coverter data para ingles
	
// metodo de converção de data para inglesa para portuguesa
	public String dataEn(Date dataPt) {
		
		//SimpleDateFormat formatoPt = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat formatoEn = new SimpleDateFormat("yyyy-MM-dd");
	
		String dataFormatadaEn = formatoEn.format(dataPt);
	
		return dataFormatadaEn;
	}
	
	//metodo de tranforma string para Date
	public Date stringToDateEn(String data) {
		
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		Date date = null;
		try {
			date = formato.parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return date;	
	}




	
	
	
	
	
	
	
	
}

