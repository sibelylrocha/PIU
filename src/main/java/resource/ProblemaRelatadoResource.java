package resource;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

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
import modelo.ProblemaRelatado;
import service.ProblemaRelatadoService;

public class ProblemaRelatadoResource implements Serializable{

private static final long serialVersionUID = 4260572209856089896L;
	
	@Inject
	private ProblemaRelatadoService pbService;

	public ProblemaRelatadoResource() {}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProblemaRelatado> listaProblemasRelatados() {
		List<ProblemaRelatado> problemasrelatados = pbService.listarProblemasRelatados();
		return problemasrelatados;
	}
	
	@GET
	@Path("{Protocolo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ConsultarProblemaRelatado(@PathParam("Protocolo") Integer Protocolo) {
		try {
			ProblemaRelatado problemarelatado = pbService.ConsultarProblemaRelatado(Protocolo);
			
			if(problemarelatado != null)
				return Response.ok().entity(problemarelatado).build();
			else
				return Response.status(Status.NOT_FOUND).build();
			
		}catch (Exception e) {
			return Response.status(Status.CONFLICT).build();
		}	
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insereProblemaRelatado(ProblemaRelatado problemarelatado){
		try {
			pbService.cadastarProblemaRelatado(problemarelatado);
			URI uri = URI.create("/problemasrelatados/" + problemarelatado.getProtocolo());
			return Response.created(uri).build();
			
		}catch(ValidacaoException ev) {
			return Response.status(422).entity(ev.getViolacoes()).build();
		}catch(Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{Protocolo}")
	@DELETE
	public Response deleteProblemaRelatado(@PathParam("Protocolo") Integer Protocolo) {
		try {
			boolean success = pbService.removerProblemaRelatado(Protocolo);
			
			if(success)
				return Response.ok().build();
			else
				return Response.status(Status.NOT_FOUND).build();
				
		}catch (Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{Protocolo}")
	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	public void atualizaProblemaRelatado(@PathParam("Protocolo") Integer Protocolo, ProblemaRelatado problemarelatado) {
		pbService.atualizarProblemaRelatado(problemarelatado);
	}
}
