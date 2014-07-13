/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author Andrea
 */
public abstract class ConexionSeguraBD {
    /*
     Datos de CONEXION A LA BASE DE DATOS
     */
    private String driver = "org.postgresql.Driver";
    private final String db = "jdbc:postgresql://localhost:5432/basedatosproyecto";
    private final String user = "postgres";
    private final String password = "j3s1c4";
	/*
	Objeto para poder ejecutar sentencias SQL
	*/
    protected PreparedStatement sentencia;
    /*
     Variable de conexion a la base de datos
     */
    protected Connection con;
    /*
     Metodo para abrir una conexion a la base de datos 
     */
    protected boolean abrirConexion() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(db, user, password);
            con.setAutoCommit(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido encontrar el DRIVER");
            return false;
        }
        return true;
    }

    protected boolean cerrarConexion() {
        try {
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido Cerar la Conexion");
            return false;
        }
        return true;
    }

    protected boolean empezarTransaccion() {
        try {
            if (abrirConexion()) {
                con.setAutoCommit(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido Cerar la Conexion");
            return false;
        }
        return true;
    }

     protected boolean commitTransaccion() {
        try {
            con.commit();
            cerrarConexion();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido Cerar la Conexion");
            return false;
        }
        return true;
    }
     
    protected boolean rollbackTransaccion() {
        try {
            con.rollback();
            cerrarConexion();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido Cerar la Conexion");
            return false;
        }
        return true;
    }

   
       
}
