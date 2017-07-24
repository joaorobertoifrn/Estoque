package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Pedido;

public class PedidoDAO extends GeralDAO {

    private static final String INSERT = "insert into Pedido (idCliente, total) values (?,?);";
    private static final String DELETE = "delete from Pedido where idPedido = ?;";
    private static final String UPDATE = "update Pedido set idCliente=?, total=? where idPedido = ?;";
    private static final String SELECT_ALL = "select * from Pedido order by idPedido;";
    private static final String SELECT_BY_ID = "select * from Pedido where idProduto = ? order by idPedido;";
    private static final String SELECT_QTD = "select count(*) as qtd from Pedido;";

    public Pedido getPedidoPorId(int id) throws ClassNotFoundException, SQLException {
        ResultSet resultado = getConsulta(SELECT_BY_ID, id);
        Pedido pedido = null;

        try {
            while (resultado.next()) {
                pedido = popularPedido(resultado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pedido;
    }

    public List<Pedido> getPedidos() throws ClassNotFoundException, SQLException {
        ResultSet resultado = getConsulta(SELECT_ALL);
        List<Pedido> listaPedidos = new ArrayList<Pedido>();
        try {
            while (resultado.next()) {
                listaPedidos.add(popularPedido(resultado));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPedidos;
    }

    public String getPedidosQtd() throws ClassNotFoundException, SQLException {
        ResultSet resultado = getConsulta(SELECT_QTD);
        Integer Qtd = 0;
        try {
            while (resultado.next()) {
                Qtd = resultado.getInt("qtd");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Integer.toString(Qtd);
    }

    private Pedido popularPedido(ResultSet resultado) throws SQLException {
    	Pedido pedido = new Pedido();
        pedido.setId(resultado.getInt("idProduto"));
        pedido.setIdCliente(resultado.getInt("idCliente"));
        pedido.setTotal(resultado.getBigDecimal("total"));
        return pedido;
    }

    public boolean inserir(Pedido pedido) throws ClassNotFoundException, SQLException {
        int retorno = getComando(INSERT, pedido.getIdCliente(), pedido.getTotal());
        return retorno > 0;
    }

    public boolean atualizar(Pedido pedido) throws ClassNotFoundException, SQLException {
        int retorno = getComando(UPDATE, pedido.getIdCliente(), pedido.getTotal(), pedido.getId());
        return retorno > 0;
    }

    public void salvar(Pedido pedido) throws ClassNotFoundException, SQLException {
        if (pedido.getId() != 0) {
            atualizar(pedido);
        } else {
            inserir(pedido);
        }
    }

    public void excluir(int idPedido) throws ClassNotFoundException, SQLException {
        getComando(DELETE, idPedido);
    }
}
