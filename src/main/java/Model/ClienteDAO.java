package Model;

import Model.ClienteDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private Connection connection;

    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para inserir um novo cliente
    public void inserirCliente(ClienteDTO cliente) throws SQLException {
        String sql = "INSERT INTO Clientes (nome, idade, sexo, contato) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setInt(2, cliente.getIdade());
            stmt.setString(3, cliente.getSexo());
            stmt.setString(4, cliente.getContato());
            stmt.executeUpdate();
        }
    }

    // Método para listar todos os clientes
 // Método para listar clientes
    public List<ClienteDTO> listarClientes() {
        List<ClienteDTO> clientes = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Inicialize a conexão aqui
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MeuBanco", "usuario", "senha");

            String sql = "SELECT * FROM Clientes";
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ClienteDTO cliente = new ClienteDTO();
                cliente.setNome(rs.getString("nome"));
                cliente.setIdade(rs.getInt("idade"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setContato(rs.getString("contato"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Feche o ResultSet, o PreparedStatement e a Connection
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return clientes;
    }

    // Método para buscar um cliente pelo ID
    public ClienteDTO buscarClientePorId(int id) throws SQLException {
        String sql = "SELECT * FROM Clientes WHERE id = ?";
        ClienteDTO cliente = null;
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new ClienteDTO();
                    cliente.setNome(rs.getString("nome"));
                    cliente.setIdade(rs.getInt("idade"));
                    cliente.setSexo(rs.getString("sexo"));
                    cliente.setContato(rs.getString("contato"));
                }
            }
        }
        return cliente;
    }

    // Método para atualizar os dados de um cliente
    public void atualizarCliente(ClienteDTO cliente) throws SQLException {
        String sql = "UPDATE Clientes SET nome = ?, idade = ?, sexo = ?, contato = ? WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setInt(2, cliente.getIdade());
            stmt.setString(3, cliente.getSexo());
            stmt.setString(4, cliente.getContato());
            stmt.setInt(5, cliente.getId());
            stmt.executeUpdate();
        }
    }

    // Método para excluir um cliente pelo ID
    public void excluirCliente(int id) throws SQLException {
        String sql = "DELETE FROM Clientes WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
