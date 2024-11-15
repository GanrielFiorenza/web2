package Model;

public class ClienteDTO {
    private int id;
    private String nome;
    private int idade;
    private String sexo;
    private String contato;

    public int getId() { return id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public int getIdade() {return idade;}
    public void setIdade(int idade) {this.idade = idade;}
    
    public String getSexo() {return sexo;}
    public void setSexo(String sexo) {this.sexo = sexo;}

    public String getContato() { return contato; }
    public void setContato(String contato) { this.contato = contato; }
}
