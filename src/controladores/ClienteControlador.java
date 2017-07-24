package controladores;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente;
import persistencia.ClienteDAO;

@WebServlet("/clienteControle")
public class ClienteControlador extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String acao = req.getParameter("acao");

        if (acao.equals("excluir")) {
            String id = req.getParameter("id");
            Cliente  cliente = new Cliente();
            if (id != null) {
                cliente.setId(Integer.parseInt(id));
            }

            ClienteDAO clienteDAO =  new ClienteDAO();
            
            try {
                clienteDAO.excluir(cliente.getId());
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ClienteControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            resp.sendRedirect("clienteControle?acao=listar");

        } else if (acao.equals("listar")) {
            ClienteDAO clienteDAO = new ClienteDAO();

            List<Cliente> listaClientes = null;
            try {
                listaClientes = clienteDAO.getClientes();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ClienteControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            req.setAttribute("listar", listaClientes);

            RequestDispatcher Dispatcher = req.getRequestDispatcher("pages/Clientes.jsp");
            Dispatcher.forward(req, resp);

        } else if (acao.equals("alterar")) {

            int id = Integer.parseInt(req.getParameter("id"));
            Cliente cliente = new Cliente();
            ClienteDAO clienteDAO = new ClienteDAO();

            try {
                cliente = clienteDAO.getClientePorId(id);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ClienteControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            req.setAttribute("cliente", cliente);

            RequestDispatcher Dispatcher = req.getRequestDispatcher("pages/salvarCliente.jsp");
            Dispatcher.forward(req, resp);
            //resp.sendRedirect("clienteControle?acao=listar");

        } else if (acao.equals("incluir")) {
            Cliente cliente = new Cliente();
            cliente.setId(0);
            cliente.setNome("");
            cliente.setEndereco("");
            cliente.setEmail("");
            cliente.setCpfCnpj("");
            cliente.setRgIe("");
            cliente.setTelefone("");
            
            req.setAttribute("cliente", cliente);

            RequestDispatcher Dispatcher = req.getRequestDispatcher("pages/salvarCliente.jsp");
            Dispatcher.forward(req, resp);

        } 
    }
 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String id = req.getParameter("id");
        String nome = req.getParameter("nome");
        String endereco = req.getParameter("endereco");
        String email = req.getParameter("email");
        String telefone = req.getParameter("telefone");
        String cpfcnpj = req.getParameter("cpfcnpj");
        String rgie = req.getParameter("rgie");
        

        Cliente cliente = new Cliente();

        
        cliente.setId(Integer.parseInt(id));
        cliente.setNome(nome);
        cliente.setEndereco(endereco);
        cliente.setEmail(email);
        cliente.setCpfCnpj(cpfcnpj);
        cliente.setRgIe(rgie);
        cliente.setTelefone(telefone);

        ClienteDAO clienteDAO = new ClienteDAO();
        try {
            if (cliente.getId() != 0){
                clienteDAO.atualizar(cliente);
            } else {
                clienteDAO.inserir(cliente);
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ClienteControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        resp.sendRedirect("clienteControle?acao=listar");
    }

}
