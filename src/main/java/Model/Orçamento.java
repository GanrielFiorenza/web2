package Model;

public class Orçamento {
    private int id;
    private String projeto;
    private float valor;
    private int clientId;
    private String status;

    public int getId() { return id; }

    public String getProjeto() { return projeto; }
    public void setProjeto(String projeto) { this.projeto = projeto; }
    
    public float getValor() {return valor;}
    public void setValor(float valor) {this.valor = valor;}
    
    public int getClienteId() {return clientId;}
    public void setClientId(int clientId) {this.clientId = clientId;}

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
