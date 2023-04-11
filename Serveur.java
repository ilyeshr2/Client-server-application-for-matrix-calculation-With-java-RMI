import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Serveur extends Remote {

    boolean authentifierUtilisateur(Utilisateur utilisateur) throws RemoteException;

    // Méthode pour calculer la somme 
    double[][] addition(double[][] matrice1, double[][] matrice2) throws RemoteException;

    // Méthode pour calculer le produit 
    double[][] multiplication(double[][] matrice1, double[][] matrice2) throws RemoteException;

    // Méthode pour calculer la transposée d'une matrice
    double[][] transposer(double[][] matrice) throws RemoteException;
}
