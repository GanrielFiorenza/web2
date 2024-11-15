package Controler;

import Model.ClienteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ClienteExcluirServlet extends HttpServlet {
    private Connection connection;

    @Override
    public void init() throws ServletException {
        this.connection = (Connection) getServletContext().getAttribute("DBConnection");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            ClienteDAO dao = new ClienteDAO(connection);
            dao.excluirCliente(id);
            response.sendRedirect("ClienteListarServlet");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
