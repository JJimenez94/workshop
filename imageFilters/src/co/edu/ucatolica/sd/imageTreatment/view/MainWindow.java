package co.edu.ucatolica.sd.imageTreatment.view;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import co.edu.ucatolica.sd.imageTreatment.client.Client;
import co.edu.ucatolica.sd.imageTreatment.controler.MainWindowControler;
import java.awt.EventQueue;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuBar;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private MainWindowControler controler;
	private JButton btnGrayScale, btnRed, btnGreen, btnBlue, btnBright, btnDark, btnChargePicture, btnSaveImg;
	private JLabel lblImagenOriginal, lblImagenProcesada;
	private ChargePicture chargePicture;
	private Client client;

	public JLabel getLblImagenOriginal() {
		return lblImagenOriginal;
	}

	public void setLblImagenOriginal(JLabel lblImagenOriginal) {
		this.lblImagenOriginal = lblImagenOriginal;
	}

	public JLabel getLblImagenProcesada() {
		return lblImagenProcesada;
	}

	public void setLblImagenProcesada(JLabel lblImagenProcesada) {
		this.lblImagenProcesada = lblImagenProcesada;
	}

	public JButton getBtnGrayScale() {
		return btnGrayScale;
	}

	public JButton getBtnRed() {
		return btnRed;
	}

	public JButton getBtnGreen() {
		return btnGreen;
	}

	public JButton getBtnBlue() {
		return btnBlue;
	}

	public JButton getBtnBright() {
		return btnBright;
	}

	public JButton getBtnDark() {
		return btnDark;
	}

	public JButton getBtnChargePicture() {
		return btnChargePicture;
	}

	public JButton getBtnSaveImg() {
		return btnSaveImg;
	}

	public MainWindowControler getControler() throws RemoteException {
		if (controler == null) {
			controler = new MainWindowControler(this);
		}
		return controler;
	}

	public Client getClient() {
		if (client == null) {
			try {
				client = new Client();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public ChargePicture getChargePicture() {
		if (chargePicture == null) {
			chargePicture = new ChargePicture(this);
		}
		return chargePicture;
	}

	public MainWindow() {

		setTitle("Procesamiento de imagenes");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 708, 474);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		btnGrayScale = new JButton("Escala de grises");
		menuBar.add(btnGrayScale);
		btnGrayScale.setToolTipText("boton para aplicar el filtro escala de grises");
		try {
			btnGrayScale.addActionListener(getControler());
		} catch (Exception e) {
			e.printStackTrace();
		}

		btnRed = new JButton("Resaltar rojo");
		btnRed.setToolTipText("boton para resaltar el color rojo de la imagen");
		menuBar.add(btnRed);
		try {
			btnRed.addActionListener(getControler());
		} catch (Exception e) {
			e.printStackTrace();
		}

		btnGreen = new JButton("Resaltar verde");
		btnGreen.setToolTipText("boton para resaltar el color verde de la imagen");
		menuBar.add(btnGreen);
		try {
			btnGreen.addActionListener(getControler());
		} catch (Exception e) {
			e.printStackTrace();
		}

		btnBlue = new JButton("Resaltar azul");
		btnBlue.setToolTipText("boton para resaltar el color azul de la imagen");
		menuBar.add(btnBlue);
		try {
			btnBlue.addActionListener(getControler());
		} catch (Exception e) {
			e.printStackTrace();
		}

		btnBright = new JButton("Aumentar brillo");
		btnBright.setToolTipText("boton para resaltar el brillo de la imagen");
		menuBar.add(btnBright);
		try {
			btnBright.addActionListener(getControler());
		} catch (Exception e) {
			e.printStackTrace();
		}

		btnDark = new JButton("Disminuir brillo");
		btnDark.setToolTipText("boton para disminuir el brillo de la imagen");
		menuBar.add(btnDark);
		try {
			btnDark.addActionListener(getControler());
		} catch (Exception e) {
			e.printStackTrace();
		}

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Border border = LineBorder.createGrayLineBorder();

		lblImagenOriginal = new JLabel("");
		lblImagenOriginal.setToolTipText("Imagen a procesar");
		lblImagenOriginal.setBorder(border);
		lblImagenOriginal.setBounds(10, 11, 336, 360);
		contentPane.add(lblImagenOriginal);

		btnChargePicture = new JButton("Cargar imagen inicial");
		btnChargePicture.setToolTipText("Boton para carga de imagen");
		btnChargePicture.setBounds(10, 379, 336, 23);
		contentPane.add(btnChargePicture);
		try {
			btnChargePicture.addActionListener(getControler());
		} catch (Exception e) {
			e.printStackTrace();
		}

		lblImagenProcesada = new JLabel("");
		lblImagenProcesada.setToolTipText("Imagen procesada");
		lblImagenProcesada.setBorder(border);
		lblImagenProcesada.setBounds(354, 11, 336, 360);
		contentPane.add(lblImagenProcesada);

		btnSaveImg = new JButton("Guardar imagen procesada");
		btnSaveImg.setToolTipText("Boton para guardar imagen procesada");
		btnSaveImg.setBounds(354, 379, 336, 23);
		contentPane.add(btnSaveImg);
		try {
			btnSaveImg.addActionListener(getControler());
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.setResizable(false);
		this.setVisible(true);
	}

}
