import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Scanner;

public class AlterarPet {
    private static final String NAO_INFORMADO = "NAO INFORMADO";

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

        try (FileReader fr = new FileReader(arquivo); BufferedReader br = new BufferedReader(fr)) {
            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("1 -")) nome = linha.substring(4);
                else if (linha.startsWith("2 -")) tipo = linha.substring(4);
                else if (linha.startsWith("3 -")) sexo = linha.substring(4);
                else if (linha.startsWith("4 -")) endereco = linha.substring(4);
                else if (linha.startsWith("5 -")) idade = linha.substring(4);
                else if (linha.startsWith("6 -")) peso = linha.substring(4);
                else if (linha.startsWith("7 -")) raca = linha.substring(4);
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

            while (true) {
                try {
                    System.out.println("Digite o nome do pet:");
                    nome = validarNomeCompleto(sc.nextLine());
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            while (true) {
                try {
                    System.out.println("Digite o endereco do pet:");
                    endereco = validarTextoLivre(sc.nextLine());
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            while (true) {
                try {
                    System.out.println("Digite a idade:");
                    idade = validarIdade(sc.nextLine());
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            while (true) {
                try {
                    System.out.println("Digite o peso:");
                    peso = validarPeso(sc.nextLine());
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            while (true) {
                try {
                    System.out.println("Digite a raca do pet:");
                    raca = validarRaca(sc.nextLine());
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

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

    private String validarNomeCompleto(String nomeCompleto) {
        String nome = nomeCompleto.trim();
        if (nome.isBlank()) {
            throw new IllegalArgumentException(NAO_INFORMADO);
        }
        if (!nome.matches("^[A-Za-zÀ-ÿ]+(\\s+[A-Za-zÀ-ÿ]+)+$")) {
            throw new IllegalArgumentException("Nome deve ter nome e sobrenome, sem numeros ou caracteres especiais.");
        }
        return nome;
    }

    private String validarTextoLivre(String textoLivre) {
        String textoFormatado = textoLivre.trim();
        if (textoFormatado.isBlank()) {
            throw new IllegalArgumentException("Input invalido");
        }
        return textoFormatado;
    }

    private Double lerNumero(String numero) {
        String numeroFormatado = numero.trim();

        if (numeroFormatado.isBlank()) {
            return null;
        }

        numeroFormatado = numeroFormatado.replace(',', '.');

        if (!numeroFormatado.matches("^\\d+(\\.\\d+)?$")) {
            throw new IllegalArgumentException("Valor numerico invalido.");
        }

        return Double.parseDouble(numeroFormatado);
    }

    private String validarPeso(String peso) {
        Double valor = lerNumero(peso);
        if (valor == null) {
            throw new IllegalArgumentException(NAO_INFORMADO);
        }
        if (valor < 0.5 || valor > 60) {
            throw new IllegalArgumentException("Peso invalido.");
        }
        return formatarNumero(valor);
    }

    private String validarIdade(String idade) {
        Double valor = lerNumero(idade);
        if (valor == null) {
            throw new IllegalArgumentException(NAO_INFORMADO);
        }
        if (valor > 20) {
            throw new IllegalArgumentException("Idade invalida");
        }
        return formatarNumero(valor);
    }

    private String validarRaca(String raca) {
        String racaNormalizada = raca.trim();
        if (racaNormalizada.isBlank()) {
            throw new IllegalArgumentException(NAO_INFORMADO);
        }
        if (!racaNormalizada.matches("^[A-Za-zÀ-ÿ]+(\\s+[A-Za-zÀ-ÿ]+)*$")) {
            throw new IllegalArgumentException("Raca invalida.");
        }
        return racaNormalizada;
    }

    private String formatarNumero(Double valor) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat decimalFormat = new DecimalFormat("0.##", symbols);
        return decimalFormat.format(valor);
    }
}
