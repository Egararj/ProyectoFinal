package servicio;

import java.sql.SQLException;
import java.util.List;

import interfaces.IParking;
import modelo.Parking;
import repositorio.ParkingRepositorio;

public class ParkingService implements IParking{

	public ParkingService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Parking> obtenerParking() throws SQLException {
		ParkingRepositorio pr = new ParkingRepositorio();
		List<Parking> parkings = pr.obtenerParking();
		return parkings;
	}

}
