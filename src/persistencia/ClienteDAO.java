package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Cliente;

public class ClienteDAO extends GeralDAO  {

    private static final String INSERT = "insert into Cliente (nome,endereco,email,cpfcnpj,rgie,telefone) values (?,?,?,?,?,?);";
    private static final String DELETE = "delete from Cliente where idCliente = ?;";
    private static final String UPDATE = "update Cliente set nome=?,endereco=?,email=?,cpfcnpj=?,rgie=?,telefone=? where idCliente = ?;";
    private static final String SELECT_ALL = "select * from Cliente order by idCliente;";
    private static final String SELECT_BY_NAME = "select * from Cliente where nome like ? order by nome;";
    private static final String SELECT_BY_ID = "select * from Cliente where idCliente = ? order by idCliente;";
    private static final String SELECT_QTD = "select count(*) as qtd from Cliente;";

    public List<Cliente> getClientesPorNome(String nome) throws ClassNotFoundException, SQLException {
        ResultSet resultado = getConsulta(SELECT_BY_NAME,nome + "%");
        List<Cliente> listaClientes = new ArrayList<Cliente>();
        try {
            while (resultado.next()) {
                listaClientes.add(popularCliente(resultado));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaClientes;
    }

    public Cliente getClientePorId(int id) throws ClassNotFoundException, SQLException {
        ResultSet resultado = getConsulta(SELECT_BY_ID,id);
            Cliente cliente = null;

        try {
            while (resultado.next()) {
                cliente = popularCliente(resultado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente;
    }
    
    
    public List<Cliente> getClientes() throws ClassNotFoundException, SQLException {
        ResultSet resultado = getConsulta(SELECT_ALL);
        List<Cliente> listaClientes = new ArrayList<Cliente>();
        try {
            while (resultado.next()) {
                listaClientes.add(popularCliente(resultado));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaClientes;
    }

    public String getClientesQtd() throws ClassNotFoundException, SQLException {
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
    
    
    
    private Cliente popularCliente(ResultSet resultado) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(resultado.getInt("idCliente"));
        cliente.setNome(resultado.getString("nome"));
        cliente.setEndereco(resultado.getString("endereco"));
        cliente.setEmail(resultado.getString("email"));
        cliente.setCpfCnpj(resultado.getString("cpfcnpj"));
        cliente.setRgIe(resultado.getString("rgie"));
        cliente.setTelefone(resultado.getString("telefone"));
        //cliente.setDataCadastro(resultado.getString("datacadastro"));
        return cliente;
    }

    public boolean inserir(Cliente cliente) throws ClassNotFoundException, SQLException {
        int retorno = getComando(INSERT, cliente.getNome(), cliente.getEndereco(), cliente.getEmail() ,cliente.getCpfCnpj(), cliente.getRgIe(), cliente.getTelefone());
        return retorno > 0;
    }
                  
    public boolean atualizar(Cliente cliente) throws ClassNotFoundException, SQLException {
        int retorno = getComando(UPDATE, cliente.getNome(), cliente.getEndereco(), cliente.getEmail(), cliente.getCpfCnpj(), cliente.getRgIe(), cliente.getTelefone(),  cliente.getId());
        return retorno > 0;
    }

    public void salvar(Cliente cliente) throws ClassNotFoundException, SQLException {
        if (cliente.getId() != 0) {
            atualizar(cliente);
        } else {
            inserir(cliente);
        }
    }

    public void excluir(int idCliente) throws ClassNotFoundException, SQLException {
        getComando(DELETE, idCliente);
    }
}
