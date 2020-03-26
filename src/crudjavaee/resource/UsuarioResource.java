package crudjavaee.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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

import crudjavaee.exception.DataNotFoundException;
import crudjavaee.model.Usuario;
import crudjavaee.resource.bean.UsuarioFilterBean;
import crudjavaee.service.UsuarioServico;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

	private UsuarioServico usuarioServico = new UsuarioServico();


	@GET
	public List<Usuario> getUsuarios(@BeanParam UsuarioFilterBean filterBean) {
		return usuarioServico.getAllUsuariosPaginados(filterBean.getInicio(), filterBean.getTamanho());
	}

	@GET
	@Path("/{id}")
	public Usuario getUsuario(@PathParam("id") int id) {
		Usuario usuario = usuarioServico.getUsuario(id);
		if(usuario==null) {
			throw new DataNotFoundException("Mensagem com id " + id + " não encontrada !");
		}
		return usuario;
	}

	//@MatrixParam
	//@HeaderParam
	//@CookieParam
	@POST
	public Response adicionarUsuario(Usuario usuario, @Context UriInfo uriInfo) throws URISyntaxException {

		Usuario novoUsuario = usuarioServico.addUsuario(usuario);
		String newId = String.valueOf(novoUsuario.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri)
				 .status(Status.CREATED)
				 .entity(novoUsuario)
				 .build();
	}
	
	@PUT
	@Path("/{id}")
	public Usuario atualizarUsuario(@PathParam("id") long id,  Usuario usuario) {
		usuario.setId(id);
		return usuarioServico.atualizarUsuario(usuario);
	}
	
	@DELETE
	@Path("/{id}")
	public void excluirUsuario(@PathParam("id") int id) {
		usuarioServico.excluirUsuario(id);
	}
	

}
