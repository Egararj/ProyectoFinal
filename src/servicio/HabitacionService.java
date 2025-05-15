package servicio;

import java.util.List;

import interfaces.IHabitacion;
import modelo.Habitacion;
import repositorio.HabitacionRepositorio;

public class HabitacionService implements IHabitacion {
	
	HabitacionRepositorio hr;

	public HabitacionService() {

	}

	@Override
	public List<Habitacion> obtenerHabitaciones() {
		hr = new HabitacionRepositorio();
		List<Habitacion> habitaciones = hr.obtenerHabitaciones();
		return habitaciones;
	}

}
