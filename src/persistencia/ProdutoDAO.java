package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Produto;

public class ProdutoDAO extends GeralDAO {

    private static final String INSERT = "insert into Produto (nome,descricao,estoque,preco) values (?,?,?,?);";
    private static final String DELETE = "delete from Produto where idProduto = ?;";
    private static final String UPDATE = "update Produto set nome=?,descricao=?,estoque=?,preco=? where idProduto = ?;";
    private static final String SELECT_ALL = "select * from Produto order by idProduto;";
    private static final String SELECT_BY_NAME = "select * from Produto where nome like ? order by nome;";
    private static final String SELECT_BY_ID = "select * from Produto where idProduto = ? order by idProduto;";
    private static final String SELECT_QTD = "select count(*) as qtd from Produto;";

    public List<Produto> getProdutosPorNome(String nome) throws ClassNotFoundException, SQLException {
        ResultSet resultado = getConsulta(SELECT_BY_NAME, nome + "%");
        List<Produto> listaProdutos = new ArrayList<Produto>();
        try {
            while (resultado.next()) {
                listaProdutos.add(popularProduto(resultado));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaProdutos;
    }

    public Produto getProdutoPorId(int id) throws ClassNotFoundException, SQLException {
        ResultSet resultado = getConsulta(SELECT_BY_ID, id);
        Produto produto = null;

        try {
            while (resultado.next()) {
                produto = popularProduto(resultado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produto;
    }

    public List<Produto> getProdutos() throws ClassNotFoundException, SQLException {
        ResultSet resultado = getConsulta(SELECT_ALL);
        List<Produto> listaProdutos = new ArrayList<Produto>();
        try {
            while (resultado.next()) {
                listaProdutos.add(popularProduto(resultado));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaProdutos;
    }

    public String getProdutosQtd() throws ClassNotFoundException, SQLException {
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

    private Produto popularProduto(ResultSet resultado) throws SQLException {
        Produto produto = new Produto();
        produto.setId(resultado.getInt("idProduto"));
        produto.setNome(resultado.getString("nome"));
        produto.setDescricao(resultado.getString("descricao"));
        produto.setEstoque(resultado.getInt("estoque"));
        produto.setPreco(resultado.getBigDecimal("preco"));
        return produto;
    }

    public boolean inserir(Produto produto) throws ClassNotFoundException, SQLException {
        int retorno = getComando(INSERT, produto.getNome(), produto.getDescricao(), produto.getEstoque(), produto.getPreco());
        return retorno > 0;
    }

    public boolean atualizar(Produto produto) throws ClassNotFoundException, SQLException {
        int retorno = getComando(UPDATE, produto.getNome(), produto.getDescricao(), produto.getEstoque(), produto.getPreco(), produto.getId());
        return retorno > 0;
    }

    public void salvar(Produto produto) throws ClassNotFoundException, SQLException {
        if (produto.getId() != 0) {
            atualizar(produto);
        } else {
            inserir(produto);
        }
    }

    public void excluir(int idProduto) throws ClassNotFoundException, SQLException {
        getComando(DELETE, idProduto);
    }
}
