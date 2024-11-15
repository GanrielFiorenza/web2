package Controler;

import Model.ClienteDAO;
import Model.ClienteDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ClienteInserirServlet extends HttpServlet {
    private Connection connection;

    @Override
    public void init() throws ServletException {
        this.connection = (Connection) getServletContext().getAttribute("DBConnection");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        int idade = Integer.parseInt(request.getParameter("idade"));
        String sexo = request.getParameter("sexo");
        String contato = request.getParameter("contato");

        ClienteDTO cliente = new ClienteDTO();
        cliente.setNome(nome);
        cliente.setIdade(idade);
        cliente.setSexo(sexo);
        cliente.setContato(contato);

        try {
            ClienteDAO dao = new ClienteDAO(connection);
            dao.inserirCliente(cliente);
            response.sendRedirect("ClienteController?action=listar");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
