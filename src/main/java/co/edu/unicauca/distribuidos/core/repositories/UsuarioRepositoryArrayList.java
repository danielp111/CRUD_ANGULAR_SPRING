

package co.edu.unicauca.distribuidos.core.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import co.edu.unicauca.distribuidos.core.models.Cliente;

@Service
public class UsuarioRepositoryArrayList {
	
	private ArrayList<Cliente> listaDeClientes;
	
	public UsuarioRepositoryArrayList()
	{
		this.listaDeClientes= new ArrayList<Cliente>();
		cargarClientes();
	}
	
   public List<Cliente> findAll()
   {
	   System.out.println("Invocando a listarclientes");
	   return this.listaDeClientes;	
   }
	
   public Cliente findById(Integer id)
   {
	   System.out.println("Invocando a consultar un cliente");
		Cliente objCliente=null;
		
		for (Cliente cliente : listaDeClientes) {
			if(cliente.getId()==id)
			{
				objCliente=cliente;
				break;
			}
		}
		
		return objCliente;
	}
	
	public Cliente save(Cliente cliente)	
	{
		 System.out.println("Invocando a almacenar cliente");
		 Cliente objCliente=null;
		 if (this.listaDeClientes.add(cliente))
		 {
			 objCliente=cliente;
		 }
		 
		 return objCliente;
	}
	
	public Cliente update(Integer id, Cliente cliente)
	{
		 System.out.println("Invocando a actualizar un cliente");
		 Cliente objCliente=null;
		 
		 for (int i = 0; i < this.listaDeClientes.size(); i++) {
			if(this.listaDeClientes.get(i).getId()==id)
			{				
				this.listaDeClientes.set(i,cliente);
				objCliente=cliente;
				break;
			}
		}
		 
		 return objCliente;
	}
	
	public boolean delete(Integer id)
	{
		System.out.println("Invocando a eliminar un cliente");
		boolean bandera=false;
		 
		 for (int i = 0; i < this.listaDeClientes.size(); i++) {
			if(this.listaDeClientes.get(i).getId()==id)
			{
				this.listaDeClientes.remove(i);
				bandera=true;
				break;
			}
		}
		 
		 return bandera;
	}
	
	private void cargarClientes()
	{
		Cliente objCliente1= new Cliente(1, "Juan", "Perez", "juan@unicauca.edu.co", new Date());
		this.listaDeClientes.add(objCliente1);
		Cliente objCliente2= new Cliente(2, "Catalina", "Lopez", "catalina@unicauca.edu.co", new Date());
		this.listaDeClientes.add(objCliente2);
		Cliente objCliente3= new Cliente(3, "Sandra", "Sanchez", "Sandra@unicauca.edu.co", new Date());
		this.listaDeClientes.add(objCliente3);
		
	}

}
