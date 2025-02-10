package entities;

public class Cliente {
	private Integer id;
	private String nome;
	private String email;
	private Integer telefone;
	
	public Cliente(Integer id, String nome, String email, Integer telefone) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
	}
	
	public Cliente(String nome, String email, Integer telefone) {
		super();
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
	}
	
	public Cliente() {}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public Integer getTelefone() {
		return telefone;
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
