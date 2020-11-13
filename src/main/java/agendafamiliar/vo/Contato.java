package agendafamiliar.vo;

public class Contato {
    private String nome;
    private String email;
    private String assunto;
    private String mensagem;

    public Contato() {
        this.nome = "";
        this.email = "";
        this.assunto = "";
        this.mensagem = "";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
