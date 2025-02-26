package models;

import java.time.LocalDate;

public class Emprestimo {
	private Integer id;
	private Integer livroId;
	private Integer clienteId;
	private LocalDate dateEmprestimo;
	private LocalDate dateDevolucao;
		
	public Emprestimo(int id, int livroId, int clientId, LocalDate dateEmprestimo, LocalDate dateDevolucao) {
		this.id = id;
		this.livroId = livroId;
		this.clienteId = clientId;
		this.dateEmprestimo = dateEmprestimo;
		this.dateDevolucao = dateDevolucao;
	
	}
	
	public Emprestimo(int livroId, int clientId, LocalDate dateEmprestimo, LocalDate dateDevolucao) {
		this.livroId = livroId;
		this.clienteId = clientId;
		this.dateEmprestimo = dateEmprestimo;
		this.dateDevolucao = dateDevolucao;
	
	}
	
	public Emprestimo() {}
	


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLivroId() {
		return livroId;
	}

	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}

	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}

	public LocalDate getDateEmprestimo() {
		return dateEmprestimo;
	}

	public void setDateEmprestimo(LocalDate dateEmprestimo) {
		this.dateEmprestimo = dateEmprestimo;
	}

	public LocalDate getDateDevolucao() {
		return dateDevolucao;
	}

	public void setDateDevolucao(LocalDate dateDevolucao) {
		this.dateDevolucao = dateDevolucao;
	}

	public String toStringUser() {
						
		return String.format("Realizou Emprestimo: %s%n Devolver Emprestimo: %s%n", 
				getDateEmprestimo().toString(), 
				getDateDevolucao().toString());
	}
	
	@Override
	public String toString() {
		return String.format("Id: %d%nLivro id: %d%nCliente id: %d%nData do Emprestimo: %s%nData da Devoluçao: %s%n",
				getId(),
				getLivroId(),
				getClienteId(),
				getDateEmprestimo().toString(),
				getDateDevolucao().toString()
		);
	}
	
	
}
