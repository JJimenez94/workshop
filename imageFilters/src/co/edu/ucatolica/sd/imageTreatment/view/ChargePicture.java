package co.edu.ucatolica.sd.imageTreatment.view;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;

public class ChargePicture extends JFrame {

	private JPanel contentPane;
	private MainWindow mainW;
	private JFileChooser fileChooser;

	public JFileChooser getFileChooser() {
		if (fileChooser == null) {
			fileChooser = new JFileChooser();
			fileChooser.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagenes JPG", "jpg", "jpeg");
			fileChooser.setFileFilter(filter);
			fileChooser.setBounds(0, 0, 623, 394);
		}
		return fileChooser;
	}

	public ChargePicture(MainWindow window) {
		this.mainW = window;
		initComponent();
	}

	/**
	 * Create the frame.
	 */
	public void initComponent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getFileChooser());
	}

}
