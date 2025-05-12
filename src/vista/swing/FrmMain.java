package vista.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class FrmMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal, panelHabitacionIzquierda, panelHuespedIzquierda, panelPakingIzquierda, panelHabitacionDerecha, panelParkingDerecha, panelHuespedDerecha;
	private JButton btnHabitacion, btnHuesped, btnParking, btnSalir;
	private JLayeredPane layeredPanelIzquierda, layeredPanelDerecha;
	private JButton btnHabitacionPrincipio, btnHabitacionIzquierda, btnHabitacionDerecha, btnHabitacionFinal;
	private JLabel lblHabitacionNumero, lblHabitacionCamas, lblHabitacionCamasDobles, lblHabitacionPiso, lblHabitacionOcupada, lblHabitacionDni;
	private JTextField textHabitacionNumero, textHabitacionCamas, textHabitacionCamasDobles, textHabitacionPiso, textHabitacionDni;
	private JCheckBox chcHabitacionOcupada;
	private JButton btnHuespedNuevo, btnHuespedEditar, btnHuespedBorrar, btnHuespedConfirmar, btnHuespedDeshacer;
	private JLabel lblHuespedNumeroHabitacion, lblHuespedNombre, lblHuespedApellidos, lblHuespedDni, lblHuespedFechaEntrada, lblHuespedFechaSalida, lblHuespedNumeroGrupo, lblHuespedMatricula;
	private JTextField textHuespedFechaEntrada, textHuespedFechaSalida, textHuespedNumeroHabitacion, textHuespedNombre, textHuespedDni, textHuespedApellidos, textHuespedNumeroGrupo, textHuespedMatricula;
	private JLabel lblParkingNumero, lblParkingOcupado, lblParkingMatricula, lblParkingDni;
	private JTextField textParkingDni, textParkingMatricula, textParkingNumero;
	private JCheckBox chcParkingOcupado;


	
	public FrmMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		
		
		
		definirVentana();
		definirEventos();
		this.setVisible(true);
	}




	private void definirEventos() {
		
	}




	private void definirVentana() {
	
		btnHabitacion = new JButton("Habitacion");
		btnHabitacion.setBounds(23, 11, 173, 56);
		panelPrincipal.add(btnHabitacion);
		
		btnHuesped = new JButton("Huesped");
		btnHuesped.setBounds(206, 11, 173, 56);
		panelPrincipal.add(btnHuesped);
		
		btnParking = new JButton("Parking");
		btnParking.setBounds(389, 11, 173, 56);
		panelPrincipal.add(btnParking);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(722, 11, 173, 56);
		panelPrincipal.add(btnSalir);
		
		layeredPanelIzquierda = new JLayeredPane();
		layeredPanelIzquierda.setBounds(23, 115, 416, 485);
		panelPrincipal.add(layeredPanelIzquierda);
		layeredPanelIzquierda.setLayout(new CardLayout(0, 0));
		
		panelHabitacionIzquierda = new JPanel();
		layeredPanelIzquierda.add(panelHabitacionIzquierda, "name_1128073183991500");
		panelHabitacionIzquierda.setLayout(null);
		
		btnHabitacionPrincipio = new JButton("Principio");
		btnHabitacionPrincipio.setBounds(10, 11, 89, 23);
		panelHabitacionIzquierda.add(btnHabitacionPrincipio);
		
		btnHabitacionIzquierda = new JButton("Izquierda");
		btnHabitacionIzquierda.setBounds(109, 11, 89, 23);
		panelHabitacionIzquierda.add(btnHabitacionIzquierda);
		
		btnHabitacionDerecha = new JButton("Derecha");
		btnHabitacionDerecha.setBounds(208, 11, 89, 23);
		panelHabitacionIzquierda.add(btnHabitacionDerecha);
		
		btnHabitacionFinal = new JButton("Final");
		btnHabitacionFinal.setBounds(307, 11, 89, 23);
		panelHabitacionIzquierda.add(btnHabitacionFinal);
		
		lblHabitacionNumero = new JLabel("Nº :");
		lblHabitacionNumero.setBounds(10, 116, 19, 14);
		panelHabitacionIzquierda.add(lblHabitacionNumero);
		
		lblHabitacionCamas = new JLabel("Número de camas individuales:");
		lblHabitacionCamas.setBounds(10, 141, 147, 14);
		panelHabitacionIzquierda.add(lblHabitacionCamas);
		
		lblHabitacionCamasDobles = new JLabel("Número de camas dobles:");
		lblHabitacionCamasDobles.setBounds(10, 166, 128, 14);
		panelHabitacionIzquierda.add(lblHabitacionCamasDobles);
		
		lblHabitacionPiso = new JLabel("Piso:");
		lblHabitacionPiso.setBounds(10, 191, 28, 14);
		panelHabitacionIzquierda.add(lblHabitacionPiso);
		
		lblHabitacionOcupada = new JLabel("Ocupada:");
		lblHabitacionOcupada.setBounds(10, 216, 63, 14);
		panelHabitacionIzquierda.add(lblHabitacionOcupada);
		
		lblHabitacionDni = new JLabel("Dni del huesped:");
		lblHabitacionDni.setBounds(10, 241, 101, 14);
		panelHabitacionIzquierda.add(lblHabitacionDni);
		
		textHabitacionNumero = new JTextField();
		textHabitacionNumero.setBounds(172, 113, 86, 20);
		panelHabitacionIzquierda.add(textHabitacionNumero);
		textHabitacionNumero.setColumns(10);
		
		textHabitacionCamas = new JTextField();
		textHabitacionCamas.setBounds(172, 138, 86, 20);
		panelHabitacionIzquierda.add(textHabitacionCamas);
		textHabitacionCamas.setColumns(10);
		
		textHabitacionCamasDobles = new JTextField();
		textHabitacionCamasDobles.setBounds(172, 163, 86, 20);
		panelHabitacionIzquierda.add(textHabitacionCamasDobles);
		textHabitacionCamasDobles.setColumns(10);
		
		textHabitacionPiso = new JTextField();
		textHabitacionPiso.setBounds(172, 191, 86, 20);
		panelHabitacionIzquierda.add(textHabitacionPiso);
		textHabitacionPiso.setColumns(10);
		
		textHabitacionDni = new JTextField();
		textHabitacionDni.setBounds(172, 238, 86, 20);
		panelHabitacionIzquierda.add(textHabitacionDni);
		textHabitacionDni.setColumns(10);
		
		chcHabitacionOcupada = new JCheckBox("Ocupada");
		chcHabitacionOcupada.setBounds(172, 212, 86, 23);
		panelHabitacionIzquierda.add(chcHabitacionOcupada);
		
		panelHuespedIzquierda = new JPanel();
		layeredPanelIzquierda.add(panelHuespedIzquierda, "name_1128142952090800");
		panelHuespedIzquierda.setLayout(null);
		
		btnHuespedNuevo = new JButton("Nuevo");
		btnHuespedNuevo.setBounds(10, 11, 63, 23);
		panelHuespedIzquierda.add(btnHuespedNuevo);
		
		btnHuespedEditar = new JButton("Editar");
		btnHuespedEditar.setBounds(83, 11, 63, 23);
		panelHuespedIzquierda.add(btnHuespedEditar);
		
		btnHuespedBorrar = new JButton("Borrar");
		btnHuespedBorrar.setBounds(156, 11, 63, 23);
		panelHuespedIzquierda.add(btnHuespedBorrar);
		
		btnHuespedConfirmar = new JButton("Confirmar");
		btnHuespedConfirmar.setBounds(229, 11, 63, 23);
		panelHuespedIzquierda.add(btnHuespedConfirmar);
		
		btnHuespedDeshacer = new JButton("Deshacer");
		btnHuespedDeshacer.setBounds(302, 11, 63, 23);
		panelHuespedIzquierda.add(btnHuespedDeshacer);
		
		lblHuespedNumeroHabitacion = new JLabel("Nº Habitación:");
		lblHuespedNumeroHabitacion.setBounds(10, 116, 98, 14);
		panelHuespedIzquierda.add(lblHuespedNumeroHabitacion);
		
		lblHuespedNombre = new JLabel("Nombre:");
		lblHuespedNombre.setBounds(10, 141, 46, 14);
		panelHuespedIzquierda.add(lblHuespedNombre);
		
		lblHuespedApellidos = new JLabel("Apellidos:");
		lblHuespedApellidos.setBounds(10, 166, 63, 14);
		panelHuespedIzquierda.add(lblHuespedApellidos);
		
		lblHuespedDni = new JLabel("Dni:");
		lblHuespedDni.setBounds(10, 191, 46, 14);
		panelHuespedIzquierda.add(lblHuespedDni);
		
		lblHuespedFechaEntrada = new JLabel("Fecha Entrada:");
		lblHuespedFechaEntrada.setBounds(10, 216, 80, 14);
		panelHuespedIzquierda.add(lblHuespedFechaEntrada);
		
		lblHuespedFechaSalida = new JLabel("Fecha Salida:");
		lblHuespedFechaSalida.setBounds(10, 241, 80, 14);
		panelHuespedIzquierda.add(lblHuespedFechaSalida);
		
		lblHuespedNumeroGrupo = new JLabel("Nº Grupo:");
		lblHuespedNumeroGrupo.setBounds(10, 266, 80, 14);
		panelHuespedIzquierda.add(lblHuespedNumeroGrupo);
		
		lblHuespedMatricula = new JLabel("Matrícula:");
		lblHuespedMatricula.setBounds(10, 291, 80, 14);
		panelHuespedIzquierda.add(lblHuespedMatricula);
		
		textHuespedFechaEntrada = new JTextField();
		textHuespedFechaEntrada.setBounds(100, 213, 86, 20);
		panelHuespedIzquierda.add(textHuespedFechaEntrada);
		textHuespedFechaEntrada.setColumns(10);
		
		textHuespedFechaSalida = new JTextField();
		textHuespedFechaSalida.setColumns(10);
		textHuespedFechaSalida.setBounds(100, 238, 86, 20);
		panelHuespedIzquierda.add(textHuespedFechaSalida);
		
		textHuespedNumeroHabitacion = new JTextField();
		textHuespedNumeroHabitacion.setColumns(10);
		textHuespedNumeroHabitacion.setBounds(100, 113, 86, 20);
		panelHuespedIzquierda.add(textHuespedNumeroHabitacion);
		
		textHuespedNombre = new JTextField();
		textHuespedNombre.setColumns(10);
		textHuespedNombre.setBounds(100, 138, 130, 20);
		panelHuespedIzquierda.add(textHuespedNombre);
		
		textHuespedDni = new JTextField();
		textHuespedDni.setBounds(100, 188, 86, 20);
		panelHuespedIzquierda.add(textHuespedDni);
		textHuespedDni.setColumns(10);
		
		textHuespedApellidos = new JTextField();
		textHuespedApellidos.setBounds(100, 163, 265, 20);
		panelHuespedIzquierda.add(textHuespedApellidos);
		textHuespedApellidos.setColumns(10);
		
		textHuespedNumeroGrupo = new JTextField();
		textHuespedNumeroGrupo.setBounds(100, 263, 86, 20);
		panelHuespedIzquierda.add(textHuespedNumeroGrupo);
		textHuespedNumeroGrupo.setColumns(10);
		
		textHuespedMatricula = new JTextField();
		textHuespedMatricula.setBounds(100, 288, 86, 20);
		panelHuespedIzquierda.add(textHuespedMatricula);
		textHuespedMatricula.setColumns(10);
		
		panelPakingIzquierda = new JPanel();
		layeredPanelIzquierda.add(panelPakingIzquierda, "name_1128677725410500");
		panelPakingIzquierda.setLayout(null);
		
		lblParkingNumero = new JLabel("Nº :");
		lblParkingNumero.setBounds(10, 116, 46, 14);
		panelPakingIzquierda.add(lblParkingNumero);
		
		lblParkingOcupado = new JLabel("Ocupado:");
		lblParkingOcupado.setBounds(10, 141, 61, 14);
		panelPakingIzquierda.add(lblParkingOcupado);
		
		lblParkingMatricula = new JLabel("Matrícula:");
		lblParkingMatricula.setBounds(10, 166, 61, 14);
		panelPakingIzquierda.add(lblParkingMatricula);
		
		lblParkingDni = new JLabel("Dni del Huesped:");
		lblParkingDni.setBounds(10, 191, 81, 14);
		panelPakingIzquierda.add(lblParkingDni);
		
		textParkingDni = new JTextField();
		textParkingDni.setBounds(101, 188, 86, 20);
		panelPakingIzquierda.add(textParkingDni);
		textParkingDni.setColumns(10);
		
		textParkingMatricula = new JTextField();
		textParkingMatricula.setBounds(101, 163, 86, 20);
		panelPakingIzquierda.add(textParkingMatricula);
		textParkingMatricula.setColumns(10);
		
		chcParkingOcupado = new JCheckBox("");
		chcParkingOcupado.setBounds(101, 137, 97, 23);
		panelPakingIzquierda.add(chcParkingOcupado);
		
		textParkingNumero = new JTextField();
		textParkingNumero.setBounds(101, 113, 86, 20);
		panelPakingIzquierda.add(textParkingNumero);
		textParkingNumero.setColumns(10);
		
		layeredPanelDerecha = new JLayeredPane();
		layeredPanelDerecha.setBounds(479, 115, 416, 485);
		panelPrincipal.add(layeredPanelDerecha);
		layeredPanelDerecha.setLayout(new CardLayout(0, 0));
		
		panelHabitacionDerecha = new JPanel();
		layeredPanelDerecha.add(panelHabitacionDerecha, "name_1128745753211500");
		
		panelHuespedDerecha = new JPanel();
		layeredPanelDerecha.add(panelHuespedDerecha, "name_1128763561117600");
		
		panelParkingDerecha = new JPanel();
		layeredPanelDerecha.add(panelParkingDerecha, "name_1128781942283400");
	}
}
