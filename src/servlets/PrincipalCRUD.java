package servlets;

import controladores.LogicaControle;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PrincipalCRUD", urlPatterns = {"/PrincipalCRUD"})
public class PrincipalCRUD extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nomeControlador = request.getParameter("controlador");
        String nomeClasse = "controladores." + nomeControlador + "Controlador";
        Class<?> classe = null;
        try {
            classe = Class.forName(nomeClasse);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PrincipalCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        LogicaControle logicaControle = null;
        try {
            logicaControle = (LogicaControle) classe.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(PrincipalCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        logicaControle.executar(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet metodos doGET, doPOST.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
