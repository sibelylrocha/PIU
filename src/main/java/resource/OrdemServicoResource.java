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
import modelo.OrdemServico;
import service.OrdemServicoService;

@Stateless
@TransactionAttribute(TransactionAttributeType.NEVER)
@Path("OrdemServico")
public class OrdemServicoResource implements Serializable{

private static final long serialVersionUID = 4260572209856089896L;
	
	@Inject
	private OrdemServicoService orService;

	public OrdemServicoResource() {}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<OrdemServico> listaOrdemServicos() {
		List<OrdemServico> OrdemServicos = orService.listarOrdemServico();
		return OrdemServicos;
	}
	
	@GET
	@Path("{Id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ConsultarOrdemServicoPorId(@PathParam("Id") Integer Id) {
		try {
			OrdemServico ordemservico = orService.ConsultarOrdemServicoPorId(Id);
			
			if(ordemservico != null)
				return Response.ok().entity(ordemservico).build();
			else
				return Response.status(Status.NOT_FOUND).build();
			
		}catch (Exception e) {
			return Response.status(Status.CONFLICT).build();
		}	
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insereOrdemServiso(OrdemServico ordemservico){
		try {
			orService.cadastarOrdemServico(ordemservico);
			URI uri = URI.create("/ordemservicos/" + ordemservico.getId());
			return Response.created(uri).build();
			
		}catch(ValidacaoException ev) {
			return Response.status(422).entity(ev.getViolacoes()).build();
		}catch(Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{Id}")
	@DELETE
	public Response deleteOrdemServico(@PathParam("Id") Integer Id) {
		try {
			boolean success = orService.removerOrdemServico(Id);
			
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
	public void atualizaOrdemServico(@PathParam("Id") Integer Id, OrdemServico ordemservico) {
		orService.atualizarOrdemServico(ordemservico);
	}
}
