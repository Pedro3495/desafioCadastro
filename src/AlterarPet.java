import java.io.*;
import java.util.Scanner;

public class AlterarPet {
    public void alterar(File arquivo) {
        Scanner sc = new Scanner(System.in);
        String nome = "";
        String tipo = "";
        String sexo = "";
        String endereco = "";
        String idade = "";
        String peso = "";
        String raca = "";

        String linha;

        try(FileReader fr = new FileReader(arquivo); BufferedReader br = new BufferedReader(fr)){
            while( (linha = br.readLine()) != null){
                if(linha.startsWith("1 -")) nome = linha.substring(4);
                else if(linha.startsWith("2 -")) tipo = linha.substring(4);
                else if(linha.startsWith("3 -")) sexo = linha.substring(4);
                else if(linha.startsWith("4 -")) endereco = linha.substring(4);
                else if(linha.startsWith("5 -")) idade = linha.substring(4);
                else if(linha.startsWith("6 -")) peso = linha.substring(4);
                else if(linha.startsWith("7 -")) raca = linha.substring(4);
            }
            System.out.println("Dados atuais do pet:");
            System.out.println("1 - " + nome);
            System.out.println("2 - " + tipo);
            System.out.println("3 - " + sexo);
            System.out.println("4 - " + endereco);
            System.out.println("5 - " + idade);
            System.out.println("6 - " + peso);
            System.out.println("7 - " + raca);
            System.out.println();

            System.out.println("Novos dados");
            System.out.println("Digite o nome do pet:");
            nome = sc.nextLine();
            System.out.println("Digite o endereço do pet:");
            endereco = sc.nextLine();
            System.out.println("Digite o idade:");
            idade = sc.nextLine();
            System.out.println("Digite o peso:");
            peso = sc.nextLine();
            System.out.println("Digite a raca do pet:");
            raca = sc.nextLine();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileWriter fw = new FileWriter(arquivo);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write("1 - " + nome);
            bw.newLine();
            bw.write("2 - " + tipo);
            bw.newLine();
            bw.write("3 - " + sexo);
            bw.newLine();
            bw.write("4 - " + endereco);
            bw.newLine();
            bw.write("5 - " + idade);
            bw.newLine();
            bw.write("6 - " + peso);
            bw.newLine();
            bw.write("7 - " + raca);

            System.out.println("Pet alterado com sucesso.");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
