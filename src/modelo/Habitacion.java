package modelo;

public class Habitacion {
	private int numeroHabitacion;
	private int camas;
	private int camasDoble;
	private int piso;
	private boolean ocupada;
	private String dniHuesped;

	public Habitacion() {}

	public Habitacion(int numeroHabitacion, int camas, int camasDoble, int piso, boolean ocupada, String dniHuesped) {
		super();
		this.numeroHabitacion = numeroHabitacion;
		this.camas = camas;
		this.camasDoble = camasDoble;
		this.piso = piso;
		this.ocupada = ocupada;
		this.dniHuesped = dniHuesped;
	}

	public int getNumeroHabitacion() {
		return numeroHabitacion;
	}

	public void setNumeroHabitacion(int numeroHabitacion) {
		this.numeroHabitacion = numeroHabitacion;
	}

	public int getCamas() {
		return camas;
	}

	public void setCamas(int camas) {
		this.camas = camas;
	}

	public int getCamasDoble() {
		return camasDoble;
	}

	public void setCamasDoble(int camasDoble) {
		this.camasDoble = camasDoble;
	}

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public boolean isOcupada() {
		return ocupada;
	}

	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}

	public String getDniHuesped() {
		return dniHuesped;
	}

	public void setDniHuesped(String dniHuesped) {
		this.dniHuesped = dniHuesped;
	}

	@Override
	public String toString() {
		return "Habitacion [numeroHabitacion=" + numeroHabitacion + ", camas=" + camas + ", camasDoble=" + camasDoble
				+ ", piso=" + piso + ", ocupada=" + ocupada + ", dniHuesped=" + dniHuesped + "]";
	}
	
	

}
