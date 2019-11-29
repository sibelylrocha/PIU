package resource;

import java.io.Serializable;
import java.net.URI;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import exception.ValidacaoException;
import modelo.Cliente;
import service.ClienteService;

@Stateless
@TransactionAttribute(TransactionAttributeType.NEVER)
@Path("Cliente")
public class ClienteResource implements Serializable{

	private static final long serialVersionUID = 4260572209856089896L;
	
	@Inject
	private ClienteService clService;

	public ClienteResource() {}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cliente> listaClientes() {
		List<Cliente> Clientes = clService.listarClientes();
		return Clientes;
	}
	
	
	@GET
	@Path("{Cpf}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getClientePorCpf(@PathParam("Cpf") String Cpf) {
		try {
			Cliente cliente = clService.getClientePorCpf(Cpf);
			
			if(cliente != null)
				return Response.ok().entity(cliente).build();
			else
				return Response.status(Status.NOT_FOUND).build();
			
		}catch (Exception e) {
			return Response.status(Status.CONFLICT).build();
		}	
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insereCliente(Cliente cliente){
		try {
			clService.cadastarCliente(cliente);
			URI uri = URI.create("/clientes/" + cliente.getId());
			return Response.created(uri).build();
			
		}catch(ValidacaoException ev) {
			return Response.status(422).entity(ev.getViolacoes()).build();
		}catch(Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{Id}")
	@DELETE
	public Response deleteUsuario(@PathParam("Id") Integer Id) {
		try {
			boolean success = clService.removerCliente(Id);
			
			if(success)
				return Response.ok().build();
			else
				return Response.status(Status.NOT_FOUND).build();
				
		}catch (Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{Id}")
	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	public void atualizaCliente(@PathParam("Id") Integer Id, Cliente cliente) {
		clService.atualizarCliente(cliente);
	}
}
