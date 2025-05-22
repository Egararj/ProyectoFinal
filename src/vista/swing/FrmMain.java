package vista.swing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import excepciones.CampoVacioException;
import excepciones.DniException;
import excepciones.FechaException;
import excepciones.NumeroException;
import modelo.Habitacion;
import modelo.Huesped;
import modelo.Parking;
import servicio.HabitacionService;
import servicio.HuespedService;
import servicio.ParkingService;
import utilidades.CompruebaFecha;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
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
	private JButton btnHuespedPrincipio, btnHuespedIzquierda, btnHuespedDerecha, btnHuespedFinal;
	private ImageIcon nuevo, editar, borrar, guardar, deshacer, primero, izquierda, derecha, ultimo;
	
	DefaultTableModel dtm;
	
	HabitacionService HaS = new HabitacionService();
	HuespedService HuS = new HuespedService();
	ParkingService PaS = new ParkingService();
	
	List<Habitacion> habitaciones = new ArrayList<>();
	List<Huesped> huespedes = new ArrayList<>();
	List<Parking> parkings = new ArrayList<>();
	List<Habitacion> habitacionesLibres = new ArrayList<>();
	
	String fechaEntradaActual = "";
	String fechaSalidaActual = "";
	String nuevaFecha = "";
	String estado = "";
	Integer nuevoNumeroGrupo;
	Integer nuevaHabitacion;
	LocalDate nuevaFechaEntrada = null;
	LocalDate nuevaFechaSalida = null;
	int puntero = 0;
	int tamaño;
	boolean b = true;
	boolean nuevoParking = false;
	
	public FrmMain() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1065, 737);
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
	
	private void cargarGridHuesped() throws SQLException, CampoVacioException, DniException, FechaException, NumeroException {

		huespedes = HuS.obtenerHuesped();
		
		String[] titulos = {"Nº Hab", "Nombre", "Dni", "F.Entrada","F.Salida"};
		dtm.setRowCount(0);
		dtm.setColumnCount(0);
		dtm.setColumnIdentifiers(titulos);
		
		for(Huesped h: huespedes) {
			String entrada = h.getFechaEntrada().toString();
			String salida = h.getFechaSalida().toString();
			Object[] fila = {h.getNumeroHabitacion(), h.getNombre(), h.getDniHuesped(), entrada, salida};
			dtm.addRow(fila);
		}
		
	}
	
	private boolean cargarHabitacionesDisponibles(int nuevoNumeroGrupo){
		
		boolean sinHabitaciones = true;
		habitaciones = HaS.obtenerHabitaciones();
		
		String[] titulos = {"Nº Hab", "Camas", "Camas Dobles", "Max Huesped","Piso"};
		dtm.setRowCount(0);
		dtm.setColumnCount(0);
		dtm.setColumnIdentifiers(titulos);
		
		for(Habitacion h: habitaciones) {
			int camas = h.getCamas();
			int camasDobles = h.getCamasDoble()*2;
			int maxTotal = camas + camasDobles;
			
			if(!h.isOcupada() && maxTotal >= nuevoNumeroGrupo) {
			Object[] fila = {h.getNumeroHabitacion(), h.getCamas(), h.getCamasDoble(), maxTotal, h.getPiso()};
			dtm.addRow(fila);
			sinHabitaciones = false;
			habitacionesLibres.add(h);
			}
		}
		
		return sinHabitaciones;
	}



	private void mostrarHabitacion(int puntero) {
		
		habitaciones = null;
		habitaciones = HaS.obtenerHabitaciones();
		Habitacion habitacion = habitaciones.get(puntero);
		if(habitacion.isOcupada()) {
			chcHabitacionOcupada.setSelected(true);
			textHabitacionDni.setText(habitacion.getDniHuesped());
		}else {
			chcHabitacionOcupada.setSelected(false);
			textHabitacionDni.setText("");
		}
		textHabitacionCamas.setText(String.valueOf(habitacion.getCamas()));
		textHabitacionCamasDobles.setText(String.valueOf(habitacion.getCamasDoble()));
		textHabitacionNumero.setText(String.valueOf(habitacion.getNumeroHabitacion()));
		textHabitacionPiso.setText(String.valueOf(habitacion.getPiso()));
		
	}
	
	private void mostrarHuesped(int puntero) throws SQLException, CampoVacioException, DniException, FechaException, NumeroException {
		
		huespedes = null;
		huespedes = HuS.obtenerHuesped();
		Huesped huesped = huespedes.get(puntero);
		textHuespedNumeroHabitacion.setText(String.valueOf(huesped.getNumeroHabitacion()));
		textHuespedNombre.setText(huesped.getNombre());
		textHuespedApellidos.setText(huesped.getApellidos());
		textHuespedDni.setText(huesped.getDniHuesped());
		textHuespedFechaEntrada.setText(huesped.getFechaEntrada().toString());
		textHuespedFechaSalida.setText(huesped.getFechaSalida().toString());
		textHuespedNumeroGrupo.setText(String.valueOf(huesped.getNumeroGrupo()));
		if(huesped.getMatricula() == null) {
			textHuespedMatricula.setText("");
		}else {
			textHuespedMatricula.setText(huesped.getMatricula());
		}
		
	}
	
	private void habilitarBotonesHuesped(boolean b) {
		
		btnHuespedNuevo.setEnabled(b);
		btnHuespedEditar.setEnabled(b);
		btnHuespedBorrar.setEnabled(b);
		btnHuespedConfirmar.setEnabled(!b);
		btnHuespedDeshacer.setEnabled(!b);
		
	}
	
	private void habilitarBotonesNavegador(boolean b) {
		
		btnHuespedPrincipio.setEnabled(b);
		btnHuespedIzquierda.setEnabled(b);
		btnHuespedDerecha.setEnabled(b);
		btnHuespedFinal.setEnabled(b);
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
				cargarGridHabitacion();
				
				nuevoNumeroGrupo = null;
				nuevaHabitacion = null;
				habitacionesLibres.clear();
				
			}
		});
		
		btnHuesped.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				layeredPanelIzquierda.removeAll();
				layeredPanelIzquierda.add(panelHuespedIzquierda);
				layeredPanelIzquierda.repaint();
				layeredPanelIzquierda.revalidate();
				try {
					mostrarHuesped(puntero = 0);
				} catch (SQLException | CampoVacioException | DniException | FechaException | NumeroException e1) {
					e1.printStackTrace();
				}
				
				layeredPanelDerecha.removeAll();
				layeredPanelDerecha.add(panelHuespedDerecha);
				layeredPanelIzquierda.repaint();
				layeredPanelIzquierda.revalidate();
				try {
					cargarGridHuesped();
				} catch (SQLException | CampoVacioException | DniException | FechaException | NumeroException e1) {
					e1.printStackTrace();
				}
				
				habilitarBotonesHuesped(b);
				habilitarBotonesNavegador(b);
				
				nuevoNumeroGrupo = null;
				nuevaHabitacion = null;
				habitacionesLibres.clear();
				
				textHuespedFechaEntrada.setEditable(!b);
				textHuespedFechaSalida.setEditable(!b);
				
			}


			
		});
		
		btnParking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				layeredPanelIzquierda.removeAll();
				layeredPanelIzquierda.add(panelParkingIzquierda);
				layeredPanelIzquierda.repaint();
				layeredPanelIzquierda.revalidate();
				
				layeredPanelDerecha.removeAll();
				layeredPanelDerecha.add(panelParkingDerecha);
				layeredPanelDerecha.repaint();
				layeredPanelDerecha.revalidate();
				
				panelParkingDerecha.removeAll();
				panelParkingDerecha.repaint();
				panelParkingDerecha.revalidate();
				
				try {
					cargarParking();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				nuevoNumeroGrupo = null;
				nuevaHabitacion = null;
				habitacionesLibres.clear();
			}

			private void cargarParking() throws SQLException {
				parkings = null;
				parkings = PaS.obtenerParking();
				
				puntero = 0;
				Parking parking;
				for (int i = 1; i <= 20; i++) {
					parking = parkings.get(puntero);
					puntero++;
					JButton parkingBoton = new JButton("Parking "+i);
					if(parking.isOcupado()) {
						parkingBoton.setBackground(Color.RED);
					}else {
						parkingBoton.setBackground(Color.GREEN);
					}
		            parkingBoton.addActionListener(new ActionListener() {
		                public void actionPerformed(ActionEvent e) {
		                	puntero = Integer.parseInt(parkingBoton.getText().substring(8))-1;
							Parking parking = parkings.get(puntero);
		                	textParkingMatricula.setText(parking.getMatricula());
		                	textParkingNumero.setText(String.valueOf(parking.getNumeroParking()));
		                	textParkingDni.setText(parking.getDniHuesped());
		                	if(parking.isOcupado()) {
		                		chcParkingOcupado.setSelected(true);
		                	}else{
		                		chcParkingOcupado.setSelected(false);
		                	}
		                	
		                }
		            });
		            panelParkingDerecha.add(parkingBoton);
				}
				
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
		
		btnHuespedPrincipio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				puntero = 0;
				try {
					mostrarHuesped(puntero);
				} catch (SQLException | CampoVacioException | DniException | FechaException | NumeroException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnHuespedIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(puntero - 1 >= 0) {
					puntero--;
					try {
						mostrarHuesped(puntero);
					} catch (SQLException | CampoVacioException | DniException | FechaException | NumeroException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		btnHuespedDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tamaño = huespedes.size();
				if(puntero + 1 < tamaño) {
					puntero++;
					try {
						mostrarHuesped(puntero);
					} catch (SQLException | CampoVacioException | DniException | FechaException | NumeroException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		btnHuespedFinal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tamaño = huespedes.size();
				puntero = tamaño-1;
				try {
					mostrarHuesped(puntero);
				} catch (SQLException | CampoVacioException | DniException | FechaException | NumeroException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnHuespedNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				habilitarBotonesHuesped(!b);
				estado = "nuevo";
				
				layeredPanelHuespedNuevo.removeAll();
				layeredPanelHuespedNuevo.add(panelHuespedNuevoGrupo);
				layeredPanelHuespedNuevo.repaint();
				layeredPanelHuespedNuevo.revalidate();
				
				
			}
		});
		
		btnHuespedConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switch (estado){
				
				case "nuevo":

					try {
						 nuevoNumeroGrupo = Integer.parseInt(textHuespedNuevoNumeroGrupo.getText());
						 if (nuevoNumeroGrupo <= 0) {
							 throw new Exception();
					}
					}catch(Exception e1) {
					     JOptionPane.showMessageDialog(null, "El valor no puede ser menor que 0");
					     break;
					}
					
					nuevoParking = chcHuespedNuevoParking.isSelected();
					
					boolean sinHabitaciones = cargarHabitacionesDisponibles(nuevoNumeroGrupo);
					
					if(sinHabitaciones) {
					     JOptionPane.showMessageDialog(null, "No hay ninguna habitación para este número de grupo");
					     break;
					}
					
					layeredPanelHuespedNuevo.removeAll();
					layeredPanelHuespedNuevo.add(panelHuespedNuevoHabitacion);
					layeredPanelHuespedNuevo.repaint();
					layeredPanelHuespedNuevo.revalidate();
					estado = "habitacion";
					
					break;
				
				case "habitacion":
					
					try {
					nuevaHabitacion = Integer.parseInt(textHuespedNuevoNumeroHabitacion.getText());
					}catch(Exception e1) {
					     JOptionPane.showMessageDialog(null, "Numero de habitacón incorrecto");
					     nuevaHabitacion = null;
					     break;
					}
					boolean existe = false;
					for(Habitacion h: habitacionesLibres) {
						if(h.getNumeroHabitacion() == nuevaHabitacion) existe = true;
					}
					if(!existe) {
						JOptionPane.showMessageDialog(null, "Esta habitación no está libre");
						nuevaHabitacion = null;
						break;
					}
						
						habitacionesLibres.clear();
						layeredPanelHuespedNuevo.removeAll();
						layeredPanelHuespedNuevo.add(panelHuespedNuevoFormulario);
						layeredPanelHuespedNuevo.repaint();
						layeredPanelHuespedNuevo.revalidate();
						
						if(chcHuespedNuevoParking.isSelected()) 
							textHuespedNuevoMatricula.setEditable(b);
						else {
							textHuespedNuevoMatricula.setEditable(!b);
						}
						
						estado = "formulario";
					
					break;
					
				case "formulario":
					
					if(nuevoParking) {
						try {
							Huesped huesped = new Huesped(textHuespedNuevoNombre.getText(), textHuespedNuevoApellidos.getText(),
									textHuespedNuevoDni.getText(), nuevoNumeroGrupo.toString() , textHuespedNuevoMatricula.getText(),
									textHuespedNuevoFechaEntrada.getText(), textHuespedNuevoFechaSalida.getText(), nuevaHabitacion.toString());
							HuS.nuevoHuesped(huesped);
						} catch (CampoVacioException | DniException | FechaException | NumeroException | SQLException e1) {
							JOptionPane.showMessageDialog(null, "Error en los datos");
							break;
						}
					}else {
						try {
							Huesped huesped = new Huesped(textHuespedNuevoNombre.getText(), textHuespedNuevoApellidos.getText(),
									textHuespedNuevoDni.getText(), nuevoNumeroGrupo.toString(),
									textHuespedNuevoFechaEntrada.getText(), textHuespedNuevoFechaSalida.getText(), nuevaHabitacion.toString());
							HuS.nuevoHuesped(huesped);
						} catch (CampoVacioException | DniException | FechaException | NumeroException | SQLException e1) {
							JOptionPane.showMessageDialog(null, "Error en los datos");
							break;
						}
					}
					JOptionPane.showMessageDialog(null, "Huesped agregado");
					
					habilitarBotonesHuesped(b);
					estado = "nuevo";
					
					
					nuevoNumeroGrupo = null;
					nuevaHabitacion = null;
					habitacionesLibres.clear();
					layeredPanelHuespedNuevo.removeAll();
					layeredPanelHuespedNuevo.add(panelHuespedNuevoVacio);
					layeredPanelHuespedNuevo.repaint();
					layeredPanelHuespedNuevo.revalidate();
					
					btnHuesped.doClick();					 
					break;
					
				case "editar":
					int numeroHabitacion = Integer.parseInt(textHuespedNumeroHabitacion.getText());

					try {
						nuevaFecha = textHuespedFechaEntrada.getText();
						nuevaFechaEntrada = CompruebaFecha.CompruebaFecha(nuevaFecha);
						nuevaFecha = textHuespedFechaSalida.getText();
						nuevaFechaSalida = CompruebaFecha.CompruebaFecha(nuevaFecha);	
						if (nuevaFechaEntrada.isAfter(nuevaFechaSalida)) {
							JOptionPane.showMessageDialog(null, "La fecha de entrada no puede ser mayor que la de salida");
							textHuespedFechaEntrada.setText(fechaEntradaActual);
							textHuespedFechaSalida.setText(fechaSalidaActual);
							break;
						}
					} catch (FechaException e1) {
						JOptionPane.showMessageDialog(null, "Fecha erronea. Los valores deben ser correctos y del fromato YYYY-MM-DD");
						textHuespedFechaEntrada.setText(fechaEntradaActual);
						textHuespedFechaSalida.setText(fechaSalidaActual);
						break;
					}
					
					try {
						HuS.editarFecha(nuevaFechaEntrada, nuevaFechaSalida, numeroHabitacion);
					} catch (SQLException e1) {
						e1.printStackTrace();
						break;
					}
					JOptionPane.showMessageDialog(null, "Fecha cambiada");
					
					btnHuesped.doClick();
					break;
				}
			}


		});
		
		btnHuespedDeshacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switch (estado) {
				
				case "nuevo":
					
					habilitarBotonesHuesped(b);
					try {
						cargarGridHuesped();
					} catch (SQLException | CampoVacioException | DniException | FechaException | NumeroException e1) {
						e1.printStackTrace();
					}
					
					nuevoNumeroGrupo = null;
					nuevaHabitacion = null;
					habitacionesLibres.clear();
					layeredPanelHuespedNuevo.removeAll();
					layeredPanelHuespedNuevo.add(panelHuespedNuevoVacio);
					layeredPanelHuespedNuevo.repaint();
					layeredPanelHuespedNuevo.revalidate();			
					break;
					
				case "habitacion":
					
					nuevoNumeroGrupo = null;
					nuevaHabitacion = null;
					habitacionesLibres.clear();
					layeredPanelHuespedNuevo.removeAll();
					layeredPanelHuespedNuevo.add(panelHuespedNuevoGrupo);
					layeredPanelHuespedNuevo.repaint();
					layeredPanelHuespedNuevo.revalidate();
					estado = "nuevo";
					break;
					
				case "formulario":
					
					layeredPanelHuespedNuevo.removeAll();
					layeredPanelHuespedNuevo.add(panelHuespedNuevoHabitacion);
					layeredPanelHuespedNuevo.repaint();
					layeredPanelHuespedNuevo.revalidate();
					estado = "habitacion";
					
				case "editar":
					
					habilitarBotonesHuesped(b);
					habilitarBotonesNavegador(b);
					try {
						cargarGridHuesped();
					} catch (SQLException | CampoVacioException | DniException | FechaException | NumeroException e1) {
						e1.printStackTrace();
					}
					
					nuevoNumeroGrupo = null;
					nuevaHabitacion = null;
					habitacionesLibres.clear();
					layeredPanelHuespedNuevo.removeAll();
					layeredPanelHuespedNuevo.add(panelHuespedNuevoVacio);
					layeredPanelHuespedNuevo.repaint();
					layeredPanelHuespedNuevo.revalidate();	
					
					textHuespedFechaEntrada.setEditable(!b);
					textHuespedFechaSalida.setEditable(!b);
					break;
					
				}
			}
		});
		
		btnHuespedBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int opcion = JOptionPane.showConfirmDialog(null, "¿Deseas borrar el huesped?", "Confirmación", JOptionPane.YES_NO_OPTION);			
				if(opcion == JOptionPane.YES_OPTION) {
					try {
						String matricula = textHuespedMatricula.getText();
						if (matricula.length() > 0) {
							PaS.liberarParking(puntero+1);
						}
						HaS.liberarHabitacion(puntero+1);
						puntero = Integer.parseInt(textHuespedNumeroHabitacion.getText());
						HuS.borrarHuesped(puntero);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					btnHuesped.doClick();
				}
			}
		});
		
		btnHuespedEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				estado = "editar";
				
				fechaEntradaActual = textHuespedFechaEntrada.getText();
				fechaSalidaActual = textHuespedFechaSalida.getText();
				nuevaFecha = "";
				
				textHuespedFechaEntrada.setEditable(b);
				textHuespedFechaSalida.setEditable(b);
				habilitarBotonesHuesped(!b);
				habilitarBotonesNavegador(!b);
				
			}


		});
		
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}




	private void definirVentana() throws SQLException {
	
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
		btnSalir.setBounds(847, 11, 173, 56);
		panelPrincipal.add(btnSalir);
		
		layeredPanelIzquierda = new JLayeredPane();
		layeredPanelIzquierda.setBounds(23, 115, 496, 542);
		panelPrincipal.add(layeredPanelIzquierda);
		layeredPanelIzquierda.setLayout(new CardLayout(0, 0));
		
		panelHabitacionIzquierda = new JPanel();
		layeredPanelIzquierda.add(panelHabitacionIzquierda, "name_1128073183991500");
		panelHabitacionIzquierda.setLayout(null);
		
		primero = new ImageIcon("imagenes/navPri.jpg");
		btnHabitacionPrincipio = new JButton(primero);
		btnHabitacionPrincipio.setBounds(10, 59, 48, 46);
		panelHabitacionIzquierda.add(btnHabitacionPrincipio);
		
		izquierda = new ImageIcon("imagenes/navIzq.jpg");
		btnHabitacionIzquierda = new JButton(izquierda);
		btnHabitacionIzquierda.setBounds(68, 59, 48, 46);
		panelHabitacionIzquierda.add(btnHabitacionIzquierda);
		
		derecha = new ImageIcon("imagenes/navDer.jpg");
		btnHabitacionDerecha = new JButton(derecha);
		btnHabitacionDerecha.setBounds(126, 59, 48, 46);
		panelHabitacionIzquierda.add(btnHabitacionDerecha);
		
		ultimo = new ImageIcon("imagenes/navUlt.jpg");
		btnHabitacionFinal = new JButton(ultimo);
		btnHabitacionFinal.setBounds(184, 59, 48, 46);
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
		textHabitacionNumero.setEditable(false);
		
		textHabitacionCamas = new JTextField();
		textHabitacionCamas.setBounds(208, 141, 86, 20);
		panelHabitacionIzquierda.add(textHabitacionCamas);
		textHabitacionCamas.setColumns(10);
		textHabitacionCamas.setEditable(false);
		
		textHabitacionCamasDobles = new JTextField();
		textHabitacionCamasDobles.setBounds(208, 166, 86, 20);
		panelHabitacionIzquierda.add(textHabitacionCamasDobles);
		textHabitacionCamasDobles.setColumns(10);
		textHabitacionCamasDobles.setEditable(false);
		
		textHabitacionPiso = new JTextField();
		textHabitacionPiso.setBounds(208, 194, 86, 20);
		panelHabitacionIzquierda.add(textHabitacionPiso);
		textHabitacionPiso.setColumns(10);
		textHabitacionPiso.setEditable(false);
		
		textHabitacionDni = new JTextField();
		textHabitacionDni.setBounds(208, 241, 86, 20);
		panelHabitacionIzquierda.add(textHabitacionDni);
		textHabitacionDni.setColumns(10);
		textHabitacionDni.setEditable(false);
		
		chcHabitacionOcupada = new JCheckBox("Ocupada");
		chcHabitacionOcupada.setBounds(208, 215, 86, 23);
		panelHabitacionIzquierda.add(chcHabitacionOcupada);
		chcHabitacionOcupada.setEnabled(false);
		
		panelHuespedIzquierda = new JPanel();
		layeredPanelIzquierda.add(panelHuespedIzquierda, "name_1128142952090800");
		panelHuespedIzquierda.setLayout(null);
		
		nuevo = new ImageIcon("imagenes/botonAgregar.jpg");
		btnHuespedNuevo = new JButton(nuevo);
		btnHuespedNuevo.setBounds(10, 11, 88, 84);
		panelHuespedIzquierda.add(btnHuespedNuevo);
		
		editar = new ImageIcon("imagenes/botonEditar.jpg");
		btnHuespedEditar = new JButton(editar);
		btnHuespedEditar.setBounds(108, 11, 88, 84);
		panelHuespedIzquierda.add(btnHuespedEditar);
		
		borrar = new ImageIcon("imagenes/borrar.jpg");
		btnHuespedBorrar = new JButton(borrar);
		btnHuespedBorrar.setBounds(202, 11, 88, 84);
		panelHuespedIzquierda.add(btnHuespedBorrar);
		
		guardar = new ImageIcon("imagenes/botonGuardar.jpg");
		btnHuespedConfirmar = new JButton(guardar);
		btnHuespedConfirmar.setBounds(300, 11, 88, 84);
		panelHuespedIzquierda.add(btnHuespedConfirmar);
		
		deshacer = new ImageIcon("imagenes/botonDeshacer.jpg");
		btnHuespedDeshacer = new JButton(deshacer);
		btnHuespedDeshacer.setBounds(398, 12, 88, 83);
		panelHuespedIzquierda.add(btnHuespedDeshacer);
		
		lblHuespedNumeroHabitacion = new JLabel("Nº Habitación:");
		lblHuespedNumeroHabitacion.setBounds(10, 158, 80, 14);
		panelHuespedIzquierda.add(lblHuespedNumeroHabitacion);
		
		lblHuespedNombre = new JLabel("Nombre:");
		lblHuespedNombre.setBounds(10, 183, 63, 14);
		panelHuespedIzquierda.add(lblHuespedNombre);
		
		lblHuespedApellidos = new JLabel("Apellidos:");
		lblHuespedApellidos.setBounds(10, 208, 63, 14);
		panelHuespedIzquierda.add(lblHuespedApellidos);
		
		lblHuespedDni = new JLabel("Dni:");
		lblHuespedDni.setBounds(10, 233, 46, 14);
		panelHuespedIzquierda.add(lblHuespedDni);
		
		lblHuespedFechaEntrada = new JLabel("Fecha Entrada:");
		lblHuespedFechaEntrada.setBounds(10, 258, 105, 14);
		panelHuespedIzquierda.add(lblHuespedFechaEntrada);
		
		lblHuespedFechaSalida = new JLabel("Fecha Salida:");
		lblHuespedFechaSalida.setBounds(10, 283, 105, 14);
		panelHuespedIzquierda.add(lblHuespedFechaSalida);
		
		lblHuespedNumeroGrupo = new JLabel("Nº Grupo:");
		lblHuespedNumeroGrupo.setBounds(10, 308, 80, 14);
		panelHuespedIzquierda.add(lblHuespedNumeroGrupo);
		
		lblHuespedMatricula = new JLabel("Matrícula:");
		lblHuespedMatricula.setBounds(10, 333, 80, 14);
		panelHuespedIzquierda.add(lblHuespedMatricula);
		
		textHuespedFechaEntrada = new JTextField();
		textHuespedFechaEntrada.setBounds(125, 252, 86, 20);
		panelHuespedIzquierda.add(textHuespedFechaEntrada);
		textHuespedFechaEntrada.setColumns(10);
		textHuespedFechaEntrada.setEditable(false);
		
		textHuespedFechaSalida = new JTextField();
		textHuespedFechaSalida.setColumns(10);
		textHuespedFechaSalida.setBounds(125, 277, 86, 20);
		panelHuespedIzquierda.add(textHuespedFechaSalida);
		textHuespedFechaSalida.setEditable(false);
		
		textHuespedNumeroHabitacion = new JTextField();
		textHuespedNumeroHabitacion.setColumns(10);
		textHuespedNumeroHabitacion.setBounds(125, 152, 86, 20);
		panelHuespedIzquierda.add(textHuespedNumeroHabitacion);
		textHuespedNumeroHabitacion.setEditable(false);
		
		textHuespedNombre = new JTextField();
		textHuespedNombre.setColumns(10);
		textHuespedNombre.setBounds(125, 177, 130, 20);
		panelHuespedIzquierda.add(textHuespedNombre);
		textHuespedNombre.setEditable(false);
		
		textHuespedDni = new JTextField();
		textHuespedDni.setBounds(125, 227, 86, 20);
		panelHuespedIzquierda.add(textHuespedDni);
		textHuespedDni.setColumns(10);
		textHuespedDni.setEditable(false);
		
		textHuespedApellidos = new JTextField();
		textHuespedApellidos.setBounds(125, 202, 188, 20);
		panelHuespedIzquierda.add(textHuespedApellidos);
		textHuespedApellidos.setColumns(10);
		textHuespedApellidos.setEditable(false);
		
		textHuespedNumeroGrupo = new JTextField();
		textHuespedNumeroGrupo.setBounds(125, 302, 86, 20);
		panelHuespedIzquierda.add(textHuespedNumeroGrupo);
		textHuespedNumeroGrupo.setColumns(10);
		textHuespedNumeroGrupo.setEditable(false);
		
		textHuespedMatricula = new JTextField();
		textHuespedMatricula.setBounds(125, 327, 86, 20);
		panelHuespedIzquierda.add(textHuespedMatricula);
		textHuespedMatricula.setColumns(10);
		textHuespedMatricula.setEditable(false);
		
		layeredPanelHuespedNuevo = new JLayeredPane();
		layeredPanelHuespedNuevo.setBounds(10, 358, 396, 158);
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
		lblHuespedNuevoParking.setBounds(10, 71, 67, 14);
		panelHuespedNuevoGrupo.add(lblHuespedNuevoParking);
		
		textHuespedNuevoNumeroGrupo = new JTextField();
		textHuespedNuevoNumeroGrupo.setBounds(118, 43, 86, 20);
		panelHuespedNuevoGrupo.add(textHuespedNuevoNumeroGrupo);
		textHuespedNuevoNumeroGrupo.setColumns(10);
		
		chcHuespedNuevoParking = new JCheckBox("");
		chcHuespedNuevoParking.setBounds(118, 62, 97, 23);
		panelHuespedNuevoGrupo.add(chcHuespedNuevoParking);
		
		panelHuespedNuevoHabitacion = new JPanel();
		layeredPanelHuespedNuevo.add(panelHuespedNuevoHabitacion, "name_1216037803886300");
		panelHuespedNuevoHabitacion.setLayout(null);
		
		lblHuespedNuevoHabitacion = new JLabel("Nº de la habitación:");
		lblHuespedNuevoHabitacion.setBounds(10, 22, 122, 14);
		panelHuespedNuevoHabitacion.add(lblHuespedNuevoHabitacion);
		
		textHuespedNuevoNumeroHabitacion = new JTextField();
		textHuespedNuevoNumeroHabitacion.setBounds(158, 19, 86, 20);
		panelHuespedNuevoHabitacion.add(textHuespedNuevoNumeroHabitacion);
		textHuespedNuevoNumeroHabitacion.setColumns(10);
		
		panelHuespedNuevoFormulario = new JPanel();
		layeredPanelHuespedNuevo.add(panelHuespedNuevoFormulario, "name_1216169491546700");
		panelHuespedNuevoFormulario.setLayout(null);
		
		lblHuespedNuevoNombre = new JLabel("Nombre:");
		lblHuespedNuevoNombre.setBounds(10, 11, 69, 14);
		panelHuespedNuevoFormulario.add(lblHuespedNuevoNombre);
		
		lblHuespedNuevoApellidos = new JLabel("Apellidos:");
		lblHuespedNuevoApellidos.setBounds(10, 36, 80, 14);
		panelHuespedNuevoFormulario.add(lblHuespedNuevoApellidos);
		
		lblHuespedNuevoDni = new JLabel("Dni:");
		lblHuespedNuevoDni.setBounds(10, 61, 46, 14);
		panelHuespedNuevoFormulario.add(lblHuespedNuevoDni);
		
		lblHuespedNuevoFechaEntrada = new JLabel("Fecha Entrada:");
		lblHuespedNuevoFechaEntrada.setBounds(10, 86, 99, 14);
		panelHuespedNuevoFormulario.add(lblHuespedNuevoFechaEntrada);
		
		lblHuespedNuevoFechaSalida = new JLabel("Fecha Salida:");
		lblHuespedNuevoFechaSalida.setBounds(10, 111, 80, 14);
		panelHuespedNuevoFormulario.add(lblHuespedNuevoFechaSalida);
		
		lblHuespedNuevoMatricula = new JLabel("Matrícula:");
		lblHuespedNuevoMatricula.setBounds(10, 133, 80, 14);
		panelHuespedNuevoFormulario.add(lblHuespedNuevoMatricula);
		
		textHuespedNuevoFechaEntrada = new JTextField();
		textHuespedNuevoFechaEntrada.setBounds(119, 80, 86, 20);
		panelHuespedNuevoFormulario.add(textHuespedNuevoFechaEntrada);
		textHuespedNuevoFechaEntrada.setColumns(10);
		
		textHuespedNuevoFechaSalida = new JTextField();
		textHuespedNuevoFechaSalida.setBounds(119, 105, 86, 20);
		panelHuespedNuevoFormulario.add(textHuespedNuevoFechaSalida);
		textHuespedNuevoFechaSalida.setColumns(10);
		
		textHuespedNuevoDni = new JTextField();
		textHuespedNuevoDni.setBounds(119, 55, 86, 20);
		panelHuespedNuevoFormulario.add(textHuespedNuevoDni);
		textHuespedNuevoDni.setColumns(10);
		
		textHuespedNuevoApellidos = new JTextField();
		textHuespedNuevoApellidos.setBounds(119, 33, 256, 20);
		panelHuespedNuevoFormulario.add(textHuespedNuevoApellidos);
		textHuespedNuevoApellidos.setColumns(10);
		
		textHuespedNuevoNombre = new JTextField();
		textHuespedNuevoNombre.setBounds(119, 8, 135, 20);
		panelHuespedNuevoFormulario.add(textHuespedNuevoNombre);
		textHuespedNuevoNombre.setColumns(10);
		
		textHuespedNuevoMatricula = new JTextField();
		textHuespedNuevoMatricula.setBounds(119, 127, 86, 20);
		panelHuespedNuevoFormulario.add(textHuespedNuevoMatricula);
		textHuespedNuevoMatricula.setColumns(10);
		
		primero = new ImageIcon("imagenes/navPri.jpg");
		btnHuespedPrincipio = new JButton(primero);
		btnHuespedPrincipio.setBounds(10, 101, 48, 46);
		panelHuespedIzquierda.add(btnHuespedPrincipio);
		
		izquierda = new ImageIcon("imagenes/navIzq.jpg");
		btnHuespedIzquierda = new JButton(izquierda);
		btnHuespedIzquierda.setBounds(68, 101, 48, 46);
		panelHuespedIzquierda.add(btnHuespedIzquierda);
		
		derecha = new ImageIcon("imagenes/navDer.jpg");
		btnHuespedDerecha = new JButton(derecha);
		btnHuespedDerecha.setBounds(126, 101, 48, 46);
		panelHuespedIzquierda.add(btnHuespedDerecha);
		
		ultimo = new ImageIcon("imagenes/navUlt.jpg");
		btnHuespedFinal = new JButton(ultimo);
		btnHuespedFinal.setBounds(184, 101, 48, 46);
		panelHuespedIzquierda.add(btnHuespedFinal);
		
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
		textParkingDni.setEditable(false);
		
		textParkingMatricula = new JTextField();
		textParkingMatricula.setBounds(135, 166, 86, 20);
		panelParkingIzquierda.add(textParkingMatricula);
		textParkingMatricula.setColumns(10);
		textParkingMatricula.setEditable(false);
		
		chcParkingOcupado = new JCheckBox("");
		chcParkingOcupado.setBounds(135, 140, 97, 23);
		panelParkingIzquierda.add(chcParkingOcupado);
		chcParkingOcupado.setEnabled(false);
		
		textParkingNumero = new JTextField();
		textParkingNumero.setBounds(135, 116, 86, 20);
		panelParkingIzquierda.add(textParkingNumero);
		textParkingNumero.setColumns(10);
		textParkingNumero.setEditable(false);
		
		layeredPanelDerecha = new JLayeredPane();
		layeredPanelDerecha.setBounds(568, 115, 452, 542);
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
		

		tableHuesped = new JTable(dtm);
		tableHuesped.setEnabled(false);
		scrollPane_1.setViewportView(tableHuesped);
		
		panelParkingDerecha = new JPanel();
		panelParkingDerecha.setLayout(new GridLayout(5, 4));
		layeredPanelDerecha.add(panelParkingDerecha, "name_1128781942283400");
		
	}
	
}
