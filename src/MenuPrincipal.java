import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao = 0;

        while(opcao != 6){
            exibirMenuPrincipal();
            try {
                opcao = Integer.parseInt(sc.nextLine());
                executarOpcao(opcao);
            }catch(NumberFormatException e){
                System.out.println("Entrada inválida. Digite apenas números.");
            }
        }
        sc.close();
    }
    public static void exibirMenuPrincipal(){
        System.out.println("1. Cadastrar um novo pet");
        System.out.println("2. Alterar os dados do pet cadastrado");
        System.out.println("3. Deletar um pet cadastrado");
        System.out.println("4. Listar todos os pets cadastrados");
        System.out.println("5. Listar pets por algum critério");
        System.out.println("6. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static void executarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                newPet cadastro = new newPet();
                Pet pet = cadastro.cadastrarPet();
                System.out.println("Pet cadastrado com sucesso:");
                System.out.println(pet);
                break;

            case 2:
                Scanner sc = new Scanner(System.in);
                System.out.println("Digite o tipo do pet(Cachorro ou gato)");
                String tipo = sc.nextLine();

                System.out.println("Buscar por: nome, raca, sexo, idade, peso, endereco, data");
                String campo = sc.nextLine();

                System.out.println("Digite o critério:");
                String criterio = sc.nextLine();

                BuscarPet buscarPet = new BuscarPet();
                List<File> resultados = buscarPet.buscar(tipo, campo, criterio);
                if (resultados.isEmpty()) {
                    System.out.println("Nenhum pet encontrado.");
                    break;
                }
                System.out.println("Qual pet você deseja alterar: ");
                int numeroEscolhido = Integer.parseInt(sc.nextLine());
                if (numeroEscolhido < 1|| numeroEscolhido > resultados.size()) {
                    System.out.println("Número inválido.");
                    break;
                }
                File arquivoEscolhido =  resultados.get(numeroEscolhido - 1);
                AlterarPet alterarPet = new AlterarPet();
                alterarPet.alterar(arquivoEscolhido);

            case 3:
                System.out.println("Deletar pet...");
                break;

            case 4:
                System.out.println("Listar todos os pets...");
                break;

            case 5:
                System.out.println("Listar pets por critério...");
                break;

            case 6:
                System.out.println("Encerrando o sistema...");
                break;

            default:
                System.out.println("Opção inválida. Digite um número de 1 a 6.");
                break;
        }
    }
}
