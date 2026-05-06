    import java.io.*;
    import java.util.ArrayList;
    import java.util.List;

    public class BuscarPet {
        public List<File> buscar(String tipoEscolhido,String campo,String criterio){
            File pasta = new File("petsCadastrados");
            File[] arquivos = pasta.listFiles((dir, nome) -> nome.toUpperCase().endsWith(".TXT"));
            List<File> resultados = new ArrayList<>();

            if(arquivos == null || arquivos.length == 0){
                System.out.println("Nenhum pet encontrado");
                return resultados;
            }

            boolean encontrou = false;

            for(File arquivo : arquivos){
                String nome = "";
                String tipo = "";
                String sexo = "";
                String endereco = "";
                String idade = "";
                String peso = "";
                String raca = "";

                try(FileReader fr = new FileReader(arquivo);BufferedReader br = new BufferedReader(fr);){
                    String linha;

                    while( (linha = br.readLine()) != null){
                        if(linha.startsWith("1 -")) nome = linha.substring(4);
                        else if(linha.startsWith("2 -")) tipo = linha.substring(4);
                        else if(linha.startsWith("3 -")) sexo = linha.substring(4);
                        else if(linha.startsWith("4 -")) endereco = linha.substring(4);
                        else if(linha.startsWith("5 -")) idade = linha.substring(4);
                        else if(linha.startsWith("6 -")) peso = linha.substring(4);
                        else if(linha.startsWith("7 -")) raca = linha.substring(4);
                    }

                    String valorComparacao = "";
                    if(campo.equalsIgnoreCase("nome")) valorComparacao = nome;
                    else if(campo.equalsIgnoreCase("sexo")) valorComparacao = sexo;
                    else if(campo.equalsIgnoreCase("endereco")) valorComparacao = endereco;
                    else if(campo.equalsIgnoreCase("idade")) valorComparacao = idade;
                    else if(campo.equalsIgnoreCase("peso")) valorComparacao = peso;
                    else if(campo.equalsIgnoreCase("raca")) valorComparacao = raca;

                    if(campo.equalsIgnoreCase("data")){
                        String dataArquivo = arquivo.getName().split("-")[0];

                        if(tipo.equalsIgnoreCase(tipoEscolhido) && dataArquivo.startsWith(criterio)){
                            encontrou = true;


                            String nomeExibicao = nome;
                            String racaExibicao = raca;
                            String enderecoExibicao = endereco;

                            if (campo.equalsIgnoreCase("nome")) {
                                nomeExibicao = destacarTermo(nome, criterio);
                            } else if (campo.equalsIgnoreCase("raca")) {
                                racaExibicao = destacarTermo(raca, criterio);
                            } else if (campo.equalsIgnoreCase("endereco")) {
                                enderecoExibicao = destacarTermo(endereco, criterio);
                            }
                            int numeroResultado = resultados.size() + 1;

                            System.out.println(numeroResultado + ". " + nomeExibicao + " - " + tipo + " - " + sexo + " - " + enderecoExibicao + " - " + idade + " - " + peso + " - " + racaExibicao);}
                            resultados.add(arquivo);
                    }else {
                        if (tipo.equalsIgnoreCase(tipoEscolhido) && valorComparacao.toLowerCase().contains(criterio.toLowerCase())) {
                            encontrou = true;
                            int numeroResultado = resultados.size() + 1;
                            System.out.println(numeroResultado + ". "+nome + " - " + tipo + " - " + sexo + " - " + endereco + " - " + idade + " - " + peso + " - " + raca);
                            resultados.add(arquivo);
                        }
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (!encontrou) {
                System.out.println("Nenhum pet encontrado com esse critério.");
            }
            return resultados;
        }


        public String destacarTermo(String texto, String termo){
            String textoLower = texto.toLowerCase();
            String termoLower = termo.toLowerCase();

            int inicio = textoLower.indexOf(termoLower);

            if(inicio == -1){
                return texto;
            }
            String antes = texto.substring(0,inicio);
            String meio = texto.substring(inicio,inicio + termo.length());
            String depois = texto.substring(inicio + termo.length());
            return antes + "**" + meio + "**" + depois;
        }


    }
