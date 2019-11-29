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
import modelo.Peca;
import service.PecaService;

@Stateless
@TransactionAttribute(TransactionAttributeType.NEVER)
@Path("Peca")
public class PecaResource implements Serializable{

private static final long serialVersionUID = 4260572209856089896L;
	
	@Inject
	private PecaService pcService;

	public PecaResource() {}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Peca> listaPecas() {
		List<Peca> pecas = pcService.listarPecas();
		return pecas;
	}
	
	@GET
	@Path("{Id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ConsultarPeca(@PathParam("Id") Integer Id) {
		try {
			Peca peca = pcService.ConsultarPeca(Id);
			
			if(peca != null)
				return Response.ok().entity(peca).build();
			else
				return Response.status(Status.NOT_FOUND).build();
			
		}catch (Exception e) {
			return Response.status(Status.CONFLICT).build();
		}	
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserePeca(Peca peca){
		try {
			pcService.cadastarPeca(peca);
			URI uri = URI.create("/pecas/" + peca.getId());
			return Response.created(uri).build();
			
		}catch(ValidacaoException ev) {
			return Response.status(422).entity(ev.getViolacoes()).build();
		}catch(Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{Id}")
	@DELETE
	public Response deletePeca(@PathParam("Id") Integer Id) {
		try {
			boolean success = pcService.removerPeca(Id);
			
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
	public void atualizaPeca(@PathParam("Id") Integer Id, Peca peca) {
		pcService.atualizarPeca(peca);
	}
}
