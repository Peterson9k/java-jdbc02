package models;

public class Cliente {
	private Integer id;
	private String nome;
	private String email;
	private Long telefone;
	
	public Cliente(int id, String nome, String email, long telefone) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
	}
	
	public Cliente() {}

	public Integer getId() {
		return id;
	}
	
	public void setId(int id) {
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

	public Long getTelefone() {
		return telefone;
	}
	
	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return String.format("Id: %d%nNome: %s%nEmail: %s%nTelefone: %d%n",
				getId(),
				getNome(),
				getEmail(),
				getTelefone()				
		);
	}
	
		
	
}
