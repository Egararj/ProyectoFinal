package repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DbConnection;
import modelo.Huesped;
import modelo.Parking;

public class ParkingRepositorio {

	public ParkingRepositorio() {
		// TODO Auto-generated constructor stub
	}

	public List<Parking> obtenerParking() throws SQLException {
		
		String sql = "SELECT * FROM parking";
		List<Parking> parkings = new ArrayList<>();
		
		try(Connection conn = DbConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery()){
			
			while(rs.next()) {
				int numeroParking = rs.getInt("numero_parking");
				boolean ocupado = rs.getBoolean("ocupado");
				String matricula = rs.getString("matricula");
				String dni = rs.getString("dni_dueño");
				
				Parking parking = new Parking(numeroParking, ocupado, matricula, dni);
				parkings.add(parking);
			}
			
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return parkings;
	}

	public void nuevoHuesped(Huesped huesped) throws SQLException {

		String sql = "UPDATE parking SET matricula=?, dni_dueño=?, ocupado=? WHERE numero_parking=?";
		
		try(Connection conn = DbConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql)){
			
			ps.setString(1, huesped.getMatricula());
			ps.setString(2, huesped.getMatricula());
			ps.setBoolean(3, true);
			ps.setInt(4, huesped.getNumeroHabitacion());
			
			ps.executeUpdate();
			
		}
		
	}

}
