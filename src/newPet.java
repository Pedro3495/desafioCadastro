import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Scanner;


public class newPet {
    private static final String NAO_INFORMADO = "NÃO INFORMADO";

    public Pet cadastrarPet(){
        Scanner sc = new Scanner(System.in);
        String nomeCompleto = "";
        TipoPet tipo = null;
        SexoPet sexo = null;
        String numeroCasa = "";
        String cidade = "";
        String rua = "";
        String idade = "";
        String peso = "";
        String raca = "";

        try(FileReader fr = new FileReader("formulario.txt"); BufferedReader br = new BufferedReader(fr)){
            String pergunta;
            int numeroPergunta = 1;

            while((pergunta = br.readLine())!= null){
                System.out.println(pergunta);
                System.out.println("Resposta: ");
                String resposta = sc.nextLine();

                switch(numeroPergunta){
                    case 1:
                        nomeCompleto = validarNomeCompleto(resposta);
                        break;
                    case 2:
                        tipo = converterTipo(resposta);
                        break;
                    case 3:
                        sexo = converterSexo(resposta);
                        break;
                    case 4:
                        System.out.println("Número da casa: ");
                        numeroCasa = validarNumeroCasa(sc.nextLine());
                        System.out.println("Cidade: ");
                        cidade = validarTextoLivre(sc.nextLine());
                        System.out.println("Rua: ");
                        rua = validarTextoLivre(sc.nextLine());
                        break;
                    case 5:
                        idade = validarIdade(resposta);
                        break;
                    case 6:
                        peso = validarPeso(resposta);
                        break;
                    case 7:
                        raca = validarRaca(resposta);
                        break;
                    default:
                        break;
                }
                numeroPergunta++;
                System.out.println();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Erro ao ler o arquivo formulario.txt", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Pet pet = new Pet(nomeCompleto, tipo, sexo, numeroCasa, cidade, rua, idade, peso, raca);
        PetArquivoService petArquivoService = new PetArquivoService();
        petArquivoService.salvar(pet);
        return pet;
        
    }


    private TipoPet converterTipo(String tipo){
        if(tipo == null || tipo.isBlank()){
            throw new IllegalArgumentException("Tipo não informado.");
        }

        String tipoNormalizado = tipo.trim().toLowerCase();
        switch (tipoNormalizado){
            case "cachorro":
                return TipoPet.CACHORRO;
            case "gato":
                return TipoPet.GATO;
            default:
                throw new IllegalArgumentException("Tipo de pet inválido.");
        }
    }

    private SexoPet converterSexo(String sexo){
        if(sexo == null || sexo.isBlank()){
            throw new IllegalArgumentException("Sexo não informado.");
        }

        String sexoNormalizado = sexo.trim().toLowerCase();
        switch (sexoNormalizado){
            case "macho":
                return SexoPet.MACHO;
            case "femea":
                return SexoPet.FEMEA;
            default:
                throw new IllegalArgumentException("Sexo do pet inválido.");
        }
    }

    private String validarNomeCompleto(String nomeCompleto){
        String nome = nomeCompleto.trim();
        if(nome.isBlank()){
            throw new IllegalArgumentException(NAO_INFORMADO);
        }
        if (!nome.matches("^[A-Za-zÀ-ÿ]+(\\s+[A-Za-zÀ-ÿ]+)+$"   )) {
            throw new IllegalArgumentException("Nome deve ter nome e sobrenome, sem números ou caracteres especiais.");
        }
        return nome ;
    }

    private String validarNumeroCasa(String numeroCasa){
        String numeroFormatado = numeroCasa.trim();
        if(numeroCasa.isBlank()){
            throw new IllegalArgumentException(NAO_INFORMADO);
        }
        return numeroFormatado;
    }
    private String validarTextoLivre(String textoLivre){
        String textoFormatado = textoLivre.trim();
        if(textoLivre.isBlank()){
            throw new IllegalArgumentException("Input inválido");
        }
        return textoFormatado;
    }

    private Double lerNumero(String numero){
        String numeroFormatado = numero.trim();

        if(numeroFormatado.isBlank()){
            return null;
        }

        numeroFormatado = numeroFormatado.replace(',', '.');

        if(!numeroFormatado.matches("^\\d+(\\.\\d+)?$")){
            throw new IllegalArgumentException("Valor numérico inválido.");
        }

        return Double.parseDouble(numeroFormatado);
    }
    
    private String validarPeso(String peso){
        Double valor = lerNumero(peso);
        if(valor == null){
            throw new IllegalArgumentException(NAO_INFORMADO);
        }
        if(valor < 0.5 || valor > 60){
            throw new IllegalArgumentException("Peso inválido.");
        }
        return formatarNumero(valor);
    }

    private String validarIdade(String idade){
        Double valor = lerNumero(idade);
        if(valor == null){
            throw new IllegalArgumentException(NAO_INFORMADO);
        }
        if(valor>20){
            throw new IllegalArgumentException("Idade inválida");
        }
        return formatarNumero(valor);
    }
    private String validarRaca(String raca){
        String racaNormalizado = raca.trim();
        if(racaNormalizado.isBlank()){
            throw new IllegalArgumentException(NAO_INFORMADO);
        }
        if(!racaNormalizado.matches("^[A-Za-zÀ-ÿ]+(\\s+[A-Za-zÀ-ÿ]+)*$")){
            throw new IllegalArgumentException("Raça inválida.");
        }
        return racaNormalizado;
    }

    private String formatarNumero(Double valor){
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat decimalFormat = new DecimalFormat("0.##", symbols);
        return decimalFormat.format(valor);
    }
}
