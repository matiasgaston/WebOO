
package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import transferobject.UsuarioTO;


/**
 *
 * @author Luis
 */
public class UsuarioDAO {
    public static final String INSERT_QUERY = "INSERT INTO invited(id, name, run) VALUES(?,?,?);";
    public static final String READ_ALL = "SELECT * FROM invited;";
    public static final String SEARCH_QUERY = "SELECT * FROM invited WHERE (name LIKE ?) OR run LIKE ?;";
    public static final String DELETE_QUERY = "DELETE FROM invited WHERE id = ?;";
    
    //BASE DE DATOS LOCAL
    private static final String DB_NAME="party";
    private static final String PORT="3306";
    private static final String URL="jdbc:mysql://127.0.0.1:"+PORT+"/"+DB_NAME;    
    private static final String USER="root";
    private static final String PASSWORD="";
    
    //BASE DE DATOS REMOTA
    //private static final String URL="jdbc:mysql://192.168.70.2:"+PORT+"/"+DB_NAME;    
    //private static final String USER="developer";
    //private static final String PASSWORD="tallerweb";
    public int create(UsuarioTO invitado) throws SQLException{
        Connection conn = null;
        int result = 0;
        
        try{
            conn = getConnection();
            
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(INSERT_QUERY);
            preparedStmt.setString (1, null);
            preparedStmt.setString(2, invitado.getNombre());
            preparedStmt.setString(3, invitado.getApellidos());

            // execute the preparedstatement
            preparedStmt.execute();
            
        }catch(SQLException e){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            conn.close();
        }    
        return result;
    }
    
    public LinkedList<UsuarioTO> search(String campo) throws SQLException{
        LinkedList<UsuarioTO> list = new LinkedList<>();
        UsuarioTO result = null;
        Connection conn = null;
        
        try{
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(SEARCH_QUERY);

            ps.setString(1, "%"+campo+"%");
            ps.setString(2, "%"+campo+"%");
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                result = new UsuarioTO();
                result.setIdUsuario(rs.getInt("id"));
                result.setNombre(rs.getString("name"));
                result.setApellidos(rs.getString("run"));
               
                list.add(result);
            }
            
        }catch(SQLException e){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            conn.close();
        }
        
        return list;
    }
    
    public LinkedList<UsuarioTO> readAll() throws SQLException{
        LinkedList<UsuarioTO> list = new LinkedList<>();
        UsuarioTO result = null;
        Connection conn = null;
        
        try{
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(READ_ALL);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                result = new UsuarioTO();
                result.setIdUsuario(rs.getInt("id"));
                result.setNombre(rs.getString("name"));
                result.setApellidos(rs.getString("run"));
               
                list.add(result);
            }
            
        }catch(SQLException e){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            conn.close();
        }
        
        return list;
    }
    
    
    private static Connection getConnection(){
        Connection conn = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
        }catch(ClassNotFoundException | SQLException e1){
            e1.printStackTrace();
            System.out.println("Grave error que hace que todo explote, literalmente");
        }
        
        return conn;
    }
    
    
}//FIN CLASE USUARIODAO
