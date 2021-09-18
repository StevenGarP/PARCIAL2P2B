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
public class MdUsuarios {
    private int id_usuario;
    private String username;
    private String password;
    
    public MdUsuarios(int id){
        this.id_usuario = id;
    }
    
    public MdUsuarios(String user, String pass){
        this.username = user;
        this.password = pass;
    }
    
    public MdUsuarios(int id, String user, String pass){
        this.id_usuario = id;
        this.username = user;
        this.password = pass;
    }
    
    public MdUsuarios () {
        
    }

    @Override
    public String toString() {
        return "MdUsuarios{" + "id_usuario=" + id_usuario + ", username=" + username + ", password=" + password + '}';
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
