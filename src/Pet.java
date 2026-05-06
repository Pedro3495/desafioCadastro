public class Pet {
    private String nomeCompleto;
    private TipoPet tipo;
    private SexoPet sexo;
    private String numeroCasa;
    private String cidade;
    private String rua;
    private String idade;
    private String peso;
    private String raca;

    public Pet(String nomeCompleto, TipoPet tipo, SexoPet sexo, String numeroCasa, String cidade, String rua, String idade, String peso, String raca) {
        this.nomeCompleto = nomeCompleto;
        this.tipo = tipo;
        this.sexo = sexo;
        this.numeroCasa = numeroCasa;
        this.cidade = cidade;
        this.rua = rua;
        this.idade = idade;
        this.peso = peso;
        this.raca = raca;
    }
    @Override
    public String toString() {
        return "Pet{" +
                "nomeCompleto='" + nomeCompleto + '\'' +
                ", tipo=" + tipo +
                ", sexo=" + sexo +
                ", numeroCasa='" + numeroCasa + '\'' +
                ", cidade='" + cidade + '\'' +
                ", rua='" + rua + '\'' +
                ", idade='" + idade + '\'' +
                ", peso='" + peso + '\'' +
                ", raca='" + raca + '\'' +
                '}';
    }
    public String getNomeCompleto() {
        return nomeCompleto;
    }
    public String getTipo() {
        return tipo.toString();
    }

    public SexoPet getSexo() {
        return sexo;
    }
    public String getNumeroCasa() {
        return numeroCasa;
    }
    public String getCidade() {
        return cidade;
    }
    public String getRua() {
        return rua;
    }
    public String getIdade() {
        return idade;
    }
    public String getPeso() {
        return peso;
    }
    public String getRaca() {
        return raca;
    }

    public String getTipoFormatado() {
        return tipo == TipoPet.CACHORRO ? "Cachorro" : "Gato";
    }

    public String getSexoFormatado() {
        return sexo == SexoPet.MACHO ? "Macho" : "Femea";
    }

    public String getIdadeFormatada() {
        if ("NÃƒO INFORMADO".equals(idade)) {
            return idade;
        }
        return idade + " anos";
    }

    public String getPesoFormatado() {
        if ("NÃƒO INFORMADO".equals(peso)) {
            return peso;
        }
        return peso + "kg";
    }
}
