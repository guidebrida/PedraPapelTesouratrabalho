import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

class Client {


    private static String msgBemVindo = "Bem vindo ao pedra papel tesoura";

    private static String msgSeuvalor = "\n\nSua escolha ";

    private static String msgEnviadoComSucesso = " foi enviado para o server aguarde o resultado";

    private static String msgRules = "\nRegras:\nPEDRA)pedra\nPAPEL)papel\nTESOURA) para tesoura\nFavor digitar em caixa alta";

    public static void main(String args[]) throws Exception {

        String input = "";
        String response;

        System.out.println(Client.msgBemVindo);

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(
                System.in));
        Socket clientSocket = new Socket("localhost", 9090);
        DataOutputStream outToServer = new DataOutputStream(
                clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(
                clientSocket.getInputStream()));

        do {



            System.out.println(msgRules);
            input = inFromUser.readLine();

        }while (!input.equals("PEDRA") && !input.equals("PAPEL") && !input.equals("TESOURA"));

        outToServer.writeBytes(input + "\n");
        System.out
                .println(msgSeuvalor
                        + input
                        + msgEnviadoComSucesso);

        response = inFromServer.readLine();

        System.out.println("Resposta do server: " + response);

        clientSocket.close();

    }
}
