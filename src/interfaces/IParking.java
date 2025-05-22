package interfaces;

import java.sql.SQLException;
import java.util.List;

import modelo.Parking;

public interface IParking {
	
	List<Parking> obtenerParking() throws SQLException;
	
	void liberarParking(int puntero) throws SQLException;

}
