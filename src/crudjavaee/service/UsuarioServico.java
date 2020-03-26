package crudjavaee.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import crudjavaee.model.Usuario;

public class UsuarioServico {

	static HashMap<Integer, Usuario> usuarioIdMap = getUsuarioIdMap();

	public UsuarioServico() {
		super();
		if (usuarioIdMap == null) {
			usuarioIdMap = new HashMap<Integer, Usuario>();

			Usuario usuario = new Usuario(1L, "Carlos Roberto", "123");
			Usuario usuario1 = new Usuario(2L, "Luciene Alves", "456");
			Usuario usuario2 = new Usuario(3L, "Ezequias Alves", "789");

			usuarioIdMap.put(1, usuario);
			usuarioIdMap.put(2, usuario1);
			usuarioIdMap.put(3, usuario2);
		}
	}

	public List<Usuario> getAllUsuarios() {
		List<Usuario> usuarios = new ArrayList<>(usuarioIdMap.values());
		return usuarios;
	}
	
	public List<Usuario> getAllUsuariosPaginados(int inicio, int tamanho){
		
		List<Usuario> usuarios = new ArrayList<>(usuarioIdMap.values());
		if(inicio+tamanho > usuarios.size()) {
			return new ArrayList<Usuario>();
		}
		return usuarios.subList(inicio, inicio+tamanho);
	}

	
	public Usuario getUsuario(int id) {
		Usuario usuario = usuarioIdMap.get(id);
		return usuario;
	}

	public Usuario addUsuario(Usuario usuario) {
		usuario.setId(usuarioIdMap.size() + 1L);
		usuarioIdMap.put(usuario.getId().intValue(), usuario);
		return usuario;
	}

	public Usuario atualizarUsuario(Usuario usuario) {
		if (usuario.getId() <= 0)
			return null;

		usuarioIdMap.put(usuario.getId().intValue(), usuario);
		return usuario;

	}

	public void excluirUsuario(int id) {
		usuarioIdMap.remove(id);
	}

	private static HashMap<Integer, Usuario> getUsuarioIdMap() {
		return usuarioIdMap;
	}

}
