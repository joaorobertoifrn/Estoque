package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import modelo.Categoria;
import modelo.ProdutoCategoria;;

public class ProdutoCategoriaDAO extends GeralDAO  {

    private static final String INSERT = "insert into Produto_Categoria (idProduto, idCategoria) values (?,?);";
    private static final String DELETE = "delete from Produto_Categoria where (idProduto=? and idCategoria = ?);";
    private static final String SELECT_BY_ID = "SELECT Categoria.idCategoria, Categoria.nome  FROM Categoria INNER JOIN Produto_Categoria ON Categoria.idCategoria = Produto_Categoria.idCategoria INNER JOIN Produto ON Produto_Categoria.idProduto = Produto.idProduto WHERE Produto.idProduto = ?;";

    public List<Categoria> getProdutoCategorias(int id) throws ClassNotFoundException, SQLException {
        ResultSet resultado = getConsulta(SELECT_BY_ID, id);
        List<Categoria> listaCategorias = new ArrayList<Categoria>();
        try {
            while (resultado.next()) {
                listaCategorias.add(popularProdutoCategoria(resultado));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoCategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaCategorias;
    }

    private Categoria popularProdutoCategoria(ResultSet resultado) throws SQLException {
    	Categoria categoria = new Categoria();
    	categoria.setId(resultado.getInt("idCategoria"));
    	categoria.setNome(resultado.getString("nome"));
        return categoria;
    }

    public boolean inserir(ProdutoCategoria produtocategoria) throws ClassNotFoundException, SQLException {
        int retorno = getComando(INSERT, produtocategoria.getIdProduto(), produtocategoria.getIdCategoria());
        return retorno > 0;
    }
                  

    public void salvar(ProdutoCategoria produtocategoria) throws ClassNotFoundException, SQLException {
    	inserir(produtocategoria);
    }

    public void excluir(ProdutoCategoria produtocategoria) throws ClassNotFoundException, SQLException {
        getComando(DELETE, produtocategoria.getIdProduto(), produtocategoria.getIdCategoria());
    }
}
