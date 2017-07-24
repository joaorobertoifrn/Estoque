package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GeralDAO {

    public ResultSet getConsulta(String sql, Object... parametros) throws ClassNotFoundException, SQLException {        
        PreparedStatement comando;

        comando = FabricaConexao.getConexao().prepareStatement(sql);
        for (int i = 1; i <= parametros.length; i++) {
            comando.setObject(i, parametros[i - 1]);
        }
        return comando.executeQuery();
    }

    public int getComando(String sql, Object... parametros) throws ClassNotFoundException, SQLException {        
        PreparedStatement comando;

        comando = FabricaConexao.getConexao().prepareStatement(sql);
        for (int i = 1; i <= parametros.length; i++) {
            comando.setObject(i, parametros[i - 1]);
        }
        return comando.executeUpdate();
    }
}
