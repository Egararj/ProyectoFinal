package modelo;

import java.time.LocalDate;

import excepciones.CampoVacioException;
import excepciones.DniException;
import excepciones.FechaException;
import excepciones.NumeroException;
import utilidades.CompruebaDni;
import utilidades.CompruebaFecha;

public class Huesped {
	private String nombre;
	private String apellidos;
	private String dniHuesped;
	private int numeroGrupo;
	private String matricula;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private int numeroHabitacion;
	
	public Huesped() {}

	public Huesped(String nombre, String apellidos, String dniHuesped, String numeroGrupo, String matricula,
			String fechaEntrada, String fechaSalida, String numeroHabitacion) throws CampoVacioException, DniException, FechaException, NumeroException {
		super();
		this.setNombre(nombre);
		this.setApellidos(apellidos);
		this.setDniHuesped(dniHuesped);
		this.setNumeroGrupo(numeroGrupo);
		this.setMatricula(matricula);
		this.setFechaEntrada(fechaEntrada);
		this.setFechaSalida(fechaSalida);
		this.setNumeroHabitacion(numeroHabitacion);
	}

	public Huesped(String nombre, String apellidos, String dniHuesped, String numeroGrupo, String fechaEntrada,
			String fechaSalida, String numeroHabitacion) throws CampoVacioException, DniException, FechaException, NumeroException {
		super();
		this.setNombre(nombre);
		this.setApellidos(apellidos);
		this.setDniHuesped(dniHuesped);
		this.setNumeroGrupo(numeroGrupo);
		this.setFechaEntrada(fechaEntrada);
		this.setFechaSalida(fechaSalida);
		this.setNumeroHabitacion(numeroHabitacion);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) throws CampoVacioException {
		if(nombre.length() == 0) throw new CampoVacioException();
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) throws CampoVacioException {
		if(apellidos.length() == 0) throw new CampoVacioException();
		this.apellidos = apellidos;
	}

	public String getDniHuesped() {
		return dniHuesped;
	}

	public void setDniHuesped(String dniHuesped) throws CampoVacioException, DniException {
		if(dniHuesped.length() == 0) throw new CampoVacioException();
		boolean correcto = CompruebaDni.CompruebaDni(dniHuesped);
		this.dniHuesped = dniHuesped;
	}

	public int getNumeroGrupo() {
		return numeroGrupo;
	}

	public void setNumeroGrupo(String numeroGrupo) throws NumeroException {
		int numeroReal;
		try {
			numeroReal = Integer.parseInt(numeroGrupo);
		}catch (Exception e) {
			throw new NumeroException();
		}
		this.numeroGrupo = numeroReal;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) throws CampoVacioException {
		if(matricula.length() == 0) throw new CampoVacioException();
		this.matricula = matricula;
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(String fechaEntrada) throws FechaException {
		LocalDate fechaReal = CompruebaFecha.CompruebaFecha(fechaEntrada);
		this.fechaEntrada = fechaReal;
	}

	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(String fechaSalida) throws FechaException {
		LocalDate fechaReal = CompruebaFecha.CompruebaFecha(fechaSalida);
		this.fechaSalida = fechaReal;
	}

	public int getNumeroHabitacion() {
		return numeroHabitacion;
	}

	public void setNumeroHabitacion(String numeroHabitacion) throws NumeroException {
		int numeroReal;
		try {
			numeroReal = Integer.parseInt(numeroHabitacion);
		}catch (Exception e) {
			throw new NumeroException();
		}
		this.numeroHabitacion = numeroReal;
	}

	@Override
	public String toString() {
		return "Huesped [nombre=" + nombre + ", apellidos=" + apellidos + ", dniHuesped=" + dniHuesped
				+ ", numeroGrupo=" + numeroGrupo + ", matricula=" + matricula + ", fechaEntrada=" + fechaEntrada
				+ ", fechaSalida=" + fechaSalida + ", numeroHabitacion=" + numeroHabitacion + "]";
	}
	
	

}
