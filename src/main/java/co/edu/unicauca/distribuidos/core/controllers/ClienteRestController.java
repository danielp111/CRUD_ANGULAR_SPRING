

package co.edu.unicauca.distribuidos.core.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.distribuidos.core.models.Cliente;
import co.edu.unicauca.distribuidos.core.services.IClienteService;
import soap_corba.Notificaciones;
import soap_corba.NotificacionesHelper;
import soap_corba.NotificacionesPackage.clienteDTO;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;


@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;
	Notificaciones ref;
	
	@GetMapping("/clientes")
	public List<Cliente> index() {
		return clienteService.findAll();
	}
		
	@GetMapping("/clientes/{id}")
	public Cliente show(@PathVariable Integer id) {
		Cliente objCliente = null;		
		objCliente = clienteService.findById(id);		
		return objCliente;
	}
	
	
	@PostMapping("/clientes")
	public Cliente create(@RequestBody Cliente cliente) {	
		Cliente objCliente = null;
		objCliente =  clienteService.save(cliente);
		//obtenerReferenciaRemota();
        //enviarPeticionServidor(objCliente.getNombre(), objCliente.getApellido());		
		return objCliente;
	}
	

	@PutMapping("/clientes/{id}")
	public Cliente update(@RequestBody Cliente cliente, @PathVariable Integer id) {
		Cliente objCliente = null;
		Cliente clienteActual = clienteService.findById(id);
		if(clienteActual!=null)	
		{
			objCliente = clienteService.update(id,cliente);
		}
		return objCliente;
	}
	
	
	@DeleteMapping("/clientes/{id}")
	public Boolean delete(@PathVariable Integer id) {				
		Boolean bandera=false;
		Cliente clienteActual = clienteService.findById(id);
		if(clienteActual!=null)		
		{
			bandera = clienteService.delete(id);
		}
		
		return bandera;
		
	}
	
	void  enviarPeticionServidor(String nombres, String apellidos)
    {
        clienteDTO objCliente= new clienteDTO(nombres, apellidos);
        ref.enviarNotificacionRegistroPaciente(objCliente);//invocando el método remoto
    }
    
    void obtenerReferenciaRemota()
    {
         try {
            String[] vec = new String[4];
            vec[0] = "-ORBInitialPort";            
            vec[1] = "localhost";
            vec[2] = "-ORBInitialPort";            
            vec[3] = "2020";
            // crea e inicia el ORB
            ORB orb = ORB.init(vec, null);
            // obtiene la base del naming context
            org.omg.CORBA.Object objRef
                    = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            // *** Resuelve la referencia del objeto en el N_S ***
            String name = "objNotificacion";
            ref = NotificacionesHelper.narrow(ncRef.resolve_str(name));            
        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace(System.out);
        }
    }
    
			
}
