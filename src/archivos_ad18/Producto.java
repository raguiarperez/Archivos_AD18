
package archivos_ad18;

import java.awt.Cursor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author dam2
 */
public class Producto {
       Connection conn;

    
    public void conectar() throws SQLException { //Todos los metodos van llamar a este para no tener que abrir y cerrar manualmente todo el rato
        
        String driver = "jdbc:oracle:thin:";
        String host = "localhost.localdomain";
        String porto = "1521";
        String sid = "orcl";
        String usuario = "hr";
        String password = "hr";
        String url = driver + usuario + "/" + password + "@" + host + ":" + porto + ":" + sid; 
        //para conectar co native protocal all java driver: creamos un obxecto Connection usando o metodo getConnection da clase  DriverManager 
        conn = DriverManager.getConnection(url);
        
    }
    
    public void cerrar() throws SQLException {
    
        conn.close();
    }

    
    public void addProducto(String codigo, String desc, int prezo) throws SQLException {
        
        try {
            conectar();
           
            Statement stm = conn.createStatement();
            
            stm.executeUpdate("insert into produtos values('" + codigo + "','" + desc + "','" + prezo + "')");
            System.out.println("Añadido con exito!"); 
            
            cerrar();
            
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No se ha podido insertar");
            
        }
        
    }
    
    
    public void verLista() {
        
        try {
            
            conectar();
            
            Statement stm = conn.createStatement();
            
            ResultSet rs = stm.executeQuery("select * from produtos");

            System.out.println("Codigo\t" + "Desc\t" + "Prezo");
            
            while (rs.next()) {
                //las columnas se cuentan a partir de 1, NO de 0
                //en este caso sabemos que hay 3
                for (int i = 1; i <= 3; i++) {
                    
                    System.out.print(rs.getString(i) + "\t");
                    
                }
                System.out.println();
                
            }
            
            cerrar();
            
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void actPrezo(String cod, int prez) {
        
        try {
            conectar();
            
            Statement st = conn.createStatement();
            
            st.executeUpdate("update produtos set prezo='" + prez + "' where codigo='" + cod + "'");
            
            System.out.println("Prezo cambiado");
            
            cerrar();
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void borrar(String cod) {
        
        try {
            conectar();
            
            Statement st = conn.createStatement();
            
            st.executeUpdate("delete from produtos where codigo = '" + cod + "'");
            
            System.out.println("Fila borrada");
            cerrar();
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void verFila(String codigo) {
        
        try {
            conectar();
            Statement stm = conn.createStatement();
            
            ResultSet rs = stm.executeQuery("select * from produtos where codigo='" + codigo + "'");
            
            System.out.println("Codigo\t" + "Desc\t" + "Prezo");
            
            while (rs.next()) {     
                for (int i = 1; i <= 3; i++) { //las columnas empiexan en 1
                    System.out.print(rs.getString(i) + "\t"); //La base lo pasa todo a String       
                }
                System.out.println();     
            }
            
            cerrar();
        } catch (SQLException ex) {
            Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
}
