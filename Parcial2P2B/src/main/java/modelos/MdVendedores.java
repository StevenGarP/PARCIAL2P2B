/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author HP
 */
public class MdVendedores {
    private int codigo_v;
    private String nombre;
    private double enero;
    private double febrero;
    private double marzo;
    private double promedio;
    
    public MdVendedores (int cod, String nom, double ene, double feb, double mar, double prom){
        this.codigo_v = cod;
        this.nombre = nom;
        this.enero = ene;
        this.febrero = feb;
        this.marzo = mar;
        this.promedio = prom;
    }
    
      public MdVendedores (String nom, double ene, double feb, double mar, double prom){
        this.nombre = nom;
        this.enero = ene;
        this.febrero = feb;
        this.marzo = mar;
        this.promedio = prom;
    }
      
      public MdVendedores (int cod, String nom, double ene, double feb, double mar){
          this.codigo_v = cod;
          this.nombre = nom;
          this.enero = ene;
          this.febrero = feb;
          this.marzo = mar;
      }

    public MdVendedores() {
    }
      

    @Override
    public String toString() {
        return "ClsVendedores{" + "codigo_v=" + getCodigo_v() + ", nombre=" + getNombre() + ", enero=" + getEnero() + ", febrero=" + getFebrero() + ", marzo=" + getMarzo() + ", total=" + "promedio=" + getPromedio() +'}';
    }

    /**
     * @return the codigo_v
     */
    public int getCodigo_v() {
        return codigo_v;
    }

    /**
     * @param codigo_v the codigo_v to set
     */
    public void setCodigo_v(int codigo_v) {
        this.codigo_v = codigo_v;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the enero
     */
    public double getEnero() {
        return enero;
    }

    /**
     * @param enero the enero to set
     */
    public void setEnero(double enero) {
        this.enero = enero;
    }

    /**
     * @return the febrero
     */
    public double getFebrero() {
        return febrero;
    }

    /**
     * @param febrero the febrero to set
     */
    public void setFebrero(double febrero) {
        this.febrero = febrero;
    }

    /**
     * @return the marzo
     */
    public double getMarzo() {
        return marzo;
    }

    /**
     * @param marzo the marzo to set
     */
    public void setMarzo(double marzo) {
        this.marzo = marzo;
    }

    /**
     * @return the promedio
     */
    public double getPromedio() {
        return promedio;
    }

    /**
     * @param promedio the promedio to set
     */
    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }
}
