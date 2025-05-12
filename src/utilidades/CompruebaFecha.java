package utilidades;

import java.time.LocalDate;

import excepciones.FechaException;

public class CompruebaFecha {

	public static LocalDate CompruebaFecha(String fechaString) throws FechaException {
		try {
		LocalDate fechaReal = LocalDate.parse(fechaString);
		return fechaReal;
		}catch(Exception e) {
			throw new FechaException();
		}
	}

}
