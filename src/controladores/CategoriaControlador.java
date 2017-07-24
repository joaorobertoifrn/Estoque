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
import modelo.Categoria;
import persistencia.CategoriaDAO;

@WebServlet("/categoriaControle")
public class CategoriaControlador extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String acao = req.getParameter("acao");

        if (acao.equals("excluir")) {
            String id = req.getParameter("id");
            Categoria  categoria = new Categoria();
            if (id != null) {
                categoria.setId(Integer.parseInt(id));
            }

            CategoriaDAO categoriaDAO =  new CategoriaDAO();
            
            try {
                categoriaDAO.excluir(categoria.getId());
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(CategoriaControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            resp.sendRedirect("categoriaControle?acao=listar");

        } else if (acao.equals("listar")) {
            CategoriaDAO categoriaDAO = new CategoriaDAO();

            List<Categoria> listaCategorias = null;
            try {
                listaCategorias = categoriaDAO.getCategorias();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(CategoriaControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            req.setAttribute("listar", listaCategorias);

            RequestDispatcher Dispatcher = req.getRequestDispatcher("pages/Categorias.jsp");
            Dispatcher.forward(req, resp);

        } else if (acao.equals("alterar")) {

            int id = Integer.parseInt(req.getParameter("id"));
            Categoria categoria = new Categoria();
            CategoriaDAO categoriaDAO = new CategoriaDAO();

            try {
                categoria = categoriaDAO.getCategoriaPorId(id);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(CategoriaControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            req.setAttribute("categoria", categoria);

            RequestDispatcher Dispatcher = req.getRequestDispatcher("pages/salvarCategoria.jsp");
            Dispatcher.forward(req, resp);

        } else if (acao.equals("incluir")) {
            Categoria categoria = new Categoria();
            categoria.setId(0);
            categoria.setNome("");
            
            req.setAttribute("categoria", categoria);

            RequestDispatcher Dispatcher = req.getRequestDispatcher("pages/salvarCategoria.jsp");
            Dispatcher.forward(req, resp);

        } 
    }
 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String id = req.getParameter("id");
        String nome = req.getParameter("nome");

        Categoria categoria = new Categoria();
      
        categoria.setId(Integer.parseInt(id));
        categoria.setNome(nome);

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        try {
            if (categoria.getId() != 0){
                categoriaDAO.atualizar(categoria);
            } else {
                categoriaDAO.inserir(categoria);
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CategoriaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        resp.sendRedirect("categoriaControle?acao=listar");
    }

}
