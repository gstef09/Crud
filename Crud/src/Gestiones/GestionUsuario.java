/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Gestiones;

import CapaDatos.Conexionbd;
import ClasesPojo.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */

  public class GestionUsuario implements IGestion {
    
        private Usuario usuario;

    public GestionUsuario() {
        
        usuario=new Usuario(0,"","","");
        Conexionbd.setUsuario("root");
        Conexionbd.setClave("");
        Conexionbd.setCadenaConexion("jdbc:mysql://localhost/usuariodb");
    }
        
        

    /**
     * Get the value of usuario
     *
     * @return the value of usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Set the value of usuario
     *
     * @param usuario new value of usuario
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void Nuevo() {
usuario.setId(0);
        usuario.setNombre(null);
        usuario.setApellido(null);
        usuario.setCedula(null);
    }

    @Override
    public void Insertar() throws SQLException {
        
        try
        {
         Conexionbd.getInstancia().conectar();
         Conexionbd.getInstancia().ejecutar("insert into usuario (Id,Nombre,Apellido,Cedula) values ("+usuario.getId()+",'"+usuario.getNombre()+"','"+usuario.getApellido()+"','"+usuario.getCedula()+"')");
        }
        catch(SQLException ex)
        {
            throw ex;
        }
        finally 
        {
            Conexionbd.getInstancia().desconectar();
        }
        
    }

        @Override
    public void Consultar() throws SQLException {
        
        
       
       
           
        try
        {
         Conexionbd.getInstancia().conectar();
         
                 Conexionbd.getInstancia().ejecutarQuery("select * from usuario where Id="+(usuario.getId())) ;
                ResultSet rs=  Conexionbd.getInstancia().getResultado();
                JOptionPane.showMessageDialog(null, "Usuario encontrado");
                if(rs==null){
                 JOptionPane.showMessageDialog(null, "El usuario no està registrado");
                }
                  while(rs.next()){
               usuario.setId(rs.getInt(1));
               usuario.setNombre(rs.getString(2));
               usuario.setApellido(rs.getString(3));
               usuario.setCedula(rs.getString(4));
        }
        }
        catch(SQLException ex)
        {
            throw ex;
        }
        finally 
        {
            Conexionbd.getInstancia().desconectar();
        } 
     
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Modificar() throws SQLException {
         try
        {
         Conexionbd.getInstancia().conectar();
         Conexionbd.getInstancia().ejecutar("update  usuario set nombre= '"+(usuario.getNombre())+"' , apellido='"+(usuario.getApellido())+"' , cedula= '"+(usuario.getCedula())+"' where id="+(usuario.getId()));
                 
        
        }
        catch(SQLException ex)
        {
            throw ex;
        }
        finally 
        {
            Conexionbd.getInstancia().desconectar();
        }
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Eliminar() throws SQLException {
          try
        {
         Conexionbd.getInstancia().conectar();
         Conexionbd.getInstancia().ejecutar("delete  from usuario where Id="+(usuario.getId()));
         JOptionPane.showMessageDialog(null,"Usuario Eliminado");
         
        }
        catch(SQLException ex)
        {
            throw ex;
        }
        finally 
        {
            Conexionbd.getInstancia().desconectar();
        }
    }
    
    
}  

