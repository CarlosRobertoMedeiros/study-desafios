package crudjavaee.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import crudjavaee.dto.UsuarioDTO;
import crudjavaee.model.Usuario;

@Stateless
public class UsuarioDAO {

	@PersistenceContext
	private EntityManager manager;

	public List<UsuarioDTO> listarUsuarios(int inicio, int tamanho) {
		List<Usuario> usuarios = manager.createQuery("select u from Usuario u", Usuario.class)
				.setFirstResult(inicio)
				.setMaxResults(tamanho)
				.getResultList();

		List<UsuarioDTO> usuariosDTO = new ArrayList<>();

		usuarios.forEach(
				usuario -> usuariosDTO.add(new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getUsuario())));

		return usuariosDTO;
	}

	public UsuarioDTO listarUsuario(int id) {
		Long idInterno = new Long(id);
		
		try {
			Usuario usuario = manager.createQuery("select u from Usuario u where u.id=:idInterno", Usuario.class)
				.setParameter("idInterno", idInterno)
				.getSingleResult();

			return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getUsuario());
		}catch (RuntimeException e) {
			UsuarioDTO usuarioDto = null;
			return usuarioDto;
		}
	}

	public UsuarioDTO adicionarUsuario(Usuario usuario) {
		Usuario novoUsuario = manager.merge(usuario);
		return new UsuarioDTO(novoUsuario.getId(), novoUsuario.getNome(), novoUsuario.getUsuario());
	}

	public UsuarioDTO atualizarUsuario(int id, Usuario usuario) {
		Long idInterno = new Long(id);
		Usuario usuarioExistente = manager.find(Usuario.class, idInterno);
		
		if (usuarioExistente==null) {
			UsuarioDTO usuarioDTO = null;
			return usuarioDTO;
		} 
		
		usuarioExistente.setNome(usuario.getNome());
		usuarioExistente.setUsuario(usuario.getUsuario());
		usuarioExistente.setSenha(usuario.getSenha());
		usuarioExistente = manager.merge(usuarioExistente);
		return new UsuarioDTO(usuarioExistente.getId(), usuarioExistente.getNome(), usuarioExistente.getUsuario());
	}

	public boolean excluirUsuario(int id) {
		Long idInterno = new Long(id);
		Usuario usuarioExistente = manager.find(Usuario.class, idInterno);
		if (usuarioExistente==null) {
			return false;
		}
		manager.remove(usuarioExistente);
		return true;
	}
}
