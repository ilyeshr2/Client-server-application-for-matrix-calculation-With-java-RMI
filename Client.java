import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    public static void printLaMatrice(double[][] matrice) {
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                System.out.print(matrice[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        try {
            //connecter au registre RMI

            Registry registry = LocateRegistry.getRegistry("localhost", 4000);

            //Obtenir une référence vers l'objet distant Serveur
            Serveur Serveur = (Serveur) registry.lookup("Serveur");

            //autonotification 
            Scanner scanner = new Scanner(System.in);
            System.out.print("user name : ");
            String userName = scanner.nextLine();
            System.out.print("password : ");
            String password = scanner.nextLine();
            Utilisateur utilisateur = new Utilisateur(userName, password);

            // Appele de la méthode d'authentification
            boolean authentifie = Serveur.authentifierUtilisateur(utilisateur);

            if (!authentifie) {
                System.out.println("mot de passe ou nom d'utilisateur incorrect essey une autre fois");
            }
           else{
            //Afficher les opérations disponibles
            System.out.println("Opérations disponibles :");
            System.out.println("1 -Somme de deux matrices");
            System.out.println("2 -Multiplication de deux matrices");
            System.out.println("3 -Transposée d'une matrice");
            System.out.print("Entrez le numéro de l'opération souhaitée : ");

            //le choix de l'utilisateur
            int choix = scanner.nextInt();

            // Traitement du choix de l'utilisateur
            switch (choix) {
                case 1:
                    //spécification des dimensions des matrices
                    System.out.print("Nombre de lignes de la première matrice : ");
                    int row1 = scanner.nextInt();
                    System.out.print("Nombre de colonnes de la première matrice : ");
                    int col1 = scanner.nextInt();
                    System.out.print("Nombre de lignes de la deuxième matrice : ");
                    int row2 = scanner.nextInt();
                    System.out.print("Nombre de colonnes de la deuxième matrice : ");
                    int col2 = scanner.nextInt();

                    // Vérifier que les matrices sont compatibles pour l'addition
                    if(row1 != row2 || col1 != col2){
                        System.out.println("Les dimensions des matrices incorrect");
                    }
                    else{
                        double[][] matrice1 = new double[row1][col1];
                        double[][] matrice2 = new double[row2][col2];

                        System.out.println("entrez les valeurs de la premiere matrice :");
                          for(int i=0;i<row1;i++){
                           for(int j=0;j<col1;j++){
                            Double valeurs1 = scanner.nextDouble();
                             matrice1[i][j]= valeurs1;

                            }
                           }
                           
                            System.out.println("entrez les valeurs de la deuxieme matrice :");
                            for(int i=0;i<row2;i++){
                             for(int j=0;j<col2;j++){
                              Double valeurs2 = scanner.nextDouble();
                               matrice2[i][j]= valeurs2;
  
                              }
                             }
                              
  
                        double[][] resultat = Serveur.addition(matrice1, matrice2);

                        //Afficher le résultat
                       System.out.println("Résultat de la somme :");
                       printLaMatrice(resultat);

                    }

                    break;
                case 2:
                    //spécification des dimensions des matrices
                    System.out.print("Nombre de lignes de la première matrice : ");
                    row1 = scanner.nextInt();
                    System.out.print("Nombre de colonnes de la première matrice : ");
                    col1 = scanner.nextInt();
                    System.out.print("Nombre de lignes de la deuxième matrice : ");
                    row2 = scanner.nextInt();
                    System.out.print("Nombre de colonnes de la deuxième matrice : ");
                    col2 = scanner.nextInt();

                    // Vérifier que les matrices sont compatibles pour la multiplication
                    if (col1 != row2){
                        System.out.println("Les dimensions des matrices incorrect");
                    } 
                    else{
                        double[][] matrice1 = new double[row1][col1];
                        double[][] matrice2 = new double[row2][col2];

                        System.out.println("entrez les valeurs de la premiere matrice :");
                          for(int i=0;i<row1;i++){
                           for(int j=0;j<col1;j++){
                            Double valeurs1 = scanner.nextDouble();
                             matrice1[i][j]= valeurs1;

                            }
                           }
                            
                            System.out.println("entrez les valeurs de la deuxieme matrice :");
                            for(int i=0;i<row2;i++){
                             for(int j=0;j<col2;j++){
                              Double valeurs1 = scanner.nextDouble();
                               matrice2[i][j]= valeurs1;
  
                              }
                             }
                              
        
                        //Calculer du produit 
                        double[][] resultat = Serveur.multiplication(matrice1, matrice2);
        
                        //Afficher le résultat
                        System.out.println("Résultat de la multiplication :");
                        printLaMatrice(resultat);
        
                    }                  
                break;

            case 3:
                // Demander à l'utilisateur de spécifier les dimensions de la matrice
                System.out.print("Nombre de lignes de la matrice : ");
                row1 = scanner.nextInt();
                System.out.print("Nombre de colonnes de la matrice : ");
                col1 = scanner.nextInt();

                double[][] matrice1 = new double[row1][col1];

                System.out.println("entrez les valeurs de la matrice  :");
                  for(int i=0;i<row1;i++){
                   for(int j=0;j<col1;j++){
                    Double valeurs1 = scanner.nextDouble();
                     matrice1[i][j]= valeurs1;

                    }
                   }
                    
                //Calculer la transposée de la matrice
                double[][] resultat = Serveur.transposer(matrice1);

                // Afficher le résultat
                System.out.println("Résultat de la transposée :");
                printLaMatrice(resultat);
                break;
            default:
                System.out.println("Opération invalide");
                break;
        }
        }
            
    
    } 
    catch (Exception e) {
        System.err.println("Erreur : " + e.getMessage());
        e.printStackTrace();
    }

    }
}
