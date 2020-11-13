package agendafamiliar.persistence.entity;

public class Data {
    public static final String TABLE = "datas";
    public static final String DATA_ID = "data_id";
    public static final String USUARIO_ID = "usuario_id";
    public static final String CALENDARIO_ID = "calendario_id";
    public static final String DIA = "dia";
    public static final String MES = "mes";
    public static final String ANO = "ano";
    public static final String DESCRICAO = "descricao";

    private Long data_id;
    private Long usuario_id;
    private Long calendario_id;
    private Integer dia;
    private Integer mes;
    private Integer ano;
    private String descricao;

    public Long getData_id() {
        return data_id;
    }

    public void setData_id(Long data_id) {
        this.data_id = data_id;
    }

    public Long getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Long usuario_id) {
        this.usuario_id = usuario_id;
    }

    public Long getCalendario_id() {
        return calendario_id;
    }

    public void setCalendario_id(Long calendario_id) {
        this.calendario_id = calendario_id;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
