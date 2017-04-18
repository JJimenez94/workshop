package co.edu.ucatolica.sd.imageTreatment.model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.imageio.ImageIO;

public class ImplRMIFilters extends UnicastRemoteObject implements InterfaceRMIFilters {

	public ImplRMIFilters() throws RemoteException {
		super();
	}
	
	public void getFile(byte[] input) throws RemoteException {
		System.out.println("Se reciben los bytes de la imagen");
		try {
			System.out.println("Reconstruyendo imagen");
			FileOutputStream fileOutputStream = new FileOutputStream("original.jpg");
			fileOutputStream.write(input);
			fileOutputStream.close();
			System.out.println("imagen almacenada en el servidor exitosamente");
		} catch (Exception e) {
			System.out.println("FileImpl: " + e.getMessage());
			e.printStackTrace();
		}
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

	public byte[] grayScale(byte[] imagen) throws RemoteException {
		System.out.println("Consumiendo escala de grises");
		InputStream in = new ByteArrayInputStream(imagen);
		BufferedImage imageGray;		
		try {
			imageGray = ImageIO.read(in);
			int width = imageGray.getWidth();
			int height = imageGray.getHeight();
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					Color oldColor = new Color(imageGray.getRGB(j, i));
					int red = (int) (oldColor.getRed() * 0.299);
					int green = (int) (oldColor.getGreen() * 0.587);
					int blue = (int) (oldColor.getBlue() * 0.114);
					int sum = (red + green + blue);
					Color newColor = new Color(sum, sum, sum);
					imageGray.setRGB(j, i, newColor.getRGB());
				}
			}
			File ouptut = new File("grayscale.jpg");
			ImageIO.write(imageGray, "jpg", ouptut);
			System.out.println("_______filtro aplicado exitosamente_______");
			return sendFile(ouptut);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public byte[] filterRed(byte[] imagen) throws RemoteException {
		System.out.println("Consumiendo filtro de rojos");
		InputStream in = new ByteArrayInputStream(imagen);
		BufferedImage imageRed;
		try {
			imageRed = ImageIO.read(in);
			int width = imageRed.getWidth();
			int height = imageRed.getHeight();
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					Color oldColor = new Color(imageRed.getRGB(j, i));
					int red = (255);
					int green = (int) (oldColor.getGreen());
					int blue = (int) (oldColor.getBlue());
					Color newColor = new Color(red, green, blue);
					imageRed.setRGB(j, i, newColor.getRGB());
				}
			}
			File ouptut = new File("red.jpg");
			ImageIO.write(imageRed, "jpg", ouptut);
			System.out.println("_______filtro aplicado exitosamente_______");
			return sendFile(ouptut);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public byte[] filterGreen(byte[] imagen) throws RemoteException {
		System.out.println("Consumiendo filtro de verdes");
		InputStream in = new ByteArrayInputStream(imagen);
		BufferedImage imageGreen;
		try {
			imageGreen = ImageIO.read(in);
			int width = imageGreen.getWidth();
			int height = imageGreen.getHeight();
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					Color oldColor = new Color(imageGreen.getRGB(j, i));
					int red = (int) (oldColor.getRed());
					int green = (255);
					int blue = (int) (oldColor.getBlue());
					Color newColor = new Color(red, green, blue);
					imageGreen.setRGB(j, i, newColor.getRGB());
				}
			}
			File ouptut = new File("green.jpg");
			ImageIO.write(imageGreen, "jpg", ouptut);
			System.out.println("_______filtro aplicado exitosamente_______");
			return sendFile(ouptut);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public byte[] filterBlue(byte[] imagen) throws RemoteException {
		System.out.println("Consumiendo filtro de azules");
		InputStream in = new ByteArrayInputStream(imagen);
		BufferedImage imageBlue;
		try {
			imageBlue = ImageIO.read(in);
			int width = imageBlue.getWidth();
			int height = imageBlue.getHeight();
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					Color oldColor = new Color(imageBlue.getRGB(j, i));
					int red = (int) (oldColor.getRed());
					int green = (int) (oldColor.getGreen());
					int blue = (255);
					Color newColor = new Color(red, green, blue);
					imageBlue.setRGB(j, i, newColor.getRGB());
				}
			}
			File ouptut = new File("blue.jpg");
			ImageIO.write(imageBlue, "jpg", ouptut);
			System.out.println("_______filtro aplicado exitosamente_______");
			return sendFile(ouptut);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public byte[] filterBright(byte[] imagen) throws RemoteException {
		System.out.println("Consumiendo aumento de brillo");
		InputStream in = new ByteArrayInputStream(imagen);
		BufferedImage imageBright;
		try {
			imageBright = ImageIO.read(in);
			int width = imageBright.getWidth();
			int height = imageBright.getHeight();
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					Color oldColor = new Color(imageBright.getRGB(j, i));
					Color newColor = oldColor.brighter();
					imageBright.setRGB(j, i, newColor.getRGB());
				}
			}
			File ouptut = new File("bright.jpg");
			ImageIO.write(imageBright, "jpg", ouptut);
			System.out.println("_______filtro aplicado exitosamente_______");
			return sendFile(ouptut);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public byte[] filterDark(byte[] imagen) throws RemoteException {
		System.out.println("Consumiendo disminucion de brillo");
		InputStream in = new ByteArrayInputStream(imagen);
		BufferedImage imageDark;
		try {
			imageDark = ImageIO.read(in);
			int width = imageDark.getWidth();
			int height = imageDark.getHeight();
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					Color oldColor = new Color(imageDark.getRGB(j, i));
					Color newColor = oldColor.darker();
					imageDark.setRGB(j, i, newColor.getRGB());
				}
			}
			File ouptut = new File("dark.jpg");
			ImageIO.write(imageDark, "jpg", ouptut);
			System.out.println("_______filtro aplicado exitosamente_______");
			return sendFile(ouptut);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
