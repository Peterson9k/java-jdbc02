package models;


public class Livro {
	// localDate
	private Integer id;
	private String titulo;
	private String autor;
	private Integer anoPublicado;
	private Integer quantidade;
	
	public Livro(Integer id, String titulo, String autor, Integer anoPublicado, Integer quantidade) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.anoPublicado = anoPublicado;
		this.quantidade = quantidade;
	}
	
	
	public Livro(String titulo, String autor, Integer anoPublicado, Integer quantidade) {
		this.titulo = titulo;
		this.autor = autor;
		this.anoPublicado = anoPublicado;
		this.quantidade = quantidade;
	}
	
	public Livro() {}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Integer getAnoPublicado() {
		return anoPublicado;
	}

	public void setAnoPublicado(Integer anoPublicado) {
		this.anoPublicado = anoPublicado;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public void realizarEmprestimo() {
		this.quantidade -= 1;
	}
	
	public void realizarDevolucao() {
		this.quantidade +=1;
	}
	
	
	
	public String toStringUser() {
		return String.format("%nTitulo: %s%nAutor: %s%nAno publicado: %d%nQuantidade: %d%n",
						getTitulo(),
						getAutor(),
						getAnoPublicado(),
						getQuantidade());
	}
	
	@Override
	public String toString() {
		return String.format("%nId: %d%nTitulo: %s%nAutor: %s%nAno Publicado: %d%nQuantidade: %d%n",
				getId(),
				getTitulo(),
				getAutor(),
				getAnoPublicado(),
				getQuantidade()
		);
	}
	
	
	


}
