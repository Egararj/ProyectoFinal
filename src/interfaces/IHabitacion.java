package interfaces;

import java.sql.SQLException;
import java.util.List;

import modelo.Habitacion;

public interface IHabitacion {
	
	List<Habitacion> obtenerHabitaciones();
	
	void liberarHabitacion(int puntero) throws SQLException;

}
