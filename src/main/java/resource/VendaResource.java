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
import modelo.Venda;
import service.VendaService;

@Stateless
@TransactionAttribute(TransactionAttributeType.NEVER)
@Path("Venda")
public class VendaResource implements Serializable{

private static final long serialVersionUID = 4260572209856089896L;
	
	@Inject
	private VendaService vdService;

	public VendaResource() {}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Venda> listaVendas() {
		List<Venda> vendas = vdService.listarVendas();
		return vendas;
	}
	
	@GET
	@Path("{Id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ConsultarVenda(@PathParam("Id") Integer Id) {
		try {
			Venda venda = vdService.ConsultarVenda(Id);
			
			if(venda != null)
				return Response.ok().entity(venda).build();
			else
				return Response.status(Status.NOT_FOUND).build();
			
		}catch (Exception e) {
			return Response.status(Status.CONFLICT).build();
		}	
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insereVenda(Venda venda){
		try {
			vdService.cadastarVenda(venda);;
			URI uri = URI.create("/vendas/" + venda.getId());
			return Response.created(uri).build();
			
		}catch(ValidacaoException ev) {
			return Response.status(422).entity(ev.getViolacoes()).build();
		}catch(Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{Id}")
	@DELETE
	public Response deleteVenda(@PathParam("Id") Integer Id) {
		try {
			boolean success = vdService.removerVenda(Id);
			
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
	public void atualizaVenda(@PathParam("Id") Integer Id, Venda venda) {
		vdService.atualizarUsuario(venda);
	}
}
