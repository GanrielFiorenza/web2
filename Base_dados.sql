CREATE DATABASE MeuBanco;
USE MeuBanco;

-- Tabela Clientes
CREATE TABLE Clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    idade INT,
    sexo ENUM('Masculino', 'Feminino', 'Outro') NOT NULL,
    contato VARCHAR(50)
);

-- Tabela Orçamento
CREATE TABLE Orcamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    projeto VARCHAR(100) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    cliente_id INT,
    status ENUM('Pendente', 'Aprovado', 'Rejeitado') DEFAULT 'Pendente',
    FOREIGN KEY (cliente_id) REFERENCES Clientes(id)
);

-- Inserindo dados na tabela Clientes
INSERT INTO Clientes (nome, idade, sexo, contato) VALUES 
('Ana Silva', 29, 'Feminino', 'ana.silva@email.com'),
('Carlos Santos', 35, 'Masculino', 'carlos.santos@email.com'),
('Mariana Costa', 40, 'Feminino', 'mariana.costa@email.com'),
('Pedro Almeida', 28, 'Masculino', 'pedro.almeida@email.com');

-- Inserindo dados na tabela Orçamento
INSERT INTO Orcamento (projeto, valor, cliente_id, status) VALUES 
('Website', 5000.00, 1, 'Aprovado'),
('Aplicativo Mobile', 12000.00, 2, 'Pendente'),
('Sistema de Gestão', 15000.00, 3, 'Rejeitado'),
('E-commerce', 10000.00, 4, 'Aprovado');
