package crudjavaee.ejb.bussiness;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import crudjavaee.dao.UsuarioDAO;
import crudjavaee.dto.UsuarioDTO;
import crudjavaee.model.Usuario;

@Stateless
public class UsuarioBusiness {

	@Inject
	private UsuarioDAO usuarioDAO;
	static HashMap<Integer, Usuario> usuarioIdMap = getUsuarioIdMap();

	public UsuarioBusiness() {
		super();
		if (usuarioIdMap == null) {
			usuarioIdMap = new HashMap<Integer, Usuario>();

			Usuario usuario =  new Usuario(1L,"Carlos Roberto","123","A");
			Usuario usuario1 = new Usuario(2L,"Luciene Alves", "456","A");
			Usuario usuario2 = new Usuario(3L,"Ezequias Alves","789","A");

			usuarioIdMap.put(1, usuario);
			usuarioIdMap.put(2, usuario1);
			usuarioIdMap.put(3, usuario2);
		}
	}

	public List<UsuarioDTO> getAllUsuarios(int inicio, int tamanho) {
		return usuarioDAO.listarUsuarios(inicio, tamanho);
	}
	
	public UsuarioDTO getUsuario(int id) {
		return usuarioDAO.listarUsuario(id);
	}

	public UsuarioDTO addUsuario(Usuario usuario) {
		UsuarioDTO novoUsuario = usuarioDAO.adicionarUsuario(usuario);
		return novoUsuario;
	}

	public UsuarioDTO atualizarUsuario(int id, Usuario usuario) {
		UsuarioDTO usuarioAtualizado = usuarioDAO.atualizarUsuario(id, usuario);
		return usuarioAtualizado;
	}

	public boolean excluirUsuario(int id) {
		return usuarioDAO.excluirUsuario(id);
		
	}

	private static HashMap<Integer, Usuario> getUsuarioIdMap() {
		return usuarioIdMap;
	}

}
