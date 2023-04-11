import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.rmi.registry.LocateRegistry;

public class ServeurMatrice extends UnicastRemoteObject implements ServeurMatrice {

    private HashMap<String,String> listeClients;
    private Random rand;

    public ServeurMatrice() throws RemoteException {
        super();
        listeClients = new HashMap<>();
        rand = new Random();
    }

    public static void main(String[] args) {


        try {
            System.out.println("Server is booting....");

            ServeurMatrice serveur = new ServeurMatrice();
            LocateRegistry.createRegistry(6666);
            String url = "rmi://localhost:6666/serveur"; 
            Naming.rebind(url, serveur);
            System.out.println("Serveur prêt.");
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public boolean authentifierUtilisateur(Utilisateur user) throws RemoteException {
        if (listeClients.containsKey(user.id)) {
            System.out.println(user.id);
            String savedUser = listeClients.get(user.pass);
            if (listeClients.get(user.id).equals(user.pass)) {
                System.out.println("Authentification réussie pour " + user.id);
                System.out.println(user.id);
                return true;
            } 
            else {
                System.out.println("Authentification échouée : mot de passe incorrect pour " + user.id);
                return false;
            }
        }
        else{
            listeClients.put(user.id, user.pass);
            System.out.println("Utilisateur ajouté : " + user.id);
            return true;
        }
}

    public double[][] addition(double[][] matriceA, double[][] matriceB) throws RemoteException {
        int rows = matriceA.length;
        int cols = matriceA[0].length;
        double[][] result = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matriceA[i][j] + matriceB[i][j];
            }
        }
        return result;
    }

    public double[][] multiplication(double[][] matriceA, double[][] matriceB) throws RemoteException {
        int rowsA = matriceA.length;
        int colsA = matriceA[0].length;
        int rowsB = matriceB.length;
        int colsB = matriceB[0].length;
        if (colsA != rowsB) {
            throw new RemoteException(
                    "Impossible de multiplier les matrices : le nombre de colonnes de la matrice A doit être égal au nombre de lignes de la matrice B.");
        }
        double[][] result = new double[rowsA][colsB];
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += matriceA[i][k] * matriceB[k][j];
                }
            }
        }
        return result;
    }

    public double[][] transposer(double[][] matrice) throws RemoteException {
        int rows = matrice.length;
        int cols = matrice[0].length;
        double[][] result = new double[cols][rows];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                result[i][j] = matrice[j][i];
            }
        }
        return result;
    }

    
}