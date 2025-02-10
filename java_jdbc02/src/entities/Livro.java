package entities;

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
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.anoPublicado = anoPublicado;
		this.quantidade = quantidade;
	}
	
	
	public Livro() {}

	public Integer getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public Integer getAnoPublicado() {
		return anoPublicado ;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void realizarEmprestimo() {
		this.quantidade -= 1;
	}
	
	@Override
	public String toString() {
		return String.format("Id: %d%nTitulo: %s%nAutor: %s%nAno Publicado: %d%nQuantidade: %d",
				getId(),
				getTitulo(),
				getAutor(),
				getAnoPublicado(),
				getQuantidade()
		);
	}
	
	
	


}
