package br.com.alexandreesl.handson.domain;

public class Cliente {

	private long id;

	private String nome;

	private String email;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object obj) {

		Cliente cliente = (Cliente) obj;

		return id == cliente.getId() ? true : false;
	}

}
