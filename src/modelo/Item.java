package modelo;

import java.io.Serializable;
import java.math.BigDecimal;

public class Item  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Pedido idPedido;
	private Produto idProduto;
	private Integer qtd;
	private BigDecimal valor;
	private BigDecimal subTotal;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Pedido getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Pedido idPedido) {
		this.idPedido = idPedido;
	}
	public Produto getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Produto idProduto) {
		this.idProduto = idProduto;
	}
	public Integer getQtd() {
		return qtd;
	}
	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public BigDecimal getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", idPedido=" + idPedido + ", idProduto=" + idProduto + ", qtd=" + qtd + ", valor="
				+ valor + ", subTotal=" + subTotal + "]";
	}

}
