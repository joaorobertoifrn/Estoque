package modelo;

import java.io.Serializable;
import java.math.BigDecimal;

public class Produto implements Serializable {
    
   private static final long serialVersionUID = 1L; 
   
   private Integer id;
   private String nome;
   private String descricao;
   private Integer estoque;
   private BigDecimal preco;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", estoque=" + estoque + ", preco=" + preco + '}';
    }
   
   
   
}
