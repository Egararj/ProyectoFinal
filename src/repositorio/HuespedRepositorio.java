package repositorio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import dao.DbConnection;
import excepciones.CampoVacioException;
import excepciones.DniException;
import excepciones.FechaException;
import excepciones.NumeroException;
import modelo.Huesped;

public class HuespedRepositorio {

	public HuespedRepositorio() {
	}

	public List<Huesped> obtenerHuesped() throws SQLException, CampoVacioException, DniException, FechaException, NumeroException {
		
		String sql = "SELECT * FROM huesped";
		List<Huesped> huespedes = new ArrayList<>();
		
		try(Connection conn = DbConnection.getConnection();
			PreparedStatement pr = conn.prepareStatement(sql);
			ResultSet rs = pr.executeQuery()){
			
			while(rs.next()) {
				
				String numeroHabitacion = rs.getString("numero_habitacion").toString();
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				String dni = rs.getString("dni");
				String numeroGrupo = rs.getString("numero_grupo").toString();
				String matricula = rs.getString("matricula");
				String fechaEntrada = rs.getDate("fecha_entrada").toString();
				String fechaSalida = rs.getDate("fecha_salida").toString();
				
				if (matricula == null) {
					Huesped huesped = new Huesped(nombre, apellidos, dni, numeroGrupo, fechaEntrada, fechaSalida, numeroHabitacion);
					huespedes.add(huesped);
				}else {
					Huesped huesped = new Huesped(nombre, apellidos, dni, numeroGrupo, matricula, fechaEntrada, fechaSalida, numeroHabitacion);
					huespedes.add(huesped);
				}		
			}			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return huespedes;
	}

	public void nuevoHuesped(Huesped huesped) throws SQLException {
		
		String sql = "INSERT INTO huesped (nombre, apellidos, dni, matricula, fecha_entrada, fecha_salida, numero_grupo, numero_habitacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try(Connection conn = DbConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql)){
			
			ps.setString(1, huesped.getNombre());
			ps.setString(2, huesped.getApellidos());
			ps.setString(3, huesped.getDniHuesped());
			if(huesped.getMatricula() != null) {
				ps.setString(4, huesped.getMatricula());
			}else {
				ps.setString(4, null);
			}
			ps.setDate(5, Date.valueOf(huesped.getFechaEntrada()));
			ps.setDate(6, Date.valueOf(huesped.getFechaSalida()));
			ps.setInt(7, huesped.getNumeroGrupo());
			ps.setInt(8, huesped.getNumeroHabitacion());
			
			ps.executeUpdate();
		}
	}

	public void borrarHuesped(int puntero) throws SQLException {

		String sql = "DELETE FROM huesped WHERE numero_habitacion = ?";
		
		try(Connection conn = DbConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql)){
			
			ps.setInt(1, puntero);
			
			ps.executeUpdate();
		}
	}

	public void editarFecha(LocalDate entrada, LocalDate salida, int puntero) throws SQLException {
		
		String sql = "UPDATE huesped SET fecha_entrada=?, fecha_salida=? WHERE numero_habitacion=?";
		
		try(Connection conn = DbConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql)){
			
			ps.setDate(1, java.sql.Date.valueOf(entrada));
			ps.setDate(2, java.sql.Date.valueOf(salida));
			ps.setInt(3, puntero);
			
			ps.executeUpdate();
			
		}
	}

}