package agendafamiliar.dal.entity;

import java.util.Objects;

public class Calendario {

    public static final String TABLE = "calendarios";
    public static final String CALENDARIO_ID = "calendario_id";
    public static final String USUARIO_ID = "usuario_id";
    public static final String NOME = "nome";

    private Long calendario_id;
    private Long usuario_id;
    private String nome;

    public Long getCalendario_id() {
        return calendario_id;
    }

    public void setCalendario_id(Long calendario_id) {
        this.calendario_id = calendario_id;
    }

    public Long getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Long usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calendario that = (Calendario) o;
        return Objects.equals(calendario_id, that.calendario_id) &&
                Objects.equals(usuario_id, that.usuario_id) &&
                Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(calendario_id, usuario_id, nome);
    }

    @Override
    public String toString() {
        return "Calendario{" +
                "calendario_id=" + calendario_id +
                ", usuario_id=" + usuario_id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
