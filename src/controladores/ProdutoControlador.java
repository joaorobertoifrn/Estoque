package controladores;

import java.io.IOException;
import java.math.BigDecimal;
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
import modelo.Produto;
import modelo.ProdutoCategoria;
import persistencia.CategoriaDAO;
import persistencia.ProdutoCategoriaDAO;
import persistencia.ProdutoDAO;

@WebServlet("/produtoControle")
public class ProdutoControlador extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
        resp.setContentType("text/html");
        String acao = req.getParameter("acao");
        
        String idProdutoReq = req.getParameter("id");
        
        if (acao.equals("excluir")) {
        	
            String id = req.getParameter("id");
            Produto produto = new Produto();
            if (id != null) {
                produto.setId(Integer.parseInt(id));
            }

            ProdutoDAO produtoDAO = new ProdutoDAO();

            try {
                produtoDAO.excluir(produto.getId());
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ProdutoControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            resp.sendRedirect("produtoControle?acao=listar");

        } else if (acao.equals("listar")) {
        	
            ProdutoDAO produtoDAO = new ProdutoDAO();

            List<Produto> listaProdutos = null;
            try {
                listaProdutos = produtoDAO.getProdutos();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ProdutoControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            req.setAttribute("listar", listaProdutos);

            RequestDispatcher Dispatcher = req.getRequestDispatcher("pages/Produtos.jsp");
            Dispatcher.forward(req, resp);

        }  else if (acao.equals("alterar")) {	
        	
        	CategoriaDAO categoriaDAO = new CategoriaDAO();
            List<Categoria> listarCategoria = null;
            try {
            	listarCategoria = categoriaDAO.getCategorias();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(CategoriaControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            req.setAttribute("listarCategoria", listarCategoria);

            
            int id = Integer.parseInt(req.getParameter("id"));
        	ProdutoCategoriaDAO produtocategoriaDAO = new ProdutoCategoriaDAO();
            List<Categoria> listarProdutoCategoria = null;
            try {
            	listarProdutoCategoria = produtocategoriaDAO.getProdutoCategorias(id);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(CategoriaControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            req.setAttribute("listarProdutoCategoria", listarProdutoCategoria);

            
            Produto produto = new Produto();
            ProdutoDAO produtoDAO = new ProdutoDAO();

            try {
                produto = produtoDAO.getProdutoPorId(id);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ProdutoControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            req.setAttribute("produto", produto);

            RequestDispatcher Dispatcher = req.getRequestDispatcher("pages/salvarProduto.jsp");
            Dispatcher.forward(req, resp);
            

        }  else if (acao.equals("incluir")) {
        	
        	Produto produto = new Produto();
            produto.setId(0);
            produto.setNome("");
            produto.setDescricao("");
            produto.setEstoque(0);
            produto.setPreco(new BigDecimal(0.0));

            req.setAttribute("produto", produto);

            RequestDispatcher Dispatcher = req.getRequestDispatcher("pages/salvarProduto.jsp");
            Dispatcher.forward(req, resp);

        } else if (acao.equals("incluirProdutoCategoria")) {
    		
        	ProdutoCategoriaDAO produtocategoriaDAO = new ProdutoCategoriaDAO();
        	
        	String idProduto = req.getParameter("idProduto");
            String idCategoria = req.getParameter("idCategoria");
        	
            ProdutoCategoria produtocategoria = new ProdutoCategoria();
            
            produtocategoria.setIdCategoria(Integer.parseInt(idCategoria));
            produtocategoria.setIdProduto(Integer.parseInt(idProduto));
            
            try {
				produtocategoriaDAO.inserir(produtocategoria);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ProdutoControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            

            List<Categoria> listarProdutoCategoria = null;
            
            try {
                listarProdutoCategoria = produtocategoriaDAO.getProdutoCategorias(Integer.parseInt(idProduto));
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ProdutoControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            req.setAttribute("listarProdutoCategoria", listarProdutoCategoria);
            
            resp.sendRedirect("produtoControle?acao=alterar&id="+idProduto);
        	
             
        } else if (acao.equals("excluirCategoria")) {
            
        	String idProduto = req.getParameter("idProduto");
            String idCategoria = req.getParameter("idCategoria");
        	
            ProdutoCategoria produtocategoria = new ProdutoCategoria();

            produtocategoria.setIdProduto(Integer.parseInt(idProduto));
            produtocategoria.setIdCategoria(Integer.parseInt(idCategoria));
            
            
            ProdutoCategoriaDAO produtocategoriaDAO = new ProdutoCategoriaDAO();

            try {
                produtocategoriaDAO.excluir(produtocategoria);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ProdutoControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            resp.sendRedirect("produtoControle?acao=alterar&id="+idProduto);
        	
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
        String id = req.getParameter("id");
        String nome = req.getParameter("nome");
        String descricao = req.getParameter("descricao");
        String estoque = req.getParameter("estoque");
        String valor = req.getParameter("preco");

        Produto produto = new Produto();

        produto.setId(Integer.parseInt(id));
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setEstoque(Integer.parseInt(estoque));
        produto.setPreco(new BigDecimal(valor));

        ProdutoDAO produtoDAO = new ProdutoDAO();
        
        try {
            if (produto.getId() != 0) {
                produtoDAO.atualizar(produto);
            } else {
                produtoDAO.inserir(produto);
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProdutoControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        resp.sendRedirect("produtoControle?acao=listar");
    }

}
