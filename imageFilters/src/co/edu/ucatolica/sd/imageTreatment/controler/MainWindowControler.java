package co.edu.ucatolica.sd.imageTreatment.controler;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.rmi.RemoteException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import co.edu.ucatolica.sd.imageTreatment.view.ChargePicture;
import co.edu.ucatolica.sd.imageTreatment.view.MainWindow;

public class MainWindowControler implements ActionListener {

	public MainWindow mainW;
	public ChargePicture chargePicture;
	private File loadedPicture, imgModified;

	public MainWindowControler(MainWindow window) throws RemoteException {
		this.mainW = window;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == mainW.getBtnGrayScale()) {
			try {				
				byte[] buffer;
				buffer = mainW.getClient().impl.grayScale(sendFile(loadedPicture));
				InputStream in = new ByteArrayInputStream(buffer);
				mainW.getLblImagenProcesada().setIcon(resizeToLabel(ImageIO.read(in)));
				mainW.getLblImagenProcesada().repaint();
				saveChanges(buffer, "grayScale");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == mainW.getBtnRed()) {
			try {
				byte[] buffer;
				buffer = mainW.getClient().impl.filterRed(sendFile(loadedPicture));
				InputStream in = new ByteArrayInputStream(buffer);
				mainW.getLblImagenProcesada().setIcon(resizeToLabel(ImageIO.read(in)));
				mainW.getLblImagenProcesada().repaint();
				saveChanges(buffer, "red");
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		} else if (e.getSource() == mainW.getBtnGreen()) {
			try {
				byte[] buffer;
				buffer = mainW.getClient().impl.filterGreen(sendFile(loadedPicture));
				InputStream in = new ByteArrayInputStream(buffer);
				mainW.getLblImagenProcesada().setIcon(resizeToLabel(ImageIO.read(in)));
				mainW.getLblImagenProcesada().repaint();
				saveChanges(buffer, "green");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == mainW.getBtnBlue()) {
			try {
				byte[] buffer;
				buffer = mainW.getClient().impl.filterBlue(sendFile(loadedPicture));
				InputStream in = new ByteArrayInputStream(buffer);
				mainW.getLblImagenProcesada().setIcon(resizeToLabel(ImageIO.read(in)));
				mainW.getLblImagenProcesada().repaint();
				saveChanges(buffer, "blue");
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		} else if (e.getSource() == mainW.getBtnBright()) {
			try {
				byte[] buffer;
				buffer = mainW.getClient().impl.filterBright(sendFile(loadedPicture));
				InputStream in = new ByteArrayInputStream(buffer);
				mainW.getLblImagenProcesada().setIcon(resizeToLabel(ImageIO.read(in)));
				mainW.getLblImagenProcesada().repaint();
				saveChanges(buffer, "bright");
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		} else if (e.getSource() == mainW.getBtnDark()) {
			try {
				byte[] buffer;
				buffer = mainW.getClient().impl.filterDark(sendFile(loadedPicture));
				InputStream in = new ByteArrayInputStream(buffer);
				mainW.getLblImagenProcesada().setIcon(resizeToLabel(ImageIO.read(in)));
				mainW.getLblImagenProcesada().repaint();
				saveChanges(buffer, "dark");
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		} else if (e.getSource() == mainW.getBtnChargePicture()) {
			try {
				File imgOriginal = openImage();
				loadedPicture = imgOriginal;
				ImageIcon img = resizeToLabel(ImageIO.read(imgOriginal));
				mainW.getLblImagenOriginal().setIcon(img);
				mainW.getLblImagenOriginal().repaint();
				byte[] buffer = sendFile(loadedPicture);
				FileOutputStream fileOutputStream = new FileOutputStream("original.jpg");
				fileOutputStream.write(buffer);
				fileOutputStream.close();
				mainW.getClient().impl.getFile(buffer);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == mainW.getBtnSaveImg()) {
			try {
				saveImage();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

	private File openImage() {
		File input = null;
		mainW.getChargePicture().getFileChooser().showOpenDialog(mainW);
		input = mainW.getChargePicture().getFileChooser().getSelectedFile();
		return input;
	}
	
	public byte[] sendFile(File input){
		byte[] array;
		try {			
			array = Files.readAllBytes(input.toPath());			
			return array;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void saveChanges(byte[] input, String name) {
		System.out.println("Se reciben los bytes de la imagen modificada");
		try {
			System.out.println("Reconstruyendo imagen");
			FileOutputStream fileOutputStream = new FileOutputStream(name + ".jpg");
			fileOutputStream.write(input);
			fileOutputStream.close();
			imgModified = new File(name+".jpg");
			System.out.println("imagen almacenada en el cliente exitosamente");
		} catch (Exception e) {
			System.out.println("FileImpl: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void saveImage() {
		try {
			mainW.getChargePicture().getFileChooser().showSaveDialog(mainW);
			File saved = mainW.getChargePicture().getFileChooser().getSelectedFile();
			if (saved != null) {
				String nombre = mainW.getChargePicture().getFileChooser().getSelectedFile().getCanonicalPath();				
				if (!nombre.endsWith(".jpg")) {
	                nombre = (nombre + ".jpg");
	            }
				BufferedImage im = ImageIO.read(imgModified);
				ImageIO.write(im, "jpg", new File(nombre));
				JOptionPane.showMessageDialog(mainW, "El archivo se a guardado Exitosamente", "Información",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(mainW, "Su archivo no se ha guardado", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private ImageIcon resizeToLabel(Image original) {
		Image picture = original.getScaledInstance(mainW.getLblImagenOriginal().getWidth(),
				mainW.getLblImagenOriginal().getHeight(), Image.SCALE_SMOOTH);
		return new ImageIcon(picture);
	}

}
