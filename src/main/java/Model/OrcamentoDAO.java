package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrcamentoDAO {
    private Connection connection;

    // Construtor: Estabelece a conexão com o banco de dados
    public OrcamentoDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para criar um novo orçamento
    public void criarOrcamento(Orçamento orcamento) throws SQLException {
        String sql = "INSERT INTO Orcamento (projeto, valor, cliente_id, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, orcamento.getProjeto());
            stmt.setFloat(2, orcamento.getValor());
            stmt.setInt(3, orcamento.getClienteId());
            stmt.setString(4, orcamento.getStatus());
            stmt.executeUpdate();
        }
    }

    // Método para atualizar o status de um orçamento
    public void atualizarOrcamento(Orçamento orcamento) throws SQLException {
        String sql = "UPDATE Orcamento SET projeto = ?, valor = ?, cliente_id = ?, status = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, orcamento.getProjeto());
            stmt.setFloat(2, orcamento.getValor());
            stmt.setInt(3, orcamento.getClienteId());
            stmt.setString(4, orcamento.getStatus());
            stmt.setInt(5, orcamento.getId());
            stmt.executeUpdate();
        }
    }

    // Método para excluir um orçamento pelo id
    public void excluirOrcamento(int id) throws SQLException {
        String sql = "DELETE FROM Orcamento WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Método para buscar todos os orçamentos
    public List<Orçamento> listarOrcamentos() throws SQLException {
        List<Orçamento> orcamentos = new ArrayList<>();
        String sql = "SELECT * FROM Orcamento";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Orçamento orcamento = new Orçamento();
                orcamento.setProjeto(rs.getString("projeto"));
                orcamento.setValor(rs.getFloat("valor"));
                orcamento.setClientId(rs.getInt("cliente_id"));
                orcamento.setStatus(rs.getString("status"));
                orcamentos.add(orcamento);
            }
        }
        return orcamentos;
    }

    // Método para buscar um orçamento pelo id
    public Orçamento buscarOrcamentoPorId(int id) throws SQLException {
        Orçamento orcamento = null;
        String sql = "SELECT * FROM Orcamento WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    orcamento = new Orçamento();
                    orcamento.setProjeto(rs.getString("projeto"));
                    orcamento.setValor(rs.getFloat("valor"));
                    orcamento.setClientId(rs.getInt("cliente_id"));
                    orcamento.setStatus(rs.getString("status"));
                }
            }
        }
        return orcamento;
    }
}
