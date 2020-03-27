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
		Usuario usuario = manager.createQuery("select u from Usuario u where u.id=:idInterno", Usuario.class)
				.setParameter("idInterno", idInterno)
				.getSingleResult();
	
		return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getUsuario());
	}
}
