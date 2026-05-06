import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PetArquivoService {
    public void salvar(Pet pet){
        String nomeFormatado = pet.getNomeCompleto().replaceAll("\\s+", "").toUpperCase();
        LocalDateTime data = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        String dataHora = data.format(formatter);
        String nomeArquivo = dataHora + "-" + nomeFormatado + ".TXT";

        File pasta = new File("petsCadastrados");
        if(!pasta.exists()){
            pasta.mkdir();
        }

        File file = new File("petsCadastrados/" + nomeArquivo);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            bw.write("1 - " +  pet.getNomeCompleto());
            bw.newLine();
            bw.write("2 - " +  pet.getTipoFormatado());
            bw.newLine();
            bw.write("3 - " +  pet.getSexoFormatado());
            bw.newLine();
            bw.write("4 - " +  pet.getRua()+", "+pet.getNumeroCasa()+", "+pet.getCidade());
            bw.newLine();
            bw.write("5 - " +  pet.getIdadeFormatada());
            bw.newLine();
            bw.write("6 - " +  pet.getPesoFormatado());
            bw.newLine();
            bw.write("7 - " +  pet.getRaca());


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
