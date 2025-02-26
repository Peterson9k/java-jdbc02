
CREATE TABLE clientes(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    telefone BIGINT
);



INSERT INTO 
    clientes 
VALUES 
    (1, "adm", "adm@gmail.com", 11999990000);

CREATE TABLE livros(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(150) NOT NULL UNIQUE,
    autor VARCHAR(150) NOT NULL,
    ano_publicacao DATE,
    quantidade INTEGER DEFAULT 0
);

-- modificaçao tabela(livros) coluna (ano_publicaçao): valor de DATE para INTEGER
ALTER TABLE livros MODIFY COLUMN ano_publicacao INTEGER;

CREATE TABLE emprestimo(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    livro_id INTEGER,
    cliente_id INTEGER,
    data_emprestimo DATE,
    data_devolucao DATE,
    Foreign Key (livro_id) REFERENCES livros(id),
    Foreign Key (cliente_id) REFERENCES clientes(id) 
);
