package faculdade.login;

public class Show {
    private Long id;
    private String nome;
    private String local;
    private String data;

    // Construtor padrão necessário para a deserialização JSON
    public Show() {
    }

    // Construtor com todos os campos
    public Show(Long id, String nome, String local, String data) {
        this.id = id;
        this.nome = nome;
        this.local = local;
        this.data = data;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    // toString método para depuração
    @Override
    public String toString() {
        return "Show{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", local='" + local + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
