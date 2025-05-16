package vista.swing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Habitacion;
import modelo.Huesped;
import modelo.Parking;
import servicio.HabitacionService;
import servicio.HuespedService;
import servicio.ParkingService;

import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrmMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal, panelHabitacionIzquierda, panelHuespedIzquierda, panelParkingIzquierda, panelHabitacionDerecha, panelParkingDerecha, panelHuespedDerecha;
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
	private JTable tableHabitacion;
	private JTable tableHuesped;
	private JLayeredPane layeredPanelHuespedNuevo;
	private JPanel panelHuespedNuevoVacio, panelHuespedNuevoGrupo, panelHuespedNuevoHabitacion, panelHuespedNuevoFormulario;
	private JLabel lblHuespedNuevoNumeroGrupo;
	private JTextField textHuespedNuevoNumeroGrupo;
	private JLabel lblHuespedNuevoParking;
	private JCheckBox chcHuespedNuevoParking;
	private JLabel lblHuespedNuevoHabitacion;
	private JTextField textHuespedNuevoNumeroHabitacion;
	private JLabel lblHuespedNuevoNombre, lblHuespedNuevoApellidos, lblHuespedNuevoDni, lblHuespedNuevoFechaEntrada, lblHuespedNuevoFechaSalida, lblHuespedNuevoMatricula;
	private JTextField textHuespedNuevoFechaEntrada, textHuespedNuevoFechaSalida, textHuespedNuevoDni, textHuespedNuevoApellidos, textHuespedNuevoNombre, textHuespedNuevoMatricula;
	
	DefaultTableModel dtm;
	
	HabitacionService HaS = new HabitacionService();
	HuespedService HuS = new HuespedService();
	ParkingService PaS = new ParkingService();
	
	List<Habitacion> habitaciones = new ArrayList<>();
	List<Huesped> huespedes = new ArrayList<>();
	List<Parking> parkings = new ArrayList<>();
	
	int puntero = 0;
	int tamaño;
	
	public FrmMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		definirVentana();
		definirEventos();
		mostrarHabitacion(puntero);
		cargarGridHabitacion();
		
		
		this.setVisible(true);
	}



	private void cargarGridHabitacion() {
		
		habitaciones = HaS.obtenerHabitaciones();
		
		String[] titulos = {"Nº", "Camas", "Camas Dobles", "Ocupada", "Dni Huesped"};
		dtm.setRowCount(0);
		dtm.setColumnCount(0);
		dtm.setColumnIdentifiers(titulos);
		String estadoOcupado = "";
		String dni = "";
		
		for(Habitacion h: habitaciones) {
			
			if(h.isOcupada()) {
				estadoOcupado = "Sí";
				dni = h.getDniHuesped();
			}else {
				estadoOcupado = "No";
				dni = "";
			}
			
			Object[] fila = {h.getNumeroHabitacion(), h.getCamas(), h.getCamasDoble(), estadoOcupado, dni};
			dtm.addRow(fila);
		}

	}



	private void mostrarHabitacion(int puntero) {
		
		habitaciones = HaS.obtenerHabitaciones();
		Habitacion habitacion = habitaciones.get(puntero);
		textHabitacionNumero.setText(String.valueOf(habitacion.getNumeroHabitacion()));
		textHabitacionPiso.setText(String.valueOf(habitacion.getPiso()));
		textHabitacionCamas.setText(String.valueOf(habitacion.getCamas()));
		textHabitacionCamasDobles.setText(String.valueOf(habitacion.getCamasDoble()));
		textHabitacionDni.setText(habitacion.getDniHuesped());
		if(habitacion.isOcupada()) {
			chcHabitacionOcupada.setSelected(true);
		}else {
			chcHabitacionOcupada.setSelected(false);
		}
		
		
	}



	private void definirEventos() {
		
		btnHabitacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				layeredPanelIzquierda.removeAll();
				layeredPanelIzquierda.add(panelHabitacionIzquierda);
				layeredPanelIzquierda.repaint();
				layeredPanelIzquierda.revalidate();
				mostrarHabitacion(puntero = 0);
				
				layeredPanelDerecha.removeAll();
				layeredPanelDerecha.add(panelHabitacionDerecha);
				layeredPanelIzquierda.repaint();
				layeredPanelIzquierda.revalidate();
				
			}
		});
		
		btnHuesped.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				layeredPanelIzquierda.removeAll();
				layeredPanelIzquierda.add(panelHuespedIzquierda);
				layeredPanelIzquierda.repaint();
				layeredPanelIzquierda.revalidate();
				
				layeredPanelDerecha.removeAll();
				layeredPanelDerecha.add(panelHuespedDerecha);
				layeredPanelIzquierda.repaint();
				layeredPanelIzquierda.revalidate();
				
			}
		});
		
		btnParking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				layeredPanelIzquierda.removeAll();
				layeredPanelIzquierda.add(panelParkingIzquierda);
				layeredPanelIzquierda.repaint();
				layeredPanelIzquierda.revalidate();
				
				layeredPanelDerecha.removeAll();
				layeredPanelDerecha.add(panelHuespedDerecha);
				layeredPanelIzquierda.repaint();
				layeredPanelIzquierda.revalidate();
			}
		});
		
		btnHabitacionPrincipio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				puntero = 0;
				mostrarHabitacion(puntero);
			}
		});
		
		btnHabitacionIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(puntero - 1 >= 0) {
					puntero--;
					mostrarHabitacion(puntero);
				}
				
			}
		});
		
		btnHabitacionDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tamaño = habitaciones.size();
				if(puntero + 1 < tamaño) {
					puntero++;
					mostrarHabitacion(puntero);
				}
			}
		});
		
		btnHabitacionFinal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tamaño = habitaciones.size();
				puntero = tamaño-1;
				mostrarHabitacion(puntero);
				
			}
		});
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
		lblHabitacionCamas.setBounds(10, 141, 188, 14);
		panelHabitacionIzquierda.add(lblHabitacionCamas);
		
		lblHabitacionCamasDobles = new JLabel("Número de camas dobles:");
		lblHabitacionCamasDobles.setBounds(10, 166, 188, 14);
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
		textHabitacionNumero.setBounds(208, 116, 86, 20);
		panelHabitacionIzquierda.add(textHabitacionNumero);
		textHabitacionNumero.setColumns(10);
		
		textHabitacionCamas = new JTextField();
		textHabitacionCamas.setBounds(208, 141, 86, 20);
		panelHabitacionIzquierda.add(textHabitacionCamas);
		textHabitacionCamas.setColumns(10);
		
		textHabitacionCamasDobles = new JTextField();
		textHabitacionCamasDobles.setBounds(208, 166, 86, 20);
		panelHabitacionIzquierda.add(textHabitacionCamasDobles);
		textHabitacionCamasDobles.setColumns(10);
		
		textHabitacionPiso = new JTextField();
		textHabitacionPiso.setBounds(208, 194, 86, 20);
		panelHabitacionIzquierda.add(textHabitacionPiso);
		textHabitacionPiso.setColumns(10);
		
		textHabitacionDni = new JTextField();
		textHabitacionDni.setBounds(208, 241, 86, 20);
		panelHabitacionIzquierda.add(textHabitacionDni);
		textHabitacionDni.setColumns(10);
		
		chcHabitacionOcupada = new JCheckBox("Ocupada");
		chcHabitacionOcupada.setBounds(208, 215, 86, 23);
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
		lblHuespedNumeroHabitacion.setBounds(10, 116, 80, 14);
		panelHuespedIzquierda.add(lblHuespedNumeroHabitacion);
		
		lblHuespedNombre = new JLabel("Nombre:");
		lblHuespedNombre.setBounds(10, 141, 63, 14);
		panelHuespedIzquierda.add(lblHuespedNombre);
		
		lblHuespedApellidos = new JLabel("Apellidos:");
		lblHuespedApellidos.setBounds(10, 166, 63, 14);
		panelHuespedIzquierda.add(lblHuespedApellidos);
		
		lblHuespedDni = new JLabel("Dni:");
		lblHuespedDni.setBounds(10, 191, 46, 14);
		panelHuespedIzquierda.add(lblHuespedDni);
		
		lblHuespedFechaEntrada = new JLabel("Fecha Entrada:");
		lblHuespedFechaEntrada.setBounds(10, 216, 105, 14);
		panelHuespedIzquierda.add(lblHuespedFechaEntrada);
		
		lblHuespedFechaSalida = new JLabel("Fecha Salida:");
		lblHuespedFechaSalida.setBounds(10, 241, 105, 14);
		panelHuespedIzquierda.add(lblHuespedFechaSalida);
		
		lblHuespedNumeroGrupo = new JLabel("Nº Grupo:");
		lblHuespedNumeroGrupo.setBounds(10, 266, 80, 14);
		panelHuespedIzquierda.add(lblHuespedNumeroGrupo);
		
		lblHuespedMatricula = new JLabel("Matrícula:");
		lblHuespedMatricula.setBounds(10, 291, 80, 14);
		panelHuespedIzquierda.add(lblHuespedMatricula);
		
		textHuespedFechaEntrada = new JTextField();
		textHuespedFechaEntrada.setBounds(125, 210, 86, 20);
		panelHuespedIzquierda.add(textHuespedFechaEntrada);
		textHuespedFechaEntrada.setColumns(10);
		
		textHuespedFechaSalida = new JTextField();
		textHuespedFechaSalida.setColumns(10);
		textHuespedFechaSalida.setBounds(125, 235, 86, 20);
		panelHuespedIzquierda.add(textHuespedFechaSalida);
		
		textHuespedNumeroHabitacion = new JTextField();
		textHuespedNumeroHabitacion.setColumns(10);
		textHuespedNumeroHabitacion.setBounds(125, 110, 86, 20);
		panelHuespedIzquierda.add(textHuespedNumeroHabitacion);
		
		textHuespedNombre = new JTextField();
		textHuespedNombre.setColumns(10);
		textHuespedNombre.setBounds(125, 135, 130, 20);
		panelHuespedIzquierda.add(textHuespedNombre);
		
		textHuespedDni = new JTextField();
		textHuespedDni.setBounds(125, 185, 86, 20);
		panelHuespedIzquierda.add(textHuespedDni);
		textHuespedDni.setColumns(10);
		
		textHuespedApellidos = new JTextField();
		textHuespedApellidos.setBounds(125, 160, 188, 20);
		panelHuespedIzquierda.add(textHuespedApellidos);
		textHuespedApellidos.setColumns(10);
		
		textHuespedNumeroGrupo = new JTextField();
		textHuespedNumeroGrupo.setBounds(125, 260, 86, 20);
		panelHuespedIzquierda.add(textHuespedNumeroGrupo);
		textHuespedNumeroGrupo.setColumns(10);
		
		textHuespedMatricula = new JTextField();
		textHuespedMatricula.setBounds(125, 285, 86, 20);
		panelHuespedIzquierda.add(textHuespedMatricula);
		textHuespedMatricula.setColumns(10);
		
		layeredPanelHuespedNuevo = new JLayeredPane();
		layeredPanelHuespedNuevo.setBounds(10, 316, 396, 158);
		panelHuespedIzquierda.add(layeredPanelHuespedNuevo);
		layeredPanelHuespedNuevo.setLayout(new CardLayout(0, 0));
		
		panelHuespedNuevoVacio = new JPanel();
		layeredPanelHuespedNuevo.add(panelHuespedNuevoVacio, "name_1215894717375500");
		panelHuespedNuevoVacio.setLayout(null);
		
		panelHuespedNuevoGrupo = new JPanel();
		layeredPanelHuespedNuevo.add(panelHuespedNuevoGrupo, "name_1215941115967600");
		panelHuespedNuevoGrupo.setLayout(null);
		
		lblHuespedNuevoNumeroGrupo = new JLabel("Nº Grupo:");
		lblHuespedNuevoNumeroGrupo.setBounds(10, 46, 67, 14);
		panelHuespedNuevoGrupo.add(lblHuespedNuevoNumeroGrupo);
		
		lblHuespedNuevoParking = new JLabel("Parking:");
		lblHuespedNuevoParking.setBounds(10, 71, 46, 14);
		panelHuespedNuevoGrupo.add(lblHuespedNuevoParking);
		
		textHuespedNuevoNumeroGrupo = new JTextField();
		textHuespedNuevoNumeroGrupo.setBounds(87, 43, 86, 20);
		panelHuespedNuevoGrupo.add(textHuespedNuevoNumeroGrupo);
		textHuespedNuevoNumeroGrupo.setColumns(10);
		
		chcHuespedNuevoParking = new JCheckBox("");
		chcHuespedNuevoParking.setBounds(87, 67, 97, 23);
		panelHuespedNuevoGrupo.add(chcHuespedNuevoParking);
		
		panelHuespedNuevoHabitacion = new JPanel();
		layeredPanelHuespedNuevo.add(panelHuespedNuevoHabitacion, "name_1216037803886300");
		panelHuespedNuevoHabitacion.setLayout(null);
		
		lblHuespedNuevoHabitacion = new JLabel("Nº de la habitación:");
		lblHuespedNuevoHabitacion.setBounds(10, 22, 102, 14);
		panelHuespedNuevoHabitacion.add(lblHuespedNuevoHabitacion);
		
		textHuespedNuevoNumeroHabitacion = new JTextField();
		textHuespedNuevoNumeroHabitacion.setBounds(122, 19, 86, 20);
		panelHuespedNuevoHabitacion.add(textHuespedNuevoNumeroHabitacion);
		textHuespedNuevoNumeroHabitacion.setColumns(10);
		
		panelHuespedNuevoFormulario = new JPanel();
		layeredPanelHuespedNuevo.add(panelHuespedNuevoFormulario, "name_1216169491546700");
		panelHuespedNuevoFormulario.setLayout(null);
		
		lblHuespedNuevoNombre = new JLabel("Nombre:");
		lblHuespedNuevoNombre.setBounds(10, 11, 46, 14);
		panelHuespedNuevoFormulario.add(lblHuespedNuevoNombre);
		
		lblHuespedNuevoApellidos = new JLabel("Apellidos:");
		lblHuespedNuevoApellidos.setBounds(10, 36, 46, 14);
		panelHuespedNuevoFormulario.add(lblHuespedNuevoApellidos);
		
		lblHuespedNuevoDni = new JLabel("Dni:");
		lblHuespedNuevoDni.setBounds(10, 61, 46, 14);
		panelHuespedNuevoFormulario.add(lblHuespedNuevoDni);
		
		lblHuespedNuevoFechaEntrada = new JLabel("Fecha Entrada:");
		lblHuespedNuevoFechaEntrada.setBounds(10, 86, 80, 14);
		panelHuespedNuevoFormulario.add(lblHuespedNuevoFechaEntrada);
		
		lblHuespedNuevoFechaSalida = new JLabel("Fecha Salida:");
		lblHuespedNuevoFechaSalida.setBounds(10, 111, 80, 14);
		panelHuespedNuevoFormulario.add(lblHuespedNuevoFechaSalida);
		
		lblHuespedNuevoMatricula = new JLabel("Matrícula:");
		lblHuespedNuevoMatricula.setBounds(10, 133, 66, 14);
		panelHuespedNuevoFormulario.add(lblHuespedNuevoMatricula);
		
		textHuespedNuevoFechaEntrada = new JTextField();
		textHuespedNuevoFechaEntrada.setBounds(100, 83, 86, 20);
		panelHuespedNuevoFormulario.add(textHuespedNuevoFechaEntrada);
		textHuespedNuevoFechaEntrada.setColumns(10);
		
		textHuespedNuevoFechaSalida = new JTextField();
		textHuespedNuevoFechaSalida.setBounds(100, 108, 86, 20);
		panelHuespedNuevoFormulario.add(textHuespedNuevoFechaSalida);
		textHuespedNuevoFechaSalida.setColumns(10);
		
		textHuespedNuevoDni = new JTextField();
		textHuespedNuevoDni.setBounds(100, 58, 86, 20);
		panelHuespedNuevoFormulario.add(textHuespedNuevoDni);
		textHuespedNuevoDni.setColumns(10);
		
		textHuespedNuevoApellidos = new JTextField();
		textHuespedNuevoApellidos.setBounds(100, 33, 275, 20);
		panelHuespedNuevoFormulario.add(textHuespedNuevoApellidos);
		textHuespedNuevoApellidos.setColumns(10);
		
		textHuespedNuevoNombre = new JTextField();
		textHuespedNuevoNombre.setBounds(100, 8, 135, 20);
		panelHuespedNuevoFormulario.add(textHuespedNuevoNombre);
		textHuespedNuevoNombre.setColumns(10);
		
		textHuespedNuevoMatricula = new JTextField();
		textHuespedNuevoMatricula.setBounds(100, 130, 86, 20);
		panelHuespedNuevoFormulario.add(textHuespedNuevoMatricula);
		textHuespedNuevoMatricula.setColumns(10);
		
		panelParkingIzquierda = new JPanel();
		layeredPanelIzquierda.add(panelParkingIzquierda, "name_1128677725410500");
		panelParkingIzquierda.setLayout(null);
		
		lblParkingNumero = new JLabel("Nº :");
		lblParkingNumero.setBounds(10, 116, 46, 14);
		panelParkingIzquierda.add(lblParkingNumero);
		
		lblParkingOcupado = new JLabel("Ocupado:");
		lblParkingOcupado.setBounds(10, 141, 61, 14);
		panelParkingIzquierda.add(lblParkingOcupado);
		
		lblParkingMatricula = new JLabel("Matrícula:");
		lblParkingMatricula.setBounds(10, 166, 61, 14);
		panelParkingIzquierda.add(lblParkingMatricula);
		
		lblParkingDni = new JLabel("Dni del Huesped:");
		lblParkingDni.setBounds(10, 191, 105, 14);
		panelParkingIzquierda.add(lblParkingDni);
		
		textParkingDni = new JTextField();
		textParkingDni.setBounds(135, 191, 86, 20);
		panelParkingIzquierda.add(textParkingDni);
		textParkingDni.setColumns(10);
		
		textParkingMatricula = new JTextField();
		textParkingMatricula.setBounds(135, 166, 86, 20);
		panelParkingIzquierda.add(textParkingMatricula);
		textParkingMatricula.setColumns(10);
		
		chcParkingOcupado = new JCheckBox("");
		chcParkingOcupado.setBounds(135, 140, 97, 23);
		panelParkingIzquierda.add(chcParkingOcupado);
		
		textParkingNumero = new JTextField();
		textParkingNumero.setBounds(135, 116, 86, 20);
		panelParkingIzquierda.add(textParkingNumero);
		textParkingNumero.setColumns(10);
		
		layeredPanelDerecha = new JLayeredPane();
		layeredPanelDerecha.setBounds(479, 115, 416, 485);
		panelPrincipal.add(layeredPanelDerecha);
		layeredPanelDerecha.setLayout(new CardLayout(0, 0));
		
		panelHabitacionDerecha = new JPanel();
		layeredPanelDerecha.add(panelHabitacionDerecha, "name_1128745753211500");
		panelHabitacionDerecha.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panelHabitacionDerecha.add(scrollPane, BorderLayout.CENTER);
		
		dtm = new DefaultTableModel();
		tableHabitacion = new JTable(dtm);
		tableHabitacion.setEnabled(false);
		scrollPane.setViewportView(tableHabitacion);
		
		panelHuespedDerecha = new JPanel();
		layeredPanelDerecha.add(panelHuespedDerecha, "name_1128763561117600");
		panelHuespedDerecha.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panelHuespedDerecha.add(scrollPane_1, BorderLayout.CENTER);
		
		tableHuesped = new JTable();
		scrollPane_1.setViewportView(tableHuesped);
		
		panelParkingDerecha = new JPanel();
		layeredPanelDerecha.add(panelParkingDerecha, "name_1128781942283400");
	}
}
