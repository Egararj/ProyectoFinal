package interfaces;

import java.sql.SQLException;
import java.util.List;

import excepciones.CampoVacioException;
import excepciones.DniException;
import excepciones.FechaException;
import excepciones.NumeroException;
import modelo.Huesped;

public interface IHuesped {

	List<Huesped> obtenerHuesped() throws SQLException, CampoVacioException, DniException, FechaException, NumeroException;
	
	void nuevoHuesped(Huesped huesped) throws SQLException;
	
}
