package repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DbConnection;
import modelo.Habitacion;

public class HabitacionRepositorio {

	public List<Habitacion> obtenerHabitaciones() {
		
		String sql = "SELECT * FROM habitacion";
		List<Habitacion> habitaciones = new ArrayList<>();
		
        try (Connection conn = DbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

        	while(rs.next()) {
            	int numeroHabitacion = rs.getInt("numero_habitacion");
            	int camas = rs.getInt("camas");
            	int camasDobles = rs.getInt("camas_dobles");
            	int piso = rs.getInt("piso");
            	String dni = rs.getString("dni");
            	boolean ocupado = rs.getBoolean("ocupada");
            	
            	Habitacion habitacion = new Habitacion(numeroHabitacion, camas, camasDobles, piso, ocupado, dni);
            	habitaciones.add(habitacion);
        	}
            }catch(Exception e){
                System.out.println(e.getMessage());
            }		
		
		
		return habitaciones;
	}

}