package servicio;

import java.sql.SQLException;
import java.util.List;

import excepciones.CampoVacioException;
import excepciones.DniException;
import excepciones.FechaException;
import excepciones.NumeroException;
import interfaces.IHuesped;
import modelo.Huesped;
import repositorio.HabitacionRepositorio;
import repositorio.HuespedRepositorio;
import repositorio.ParkingRepositorio;

public class HuespedService implements IHuesped{

	public HuespedService() {
	}

	@Override
	public List<Huesped> obtenerHuesped() throws SQLException, CampoVacioException, DniException, FechaException, NumeroException {
		HuespedRepositorio hr = new HuespedRepositorio();
		List<Huesped> huespedes = hr.obtenerHuesped();
		return huespedes;
	}

	@Override
	public void nuevoHuesped(Huesped huesped) throws SQLException {
		HuespedRepositorio hur = new HuespedRepositorio();
		hur.nuevoHuesped(huesped);
		HabitacionRepositorio har = new HabitacionRepositorio();
		har.nuevoHuesped(huesped);
		
		if(huesped.getMatricula() != null) {
			ParkingRepositorio par = new ParkingRepositorio();
			par.nuevoHuesped(huesped);
		}
	}

}
