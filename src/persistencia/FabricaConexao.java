package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {
    
    private static Connection conexao;
    
    public static Connection getConexao() throws ClassNotFoundException, SQLException {       
        if (conexao == null){
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/Estoque?zeroDateTimeBehavior=convertToNull","root","mh22xx");       
            conexao = DriverManager.getConnection("jdbc:sqlserver://dbifrn.database.windows.net:1433;database=Estoque;user=estoque@dbifrn;password={Senha};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");       
        }
        return conexao; 
    }
    
    public static void fecharConexao() throws SQLException {       
        if (conexao != null)
            conexao.close();        
    }  
}
