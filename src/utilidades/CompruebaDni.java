package utilidades;

import excepciones.DniException;

public class CompruebaDni {

	public static boolean CompruebaDni(String dni) throws DniException {
		
		boolean correcto = true;
		int numerosDni;
		String letraDni;
		String letrasDni = "TRWAGMYFPDXBNJZSQVHLCKE";
		int resto;
		
		try {
			if(dni.length() != 9) throw new DniException();
			numerosDni = Integer.parseInt(dni.substring(0, 8));
			letraDni = dni.substring(8, 9);
			resto = numerosDni%23;
			if(!letrasDni.substring(resto, resto+1).equals(letraDni)) throw new DniException();
			
		}catch(Exception e) {
			throw new DniException();
		}
		
		return correcto;
	}

}
