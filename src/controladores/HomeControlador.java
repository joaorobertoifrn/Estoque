package controladores;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistencia.ClienteDAO;
import persistencia.ProdutoDAO;
import persistencia.CategoriaDAO;

@WebServlet("/home")
public class HomeControlador extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            
            ClienteDAO clienteDAO = new ClienteDAO();

            String qtdCliente = null;
            try {
                qtdCliente = clienteDAO.getClientesQtd();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ClienteControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            req.setAttribute("qtdClientes", qtdCliente);

            
            ProdutoDAO produtoDAO = new ProdutoDAO();

            String qtdProduto = null;
            try {
                qtdProduto = produtoDAO.getProdutosQtd();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ClienteControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            req.setAttribute("qtdProdutos", qtdProduto);
            

            
            
            CategoriaDAO categoriaDAO = new CategoriaDAO();

            String qtdCategoria = null;
            try {
                qtdCategoria = categoriaDAO.getCategoriasQtd();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ClienteControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            req.setAttribute("qtdCategorias", qtdCategoria);
            
        
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
    
    
    
}
