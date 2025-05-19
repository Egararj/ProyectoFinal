package servicio;

import java.sql.SQLException;
import java.util.List;

import excepciones.CampoVacioException;
import excepciones.DniException;
import excepciones.FechaException;
import excepciones.NumeroException;
import interfaces.IHuesped;
import modelo.Huesped;
import repositorio.HuespedRepositorio;

public class HuespedService implements IHuesped{

	public HuespedService() {
	}

	@Override
	public List<Huesped> obtenerHuesped() throws SQLException, CampoVacioException, DniException, FechaException, NumeroException {
		HuespedRepositorio hr = new HuespedRepositorio();
		List<Huesped> huespedes = hr.obtenerHuesped();
		return huespedes;
	}

}
