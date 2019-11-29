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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import exception.ValidacaoException;
import modelo.Empresa;
import service.EmpresaService;

@Stateless
@TransactionAttribute(TransactionAttributeType.NEVER)
@Path("Empresa")
public class EmpresaResource implements Serializable{

private static final long serialVersionUID = 4260572209856089896L;
	
	@Inject
	private EmpresaService emService;

	public EmpresaResource() {}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Empresa> listaEmpresas() {
		List<Empresa> empresas = emService.listarEmpresas();
		return empresas;
	}
	
	@GET
	@Path("{Cnpj}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ConsultarEmpresa(@PathParam("Cnpj") String Cnpj) {
		try {
			Empresa empresa = emService.ConsultarEmpresa(Cnpj);
			
			if(empresa != null)
				return Response.ok().entity(empresa).build();
			else
				return Response.status(Status.NOT_FOUND).build();
			
		}catch (Exception e) {
			return Response.status(Status.CONFLICT).build();
		}	
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insereEmpresa(Empresa empresa){
		try {
			emService.cadastarEmpresa(empresa);
			URI uri = URI.create("/empresas/" + empresa.getCnpj());
			return Response.created(uri).build();
			
		}catch(ValidacaoException ev) {
			return Response.status(422).entity(ev.getViolacoes()).build();
		}catch(Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{Cnpj}")
	@DELETE
	public Response deleteEmpresa(@PathParam("Cnpj") String Cnpj) {
		try {
			boolean success = emService.removerEmpresa(Cnpj);
			
			if(success)
				return Response.ok().build();
			else
				return Response.status(Status.NOT_FOUND).build();
				
		}catch (Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{Cnpj}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void trocaEmpresa(@PathParam("Cnpj") String Cnpj, Empresa empresa) {
		emService.trocaEmpresa(Cnpj, empresa);
	}
	
	@Path("{Cnpj}")
	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	public void atualizaEmpresa(@PathParam("Cnpj") String Cnpj, Empresa empresa) {
		emService.atualizarEmpresa(empresa);
	}
}
