package modelo;

public class Parking {
	private int numeroParking;
	private boolean ocupado;
	private String matricula;
	private String dniHuesped;
	
	public Parking() {}

	public Parking(int numeroParking, boolean ocupado, String matricula, String dniHuesped) {
		super();
		this.numeroParking = numeroParking;
		this.ocupado = ocupado;
		this.matricula = matricula;
		this.dniHuesped = dniHuesped;
	}

	public int getNumeroParking() {
		return numeroParking;
	}

	public void setNumeroParking(int numeroParking) {
		this.numeroParking = numeroParking;
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getDniHuesped() {
		return dniHuesped;
	}

	public void setDniHuesped(String dniHuesped) {
		this.dniHuesped = dniHuesped;
	}

	@Override
	public String toString() {
		return "Parking [numeroParking=" + numeroParking + ", ocupado=" + ocupado + ", matricula=" + matricula
				+ ", dniHuesped=" + dniHuesped + "]";
	}
	
	

}
