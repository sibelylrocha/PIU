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
import modelo.Pagamento;
import service.PagamentoService;

@Stateless
@TransactionAttribute(TransactionAttributeType.NEVER)
@Path("Pagamento")
public class PagamentoResource implements Serializable{

private static final long serialVersionUID = 4260572209856089896L;
	
	@Inject
	private PagamentoService pgService;

	public PagamentoResource() {}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pagamento> listaPagamentos() {
		List<Pagamento> pagamentos = pgService.listarPagamentos();
		return pagamentos;
	}
	
	@GET
	@Path("{Id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ConsultarPagamentoPorId(@PathParam("Id") Integer Id) {
		try {
			Pagamento pagamento = pgService.ConsultarPagamentoPorId(Id);
			
			if(pagamento != null)
				return Response.ok().entity(pagamento).build();
			else
				return Response.status(Status.NOT_FOUND).build();
			
		}catch (Exception e) {
			return Response.status(Status.CONFLICT).build();
		}	
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserePagamento(Pagamento pagamento){
		try {
			pgService.cadastarPagamento(pagamento);
			URI uri = URI.create("/pagamentos/" + pagamento.getId());
			return Response.created(uri).build();
			
		}catch(ValidacaoException ev) {
			return Response.status(422).entity(ev.getViolacoes()).build();
		}catch(Exception e) {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	@Path("{Id}")
	@DELETE
	public Response deletePagamento(@PathParam("Id") Integer Id) {
		try {
			boolean success = pgService.removerPagamento(Id);
			
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
	public void atualizaPagamento(@PathParam("Id") Integer Id, Pagamento pagamento) throws Exception{
		pgService.atualizarPagamento(pagamento);
	}
}
