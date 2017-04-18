package co.edu.ucatolica.sd.imageTreatment.model;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceRMIFilters extends Remote {

	public void getFile(byte[] input) throws RemoteException;
	
	public byte[] grayScale(byte[] imagen) throws RemoteException;

	public byte[] filterRed(byte[] imagen) throws RemoteException;

	public byte[] filterGreen(byte[] imagen) throws RemoteException;

	public byte[] filterBlue(byte[] imagen) throws RemoteException;

	public byte[] filterBright(byte[] imagen) throws RemoteException;
	
	public byte[] filterDark(byte[] imagen) throws RemoteException;

}
