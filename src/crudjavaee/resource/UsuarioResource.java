package crudjavaee.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import crudjavaee.dto.UsuarioDTO;
import crudjavaee.ejb.bussiness.UsuarioBusiness;
import crudjavaee.exception.UsuarioNotFoundException;
import crudjavaee.model.Usuario;
import crudjavaee.resource.bean.UsuarioFilterBean;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

	@Inject
	private UsuarioBusiness usuarioBusiness;


	@GET
	public Response getUsuarios(@BeanParam UsuarioFilterBean filterBean)  {
		List<UsuarioDTO> usuarios = usuarioBusiness.getAllUsuarios(filterBean.getInicio(), filterBean.getTamanho());
		
		if (usuarios.isEmpty()) {
			return Response
				.noContent()
				.build();
		}
		return Response
				.ok(usuarios)
				.build();
	}

	@GET
	@Path("/{id}")
	public Response getUsuario(@PathParam("id") int id) {
		UsuarioDTO usuario = usuarioBusiness.getUsuario(id);
		if(usuario==null) {
			return Response
					.noContent()
					.build();
		}
		return Response
				.ok(usuario)
				.build();
	}

	@POST
	public Response adicionarUsuario(Usuario usuario, @Context UriInfo uriInfo) throws URISyntaxException {

		UsuarioDTO novoUsuario = usuarioBusiness.addUsuario(usuario);
		String newId = String.valueOf(novoUsuario.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		
		return Response.created(uri)
				 .status(Status.CREATED)
				 .entity(novoUsuario)
				 .build();
	}
	
	@PUT
	@Path("/{id}")
	public Response atualizarUsuario(@PathParam("id") int id,  Usuario usuario, @Context UriInfo uriInfo) throws URISyntaxException {
		
		UsuarioDTO meuUsuario = usuarioBusiness.atualizarUsuario(id, usuario);
		URI uri = uriInfo.getAbsolutePathBuilder().path(meuUsuario.getId().toString()).build();
		
		if (meuUsuario==null) {
			return Response
					.status(Status.NOT_FOUND)
					.build();
		}
		return Response.created(uri)
				 .status(Status.OK)
				 .entity(meuUsuario)
				 .build();
	}
	
	@DELETE
	@Path("/{id}")
	public Response excluirUsuario(@PathParam("id") int id) {
		boolean excluiu = usuarioBusiness.excluirUsuario(id);
		if (excluiu) {
			return Response
					 .status(Status.OK)
					 .build();
		}
		else {
			return Response
					.status(Status.NOT_FOUND)
					.build();
		}
	}
}
