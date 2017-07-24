package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Usuario;
import persistencia.UsuarioDAO;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sessao = request.getSession(false);
        
        if (sessao!=null){
            sessao.invalidate();
        }
        
        response.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        String login = req.getParameter("login");
        String senha = req.getParameter("senha");
        
        Usuario usuario = new Usuario();
        
        usuario.setLogin(login);
        usuario.setSenha(senha);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuarioAutenticado = new Usuario();
        try {
            usuarioAutenticado = usuarioDAO.AutenticaUsuario(usuario);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (usuarioAutenticado != null){
            
            HttpSession sessao = req.getSession();
            
            sessao.setAttribute("usuarioLogado", usuarioAutenticado.getNome());
            
            sessao.setMaxInactiveInterval(60*5);
            
            
            Cookie cookie = new Cookie("usarioLogadoCookie", usuarioAutenticado.getLogin());
            cookie.setMaxAge(30 * 60);
            resp.addCookie(cookie);
           
            resp.sendRedirect("home");
        } else {
            resp.getWriter().print("<script> window.alert('Usuario nao encontrado'); location.href='login.html' </script>");
        }
    }

}
