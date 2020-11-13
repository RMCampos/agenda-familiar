package agendafamiliar.persistence.entity;


import java.util.Objects;

public class Usuario {
    public final static String TABLE = "usuarios";
    public final static String USUARIOS_ID = "usuarios_id";
    public final static String NOME = "nome";
    public final static String EMAIL = "email";
    public final static String SENHA = "senha";

    private Long usuarios_id;
    private String nome;
    private String email;
    private String senha;


    public Long getUsuarios_id() {
        return usuarios_id;
    }

    public void setUsuarios_id(Long usuarios_id) {
        this.usuarios_id = usuarios_id;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario user = (Usuario) o;
        return Objects.equals(usuarios_id, user.usuarios_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarios_id, nome, email, senha);
    }

    @Override
    public String toString() {
        return "User{" +
                "usuarios_id=" + usuarios_id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
