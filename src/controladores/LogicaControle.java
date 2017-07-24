package controladores;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LogicaControle {
    
    public void executar(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException;
    
}
