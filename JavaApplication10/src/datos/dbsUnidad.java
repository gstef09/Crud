/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import Interfaz.Unidad;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Percho
 */
public class dbsUnidad extends ConexionSeguraBD {

    public boolean insertar(Unidad uni) {
        try {
            String sql = "INSERT INTO \"Unidad\"(nombre_uni, valor_uni, estado_uni, unidad_uni) VALUES (?,?,?,?);";
            this.abrirConexion();
            this.sentencia = this.con.prepareStatement(sql);
            //Agregamos los parametros
            this.sentencia.setString(1, uni.getNombre());
            this.sentencia.setDouble(2, uni.getValor());
            this.sentencia.setString(4, uni.getUnidad());
            this.sentencia.setString(3, "A");
            //--------------------------------------------
            this.sentencia.executeUpdate();
            this.cerrarConexion();
            return true;
        } catch (Exception ex) {
            System.out.println("ERROR:"+ ex.getMessage());
            return false;
        }
    }

    public DefaultTableModel llenarTablaUnidad() {
        DefaultTableModel model = new DefaultTableModel();
        for (String col : new String[]{"UNIDAD", "VALOR", "TIPO"}) {
            model.addColumn(col);
        }
        try {
            String sql = "SELECT *FROM \"Unidad\" WHERE estado_uni='A'";
            this.abrirConexion();
            this.sentencia = this.con.prepareStatement(sql);
            ResultSet rs = this.sentencia.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{rs.getObject(2),rs.getObject(3),rs.getObject(5)});
            }
            this.cerrarConexion();
            return model;
        } catch (Exception ex) {
            return null;
        }
    }
    
    public List<Unidad> listarUnidad() {
       List<Unidad> model = new LinkedList<Unidad>();
        try {
            String sql = "SELECT *FROM \"Unidad\" WHERE estado_uni='A'";
            this.abrirConexion();
            this.sentencia = this.con.prepareStatement(sql);
            ResultSet rs = this.sentencia.executeQuery();
            while (rs.next()) {
                model.add(new Unidad(rs.getInt(1), rs.getString(2), rs.getDouble(3),rs.getString(5), rs.getString(4)));
            }
            this.cerrarConexion();
            return model;
        } catch (Exception ex) {
            return null;
        }
    }
}
