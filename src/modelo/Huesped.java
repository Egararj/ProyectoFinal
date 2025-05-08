package modelo;

import java.time.LocalDate;

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

	public Huesped(String nombre, String apellidos, String dniHuesped, int numeroGrupo, String matricula,
			LocalDate fechaEntrada, LocalDate fechaSalida, int numeroHabitacion) {
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

	public Huesped(String nombre, String apellidos, String dniHuesped, int numeroGrupo, LocalDate fechaEntrada,
			LocalDate fechaSalida, int numeroHabitacion) {
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

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDniHuesped() {
		return dniHuesped;
	}

	public void setDniHuesped(String dniHuesped) {
		this.dniHuesped = dniHuesped;
	}

	public int getNumeroGrupo() {
		return numeroGrupo;
	}

	public void setNumeroGrupo(int numeroGrupo) {
		this.numeroGrupo = numeroGrupo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public int getNumeroHabitacion() {
		return numeroHabitacion;
	}

	public void setNumeroHabitacion(int numeroHabitacion) {
		this.numeroHabitacion = numeroHabitacion;
	}

	@Override
	public String toString() {
		return "Huesped [nombre=" + nombre + ", apellidos=" + apellidos + ", dniHuesped=" + dniHuesped
				+ ", numeroGrupo=" + numeroGrupo + ", matricula=" + matricula + ", fechaEntrada=" + fechaEntrada
				+ ", fechaSalida=" + fechaSalida + ", numeroHabitacion=" + numeroHabitacion + "]";
	}
	
	

}
