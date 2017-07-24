package modelo;

import java.io.Serializable;

public class Categoria  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
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
	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome + "]";
	}	

}
