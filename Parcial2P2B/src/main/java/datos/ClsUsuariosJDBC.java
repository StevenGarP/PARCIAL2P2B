/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelos.MdUsuarios;

/**
 *
 * @author HP
 */
public class ClsUsuariosJDBC implements Interface_UsuariosJDBC<MdUsuarios> {
    private ResultSet rs;
    private PreparedStatement stmt;
    private Connection con;
    private static final String SQL_SELECT = "select * from tb_usuarios";
    private static final String SQL_INSERT = "insert into tb_usuarios (Usuario, Contraseña) values(?, ?)";
    private static final String SQL_UPDATE = "update tb_usuarios set Usuario = ?, Contraseña = ? where  = " + "codigo = ?";
    private static final String SQL_SEARCH = "select * from tb_usuarios where codigo = ?";
    private static final String SQL_DELETE = "delete from tb_usuarios where codigo = ?"; 
    
    @Override
    public int SQLCreate(MdUsuarios x) {
        int crear = 0;
       try{
           con = ClsConexion.getConnection();
           stmt = con.prepareStatement(SQL_INSERT);
           stmt.setString(1, x.getUsername());
           stmt.setString(2, x.getPassword());
           crear = stmt.executeUpdate();
       } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
           // ClsConexion.close(rs);
            ClsConexion.close(stmt);
            ClsConexion.close(con);
       }
       return crear;
    }

    @Override
    public int SQLUpdate(MdUsuarios x) {
        int actualizar = 0;
       try{
           con = ClsConexion.getConnection();
           stmt = con.prepareStatement(SQL_INSERT);
           stmt.setString(1, x.getUsername());
           stmt.setString(2, x.getPassword());
           actualizar = stmt.executeUpdate();
       } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
           // ClsConexion.close(rs);
            ClsConexion.close(stmt);
            ClsConexion.close(con);
       }
       return actualizar;
    }

    @Override
    public int SQLDelete(int key) {
    int eliminar = 0;
        try {
            con = ClsConexion.getConnection();
            stmt = con.prepareStatement(SQL_DELETE);
            stmt.setInt(1, key);
            eliminar = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
           // ClsConexion.close(rs);
            ClsConexion.close(stmt);
            ClsConexion.close(con);
        }
        return eliminar;    
    }

    @Override
    public MdUsuarios SQLSelectOnlyOne(int key) {
    MdUsuarios us = new MdUsuarios();
        try {
            con = ClsConexion.getConnection();
            stmt = con.prepareStatement(SQL_SEARCH);
            stmt.setInt(1, key);
            rs = stmt.executeQuery();
            while(rs.next()){
                us.setId_usuario(rs.getInt("codigo"));
                us.setUsername(rs.getString("Usuario"));
                us.setPassword(rs.getString("Contraseña"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
           // ClsConexion.close(rs);
            ClsConexion.close(stmt);
            ClsConexion.close(con);
        }
        return us;   
    }

    @Override
    public List<MdUsuarios> SQLSelect() {
        List<MdUsuarios> usuarios = new ArrayList<MdUsuarios>();
        try {
            con = ClsConexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            MdUsuarios usuario = null;
            
            while(rs.next()){
                int id_usuario = rs.getInt("codigo");
                String user = rs.getString("Usuario");
                String pass = rs.getString("Contraseña");
                usuario = new MdUsuarios(id_usuario, user, pass);
                usuario.setId_usuario(id_usuario);
                usuario.setUsername(user);
                usuario.setPassword(pass);
                usuarios.add(usuario);      
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            //ClsConexion.close(rs);
            ClsConexion.close(stmt);
            ClsConexion.close(con);
        }
        return usuarios;
    }   
}
