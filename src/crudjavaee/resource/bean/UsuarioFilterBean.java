package crudjavaee.resource.bean;

import javax.ws.rs.QueryParam;

public class UsuarioFilterBean {

	private @QueryParam("inicio") int inicio;
	private @QueryParam("tamanho") int tamanho;

	public int getInicio() {
		return inicio;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

}
