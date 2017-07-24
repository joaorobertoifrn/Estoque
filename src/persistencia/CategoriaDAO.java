package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Categoria;

public class CategoriaDAO extends GeralDAO  {

    private static final String INSERT = "insert into Categoria (nome) values (?);";
    private static final String DELETE = "delete from Categoria where idCategoria = ?;";
    private static final String UPDATE = "update Categoria set nome=? where idCategoria = ?;";
    private static final String SELECT_ALL = "select * from Categoria order by idCategoria;";
    private static final String SELECT_BY_NAME = "select * from Categoria where nome like ? order by nome;";
    private static final String SELECT_BY_ID = "select * from Categoria where idCategoria = ? order by idCategoria;";
    private static final String SELECT_QTD = "select count(*) as qtd from Categoria;";

    public List<Categoria> getCategoriaPorNome(String nome) throws ClassNotFoundException, SQLException {
        ResultSet resultado = getConsulta(SELECT_BY_NAME,nome + "%");
        List<Categoria> listaCategorias = new ArrayList<Categoria>();
        try {
            while (resultado.next()) {
                listaCategorias.add(popularCategoria(resultado));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaCategorias;
    }

    public Categoria getCategoriaPorId(int id) throws ClassNotFoundException, SQLException {
        ResultSet resultado = getConsulta(SELECT_BY_ID,id);
        Categoria categoria = null;

        try {
            while (resultado.next()) {
                categoria = popularCategoria(resultado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categoria;
    }
    
    
    public List<Categoria> getCategorias() throws ClassNotFoundException, SQLException {
        ResultSet resultado = getConsulta(SELECT_ALL);
        List<Categoria> listaCategorias = new ArrayList<Categoria>();
        try {
            while (resultado.next()) {
                listaCategorias.add(popularCategoria(resultado));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaCategorias;
    }

    public String getCategoriasQtd() throws ClassNotFoundException, SQLException {
        ResultSet resultado = getConsulta(SELECT_QTD);
        Integer Qtd = 0;
        try {
            while (resultado.next()) {
                Qtd = resultado.getInt("qtd");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Integer.toString(Qtd);
    }
    
    
    
    private Categoria popularCategoria(ResultSet resultado) throws SQLException {
    	Categoria categoria = new Categoria();
        categoria.setId(resultado.getInt("idCategoria"));
        categoria.setNome(resultado.getString("nome"));
        return categoria;
    }

    public boolean inserir(Categoria categoria) throws ClassNotFoundException, SQLException {
        int retorno = getComando(INSERT, categoria.getNome());
        return retorno > 0;
    }
                  
    public boolean atualizar(Categoria categoria) throws ClassNotFoundException, SQLException {
        int retorno = getComando(UPDATE, categoria.getNome(), categoria.getId());
        return retorno > 0;
    }

    public void salvar(Categoria categoria) throws ClassNotFoundException, SQLException {
        if (categoria.getId() != 0) {
            atualizar(categoria);
        } else {
            inserir(categoria);
        }
    }

    public void excluir(int idCategoria) throws ClassNotFoundException, SQLException {
        getComando(DELETE, idCategoria);
    }
}
