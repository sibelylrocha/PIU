package resource;

import java.io.Serializable;
import java.net.URI;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import exception.ValidacaoException;
import modelo.Estoque;
import service.EstoqueService;

@Stateless
@TransactionAttribute(TransactionAttributeType.NEVER)
@Path("Estoque")
public class EstoqueResource implements Serializable{

private static final long serialVersionUID = 4260572209856089896L;
	
	@Inject
	private EstoqueService etService;

	public EstoqueResource() {}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insereEstoque(Estoque estoque){
		try {
			etService.cadastarEstoque(estoque);
			URI uri = URI.create("/estoques/" + estoque.getId());
			return Response.created(uri).build();
			
		}catch(ValidacaoException ev) {
			return Response.status(422).entity(ev.getViolacoes()).build();
		}catch(Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{Id}")
	@DELETE
	public Response deleteEstoque(@PathParam("Id") Integer Id) {
		try {
			boolean success = etService.removerEstoque(Id);
			
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
	public void atualizaEstoque(@PathParam("Id") Integer Id, Estoque estoque) throws Exception{
		etService.atualizarEstoque(estoque);
	}
}
