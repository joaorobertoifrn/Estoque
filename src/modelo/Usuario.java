package modelo;

public class Usuario {

    private Integer id;
    private String nome;
    private String login;
    private String senha;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Usuario { " + "id= " + id + ", nome= " + nome.trim() + ", login= " + login.trim() + ", senha= " + senha.trim() + '}';
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

}
