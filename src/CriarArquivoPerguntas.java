import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CriarArquivoPerguntas {
    public static void main(String[] args) {
        File file = new File("formulario.txt");
        try(FileWriter fw = new FileWriter(file); BufferedWriter bw = new BufferedWriter(fw);) {
            bw.write("1 - Qual o nome e sobrenome do pet?");
            bw.newLine();
            bw.write("2 - Qual o tipo do pet (Cachorro/Gato)?");
            bw.newLine();
            bw.write("3 - Qual o sexo do animal?");
            bw.newLine();
            bw.write("4 - Qual endereço e bairro que ele foi encontrado?");
            bw.newLine();
            bw.write("5 - Qual a idade aproximada do pet?");
            bw.newLine();
            bw.write("6 - Qual o peso aproximado do pet?");
            bw.newLine();
            bw.write("7 - Qual a raça do pet?");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
