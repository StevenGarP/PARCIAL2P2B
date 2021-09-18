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
import modelos.MdVendedores;

/**
 *
 * @author HP
 */
public class ClsVendedoresJDBC implements Interface_VendedoresJDBC<MdVendedores>{
        private ResultSet rs;
    private PreparedStatement stmt;
    private Connection con;
    private static final int TRES = 3;
    private static final String SQL_SELECT = "select * from tb_vendedores";
    private static final String SQL_INSERT = "insert into tb_vendedores (Codigo, Nombre, Enero, Febrero, Marzo, Promedio) values(?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "update tb_vendedores set Nombre = ?, Enero = ?, Febrero = ?, Marzo = ?, Promedio = ? where Codigo = ?";
    private static final String SQL_SEARCH = "select * from tb_vendedores where Codigo = ?";
    private static final String SQL_DELETE = "delete from tb_vendedores where Codigo = ?"; 

    @Override
    public int SQLCreate(MdVendedores x) {
        int crear = 0;
        try{
            con = ClsConexion.getConnection();
            stmt = con.prepareStatement(SQL_INSERT);
            stmt.setInt(1, x.getCodigo_v());
            stmt.setString(2, x.getNombre());
            stmt.setDouble(3, x.getEnero());
            stmt.setDouble(4, x.getFebrero());
            stmt.setDouble(5, x.getMarzo());
            stmt.setDouble(6, (x.getEnero() + x.getFebrero() + x.getMarzo()));
            stmt.setDouble(7, (x.getEnero() + x.getFebrero() + x.getMarzo() / TRES));
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
    public int SQLUpdate(MdVendedores x) {
        int actualizar = 0;
        try{
            con = ClsConexion.getConnection();
            stmt = con.prepareStatement(SQL_UPDATE);
            stmt.setString(1, x.getNombre());
            stmt.setDouble(2, x.getEnero());
            stmt.setDouble(3, x.getFebrero());
            stmt.setDouble(4, x.getMarzo());
            stmt.setDouble(5, (x.getEnero() + x.getFebrero() + x.getMarzo()));
            stmt.setDouble(6, (x.getEnero() + x.getFebrero() + x.getMarzo() / TRES));
            stmt.setInt(7, x.getCodigo_v());
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
    public MdVendedores SQLSelectOnlyOne(int key) {
        MdVendedores al = new MdVendedores();
        try {
            con = ClsConexion.getConnection();
            stmt = con.prepareStatement(SQL_SEARCH);
            stmt.setInt(1, key);
            rs = stmt.executeQuery();
            while(rs.next()){
                al.setCodigo_v(rs.getInt("Codigo"));
                al.setNombre(rs.getString("Nombre"));
                al.setEnero(rs.getDouble("Enero"));
                al.setFebrero(rs.getDouble("Febrero"));
                al.setMarzo(rs.getDouble("Marzo"));
                al.setPromedio(rs.getDouble("Promedio"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
           // ClsConexion.close(rs);
            ClsConexion.close(stmt);
            ClsConexion.close(con);
        }
        return al;
    }

    @Override
    public List<MdVendedores> SQLSelect() {
        List<MdVendedores> vendedores = new ArrayList<MdVendedores>();
        try {
            con = ClsConexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            MdVendedores vendedor = null;
            
            while(rs.next()){
                int codigo_v = rs.getInt("Codigo");
                String nombre = rs.getString("Nombre");
                Double enero = rs.getDouble("Enero");
                Double febrero = rs.getDouble("Febrero");
                Double marzo = rs.getDouble("Marzo");
                Double promedio = rs.getDouble("Promedio");
                vendedor = new MdVendedores(codigo_v, nombre, enero, febrero, marzo, promedio);
                vendedor.setCodigo_v(codigo_v);
                vendedor.setNombre(nombre);
                vendedor.setEnero(enero);
                vendedor.setFebrero(febrero);
                vendedor.setPromedio(promedio);
                vendedores.add(vendedor);      
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            //ClsConexion.close(rs);
            ClsConexion.close(stmt);
            ClsConexion.close(con);
        }
        return vendedores;
    
}
}
