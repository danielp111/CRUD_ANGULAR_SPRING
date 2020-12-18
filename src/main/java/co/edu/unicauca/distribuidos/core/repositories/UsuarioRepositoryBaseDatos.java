
package co.edu.unicauca.distribuidos.core.repositories;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.stereotype.Service;

import co.edu.unicauca.distribuidos.core.models.Cliente;
import co.edu.unicauca.distribuidos.core.repositories.conexion.ConexionBD;

/**
 * la clase permite registrar, eliminar, actualizar, consultar y listar datos de los clientes
 * @author: Daniel Eduardo Paz Perafán
 * @version: 09/12/2020 * 
 */
@Service
public class UsuarioRepositoryBaseDatos{
    private final ConexionBD conexionABaseDeDatos;
    
    public UsuarioRepositoryBaseDatos()
    {
        conexionABaseDeDatos= new ConexionBD();
    } 

/**
 * 
 * @author: Daniel Eduardo Paz Perafán
 * @version: 09/12/2020
 * @param cliente El parámetro encapsula la información del cliente a registrar en el sistema
 * @return si el cliente se registro correctamente, el método retorna el cliente con los datos registrados,null en caso contrario
 */       
    public Cliente save(Cliente objCliente) 
    {
    	System.out.println("registrando cliente");
    	Cliente objClienteAlmacenado=null;
    	int resultado=-1;
    	 
    	try { 
    	
    		conexionABaseDeDatos.conectar();
       
                   
            PreparedStatement sentencia = null;
            String consulta = "insert into cliente(cliente.nombre, cliente.apellido, cliente.email, cliente.createAt) values(?,?,?,?)";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);
            
            sentencia.setString(1, objCliente.getNombre());
            sentencia.setString(2, objCliente.getApellido());
            sentencia.setString(3, objCliente.getEmail());
            
            sentencia.setDate(4, new java.sql.Date(objCliente.getCreateAt().getTime()));
            
            resultado = sentencia.executeUpdate(); 
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
                  System.out.println("error en la inserción: "+e.getMessage());         
        }
        
        if(resultado == 1)
        {
        	objClienteAlmacenado=objCliente;
        }
        return objClienteAlmacenado;
    }
       
    public List<Cliente> findAll()
    {
    	System.out.println("listando clientes");
        ArrayList<Cliente> clientes = new ArrayList();
        
        conexionABaseDeDatos.conectar();        
        try {            
            PreparedStatement sentencia = null;
            String consulta = "select * from cliente";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);            
            ResultSet res = sentencia.executeQuery();
            while(res.next()){
	            Cliente objCliente= new Cliente();
	            objCliente.setId(res.getInt("idCliente"));
	            objCliente.setNombre(res.getString("nombre"));
	            objCliente.setApellido(res.getString("apellido"));
	            objCliente.setEmail(res.getString("email"));
	            objCliente.setCreateAt(res.getDate("createAt"));            
	            clientes.add(objCliente);
            }
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
                  System.out.println("error en la inserción: "+e.getMessage());         
        }
        
        return clientes;
    }
           
    public Cliente findById(Integer idCliente)
    {
    	System.out.println("consultar cliente");
    	Cliente objCliente=null;
      
        conexionABaseDeDatos.conectar();        
        try {            
            PreparedStatement sentencia = null;
            String consulta = "select cliente.idCliente, cliente.nombre, cliente.apellido, cliente.email, cliente.createAt from cliente where cliente.idCliente=?";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);            
            sentencia.setInt(1, idCliente);
            ResultSet res = sentencia.executeQuery();
            while(res.next()){
            	System.out.println("cliente encontrado");
            	objCliente= new Cliente();            	
            	objCliente.setId(res.getInt("idCliente"));
                objCliente.setNombre(res.getString("nombre"));
                objCliente.setApellido(res.getString("apellido"));
                objCliente.setEmail(res.getString("email"));
                objCliente.setCreateAt(res.getDate("createAt"));    
            }
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
                  System.out.println("error en la consulta de un empleado: "+e.getMessage());         
        }
        
        return objCliente;
    }
       
    public Cliente update(Integer idCliente, Cliente objCliente)
    {
    	System.out.println("actualizar cliente");
    	Cliente objClienteActualizado=null;
        conexionABaseDeDatos.conectar();
        int resultado=-1;
        try {            
            PreparedStatement sentencia = null;
            String consulta = "update cliente set cliente.nombre=?,"
                                                 + "cliente.apellido=?,"
                                                 + "cliente.email=? ,"
                                                 + "cliente.createAt=? "
                                                 + "where cliente.idCliente=?";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);
            
            
            sentencia.setString(1, objCliente.getNombre());
            sentencia.setString(2, objCliente.getApellido());
            sentencia.setString(3, objCliente.getEmail());
            
            sentencia.setDate(4, new java.sql.Date(objCliente.getCreateAt().getTime()));
            
            sentencia.setInt(5, idCliente);
            resultado = sentencia.executeUpdate(); 
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
                  System.out.println("error en la actualización: "+e.getMessage());         
        }
        
        if(resultado == 1)
        {
        	objClienteActualizado=objCliente;
        }
        return objClienteActualizado;        
       
    }
        
    public boolean delete(Integer idCliente){
    	System.out.println("eliminar cliente");
        conexionABaseDeDatos.conectar();
        int resultado=-1;
        try {            
            PreparedStatement sentencia = null;
            String consulta = "delete from cliente where cliente.idCliente=?";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);            
            sentencia.setInt(1, idCliente);
            resultado = sentencia.executeUpdate(); 
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
                  System.out.println("error en la eliminación: "+e.getMessage());         
        }
        
        return resultado == 1;
    }
}





