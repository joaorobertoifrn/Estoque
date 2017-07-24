package persistencia;

import modelo.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO extends GeralDAO {

    private static final String INSERT = "insert into Usuario (nome,login,senha) values (?,?,?);";
    private static final String DELETE = "delete from Usuario where idUsuario = ?;";
    private static final String UPDATE = "update Usuario set nome=?,login=?,senha=? where idUsuario = ?;";
    private static final String SELECT_ALL = "select * from Usuario order by nome;";
    private static final String SELECT_BY_NAME = "select * from Usuario where nome like ? order by nome;";
    private static final String AUTENTICA = "select * from Usuario where login = ? and senha = ?";

    public List<Usuario> getUsuariosPorNome(String nome) throws ClassNotFoundException, SQLException {
        ResultSet resultado = getConsulta(SELECT_BY_NAME, nome + "%");
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();
        try {
            while (resultado.next()) {
                listaUsuarios.add(popularCliente(resultado));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaUsuarios;
    }

    public List<Usuario> getUsuarios() throws ClassNotFoundException, SQLException {
        ResultSet resultado = getConsulta(SELECT_ALL);
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();
        try {
            while (resultado.next()) {
                listaUsuarios.add(popularCliente(resultado));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaUsuarios;
    }

    private Usuario popularCliente(ResultSet resultado) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(resultado.getInt("idUsuario"));
        usuario.setNome(resultado.getString("nome"));
        usuario.setLogin(resultado.getString("login"));
        usuario.setSenha(resultado.getString("senha"));
        return usuario;
    }

    public boolean inserir(Usuario usuario) throws ClassNotFoundException, SQLException {
        int retorno = getComando(INSERT, usuario.getNome(), usuario.getLogin(), usuario.getSenha()
        );
        return retorno > 0;
    }

    public void atualizar(Usuario cliente) throws ClassNotFoundException, SQLException {
        getComando(UPDATE, cliente.getNome(), cliente.getLogin(),
                cliente.getSenha(), cliente.getId());
    }

    public void excluir(int idCliente) throws ClassNotFoundException, SQLException {
        getComando(DELETE, idCliente);
    }

    public Usuario AutenticaUsuario(Usuario usuario) throws ClassNotFoundException, SQLException {
        ResultSet resultado = getConsulta(AUTENTICA, usuario.getLogin(), usuario.getSenha());

        Usuario usuarioAutenticado = null;
        try {
            if (resultado.next()) {
                usuarioAutenticado = popularCliente(resultado);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarioAutenticado;
    }

}
