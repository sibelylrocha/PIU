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
import modelo.Produto;
import service.ProdutoService;

@Stateless
@TransactionAttribute(TransactionAttributeType.NEVER)
@Path("Produto")
public class ProdutoResource implements Serializable {

	private static final long serialVersionUID = 4260572209856089896L;
	
	@Inject
	private ProdutoService pdService;

	public ProdutoResource() {}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Produto> listaProdutos() {
		List<Produto> produtos = pdService.listarProduto();
		return produtos;
	}
	
	@GET
	@Path("{Id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ConsultarProdutoPorId(@PathParam("Id") Integer Id) {
		try {
			Produto produto = pdService.ConsultarProdutoPorId(Id);
			
			if(produto != null)
				return Response.ok().entity(produto).build();
			else
				return Response.status(Status.NOT_FOUND).build();
			
		}catch (Exception e) {
			return Response.status(Status.CONFLICT).build();
		}	
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insereProduto(Produto produto){
		try {
			pdService.cadastarProduto(produto);
			URI uri = URI.create("/produtos/" + produto.getId());
			return Response.created(uri).build();
			
		}catch(ValidacaoException ev) {
			return Response.status(422).entity(ev.getViolacoes()).build();
		}catch(Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{Id}")
	@DELETE
	public Response deleteProduto(@PathParam("Id") Integer Id) {
		try {
			boolean success = pdService.removerProduto(Id);
			
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
	public void atualizaProduto(@PathParam("Id") Integer Id, Produto produto) {
		pdService.atualizarUsuario(produto);
	}
}
