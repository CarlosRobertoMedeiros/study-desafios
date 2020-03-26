package crudjavaee.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import crudjavaee.model.Usuario;
import crudjavaee.service.UsuarioServico;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioController {

	private UsuarioServico usuarioServico = new UsuarioServico();

	@GET
	public List<Usuario> getUsuarios(@QueryParam("inicio") int inicio, 
									 @QueryParam("tamanho") int tamanho ) {
		return usuarioServico.getAllUsuariosPaginados(inicio, tamanho);
	}

	@GET
	@Path("/{id}")
	public Usuario getUsuarioById(@PathParam("id") int id) {
		return usuarioServico.getUsuario(id);
	}

	@POST
	public Usuario adicionarUsuario(Usuario usuario) {
		return usuarioServico.addUsuario(usuario);
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
