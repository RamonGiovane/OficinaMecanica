package tsi.daw.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import tsi.daw.modelo.Usuario;

public class Util {
	public static Date stringToDate(String data) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return formato.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
			return Calendar.getInstance().getTime();
		}

	}
	
	public static boolean verificarPapel(Usuario usuario, String papel) {
		return papel.equals(usuario.getPapel()) ?  true : false;
		
	}
}
