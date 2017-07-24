package modelo;

import java.io.Serializable;

public class ProdutoCategoria  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idCategoria;
	private Integer idProduto;
	
	public Integer getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	public Integer getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	

}
