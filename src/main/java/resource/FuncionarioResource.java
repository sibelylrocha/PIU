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
import model.Funcionario;
import service.FuncionarioService;

@Stateless
@TransactionAttribute(TransactionAttributeType.NEVER)
@Path("funcionarios") // verificar
public class FuncionarioResource implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FuncionarioService usService;

	public FuncionarioResource() {}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Funcionario> listaFuncionarios() {
		List<Funcionario> funcionarios = usService.listarFuncionarios();
		return funcionarios;
	}
	
	@GET
	@Path("{codigo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFuncionarioPorCodigo(@PathParam("codigo") Integer codigo) {
		try {
			Funcionario funcionario = usService.getFuncionarioPorCodigo(codigo);
			
			if(funcionario != null)
				return Response.ok().entity(funcionario).build();
			else
				return Response.status(Status.NOT_FOUND).build();
			
		}catch (Exception e) {
			return Response.status(Status.CONFLICT).build();
		}	
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insereFuncionario(Funcionario funcionario){
		try {
			usService.cadastarFuncionario(funcionario);
			URI uri = URI.create("/funcionarios/" + funcionario.getCodigo());
			return Response.created(uri).build();
			
		}catch(ValidacaoException ev) {
			return Response.status(422).entity(ev.getViolacoes()).build();
		}catch(Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{codigo}")
	@DELETE
	public Response deleteFuncionario(@PathParam("codigo") Integer codigo) {
		try {
			boolean success = usService.removerFuncionario(codigo);
			
			if(success)
				return Response.ok().build();
			else
				return Response.status(Status.NOT_FOUND).build();
				
		}catch (Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{codigo}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void trocaUsuario(@PathParam("codigo") Integer codigo, Funcionario funcionario) {
		usService.trocaFuncionario(codigo, funcionario);
	}
	
	@Path("{codigo}")
	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	public void atualizaFuncionario(@PathParam("codigo") Integer codigo, Funcionario funcionario) {
		usService.atualizarFuncionario(codigo, funcionario);
	}
}
