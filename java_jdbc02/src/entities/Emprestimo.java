package entities;

import java.time.LocalDate;

public class Emprestimo {
	private Integer id;
	private Integer livroId;
	private Integer clienteId;
	private LocalDate dateEmprestimo;
	private LocalDate dateDevoluçao;
	
	public Emprestimo(Livro livroId, Cliente clienteId, LocalDate dateEmprestimo,
			LocalDate dateDevoluçao) {
		this.livroId = livroId.getId();
		this.clienteId = clienteId.getId();
		this.dateEmprestimo = dateEmprestimo;
		this.dateDevoluçao = dateDevoluçao;
	}
	
	public Integer getId() {
		return id;
	}

	public Integer getLivroId() {
		return livroId;
	}

	public Integer getClienteId() {
		return clienteId;
	}

	public LocalDate getDateEmprestimo() {
		return dateEmprestimo;
	}

	public LocalDate getDateDevoluçao() {
		return dateDevoluçao;
	}

	@Override
	public String toString() {
		return String.format("Id: %d%nLivro id: %d%nCliente id: %d%nData do Emprestimo: %d%nData da Devoluçao: %d",
				getId(),
				getLivroId(),
				getClienteId(),
				getDateEmprestimo(),
				getDateDevoluçao()
		);
	}
	
	
}
