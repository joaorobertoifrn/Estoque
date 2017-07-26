package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class Pedido  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer idCliente;
	private BigDecimal total;
	private String dataCadastro;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public String getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	@Override
	public String toString() {
		return "Pedido [id=" + id + ", idCliente=" + idCliente + ", total=" + total + ", dataCadastro=" + dataCadastro
				+ "]";
	}

}
