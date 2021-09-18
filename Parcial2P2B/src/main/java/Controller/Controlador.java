/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import datos.ClsVendedoresJDBC;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.MdVendedores;
import vista.VendedoresFRM;

/**
 *
 * @author HP
 */
public class Controlador implements ActionListener {

    ClsVendedoresJDBC vende = new ClsVendedoresJDBC();
    MdVendedores vendedor = new MdVendedores();
    VendedoresFRM vista = new VendedoresFRM();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public Controlador (VendedoresFRM v){
        this.vista = v;
        this.vista.btn_listar.addActionListener(this);
        this.vista.btn_crear.addActionListener(this);
        this.vista.btn_editar.addActionListener(this);
        this.vista.btn_actualizar.addActionListener(this);
        this.vista.btn_eliminar1.addActionListener(this);
        listar(vista.TABLA);
    }
    
    public void listar (JTable tabla){
        modelo = (DefaultTableModel)tabla.getModel();
        List<MdVendedores> lista = vende.SQLSelect();
        Object[]object = new Object[7];
        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getCodigo_v();
            object[1] = lista.get(i).getNombre();
            object[2] = lista.get(i).getEnero();
            object[3] = lista.get(i).getFebrero();
            object[4] = lista.get(i).getMarzo();
            object[6] = lista.get(i).getPromedio();
            modelo.addRow(object);
        }
        vista.TABLA.setModel(modelo);
    }
    
    public void crear(){
        int codigo = Integer.parseInt(vista.txt_cod.getText());
        String nombre = vista.txt_name.getText();
        double enero = Double.parseDouble(vista.txt_ene.getText());
        double febrero = Double.parseDouble(vista.txt_feb.getText());
        double marzo = Double.parseDouble(vista.txt_mar.getText());
        vendedor.setCodigo_v(codigo);
        vendedor.setNombre(nombre);
        vendedor.setEnero(enero);
        vendedor.setFebrero(febrero);
        vendedor.setMarzo(marzo);
        int r = vende.SQLCreate(vendedor);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Vendedor creado con exito:)");
        }else{
            JOptionPane.showMessageDialog(vista, "Error al crear");
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btn_listar){
            listar(vista.TABLA);
        }
        if(e.getSource() == vista.btn_crear){
            crear();
            limpiarTabla();
            listar(vista.TABLA);
        }
        if (e.getSource() == vista.btn_editar) {
            editar();
        }
        if (e.getSource() == vista.btn_actualizar) {
            actualizar();
            limpiarTabla();
            listar(vista.TABLA);
        }
        if (e.getSource() == vista.btn_eliminar1) {
            eliminar();
            limpiarTabla();
            listar(vista.TABLA);
        }
    }

    private void actualizar() {
        int codigo = Integer.parseInt(vista.txt_cod.getText());
        String nombre = vista.txt_name.getText();
        double enero = Double.parseDouble(vista.txt_ene.getText());
        double febrero = Double.parseDouble(vista.txt_feb.getText());
        double marzo = Double.parseDouble(vista.txt_mar.getText());
        vendedor.setCodigo_v(codigo);
        vendedor.setNombre(nombre);
        vendedor.setEnero(enero);
        vendedor.setFebrero(febrero);
        vendedor.setMarzo(marzo);
        int r = vende.SQLUpdate(vendedor);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Vendedor actualizado con exito:)");
        }else{
            JOptionPane.showMessageDialog(vista, "Error al actualizar");
        }
    }
    
    private void limpiarTabla(){
        for (int i = 0; i < vista.TABLA.getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
        }
    }

    private void eliminar() {
        int fila = vista.TABLA.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Debe seleccionar un elemento de la tabla");
            }else{
                int codigo = Integer.parseInt((String)vista.TABLA.getValueAt(fila, 0).toString());
                vende.SQLDelete(codigo);
                JOptionPane.showMessageDialog(vista, "Vendedor eliminado con exito");
            }
    }

    private void editar() {
       int fila = vista.TABLA.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Antes de presionar 'Editar' porfavor seleccione una fila");
            }else{
                int codigo = Integer.parseInt((String)vista.TABLA.getValueAt(fila, 0).toString());
                String name = (String)vista.TABLA.getValueAt(fila, 1);
                double ene = Double.parseDouble((String)vista.TABLA.getValueAt(fila, 2).toString());
                double feb = Double.parseDouble((String)vista.TABLA.getValueAt(fila, 3).toString());
                double mar = Double.parseDouble((String)vista.TABLA.getValueAt(fila, 4).toString());
                vista.txt_cod.setText(""+codigo);
                vista.txt_name.setText(name);
                vista.txt_ene.setText(""+ene);
                vista.txt_feb.setText(""+feb);
                vista.txt_mar.setText(""+mar);  
            }
    }
    
    
  
    
}
