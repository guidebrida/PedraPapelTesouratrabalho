import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private static String msgBemVindo = "Bem vindo ao pedra papel tesoura";
    private static String msgVenceu = "voce venceu";
    private static String msgPerdeu = "voce perdeu";
    private static String msgEmpate = "voce empatou jogue novamento";

    private static String msgJogadorUmVencedor = "Jogador 1 venceu";

    private static String msgJogadorDoisVencedor = "Jogador 2 venceu";

    private static String msgServerEmpate = "empatou";

    public static void main(String args[]) throws Exception {

        String resClient_1 = "";
        String resClient_2 = "";
        String inputClient_1;
        String inputClient_2;



        System.out.println(Server.msgBemVindo);

        ServerSocket serverSocket = new ServerSocket(9090);
        while (!serverSocket.isClosed()) {

            Socket client_1 = serverSocket.accept();
            if (client_1.isConnected()) {
                System.out.println("\nJogador 1 entrou aguardando jogador 2...");
            }
            DataOutputStream outClient_1 = new DataOutputStream(client_1.getOutputStream());
            BufferedReader inClient_1 = new BufferedReader(new InputStreamReader(client_1.getInputStream()));

            Socket client_2 = serverSocket.accept();
            if (client_2.isConnected()) {
                System.out.println("Jogador 2 entrou...");
            }
            DataOutputStream outClient_2 = new DataOutputStream(client_2.getOutputStream());
            BufferedReader inClient_2 = new BufferedReader(new InputStreamReader(client_2.getInputStream()));

            inputClient_1 = inClient_1.readLine();
            inputClient_2 = inClient_2.readLine();

            if (inputClient_1.equals(inputClient_2)) {
                resClient_1 = msgEmpate;
                resClient_2 = msgEmpate;
                System.out.println(msgServerEmpate);
            }


            else if (inputClient_1.equals("PEDRA") && inputClient_2.equals("TESOURA")) {
                resClient_1 = msgVenceu;
                resClient_2 = msgPerdeu;
                System.out.println(msgJogadorUmVencedor);

            }

            else if (inputClient_1.equals("TESOURA") && inputClient_2.equals("PEDRA")) {
                resClient_1 = msgPerdeu;
                resClient_2 = msgVenceu;
                System.out.println(msgJogadorDoisVencedor);
            }

            else if (inputClient_1.equals("PEDRA") && inputClient_2.equals("PAPEL")) {
                resClient_1 = msgPerdeu;
                resClient_2 = msgVenceu;
                System.out.println(msgJogadorDoisVencedor);
            }

            else if (inputClient_1.equals("PAPEL") && inputClient_2.equals("PEDRA")) {
                resClient_1 = msgVenceu;
                resClient_2 = msgPerdeu;
                System.out.println(msgJogadorUmVencedor);
            }

            else if (inputClient_1.equals("TESOURA") && inputClient_2.equals("PAPEL")) {
                resClient_1 = msgVenceu;
                resClient_2 = msgPerdeu;
                System.out.println(msgJogadorUmVencedor);
            }

            else if (inputClient_1.equals("PAPEL") && inputClient_2.equals("TESOURA")) {
                resClient_1 = msgPerdeu;
                resClient_2 = msgVenceu;
                System.out.println(msgJogadorDoisVencedor);
            }

            outClient_1.writeBytes(resClient_1.toUpperCase());
            outClient_2.writeBytes(resClient_2.toUpperCase());
            client_1.close();
            client_2.close();

            System.out.println("\nWaiting for new players ...\n");

        }
    }
}