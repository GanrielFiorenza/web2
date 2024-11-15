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
import java.util.List;

public class ClienteController extends HttpServlet {
    private Connection connection;

    @Override
    public void init() throws ServletException {
        this.connection = (Connection) getServletContext().getAttribute("DBConnection");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("listar".equals(action)) {
            try {
				listarClientes(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else if ("inserir".equals(action)) {
            request.getRequestDispatcher("clienteCRUD.jsp").forward(request, response);
        } else if ("editar".equals(action)) {
            editarCliente(request, response);
        } else if ("excluir".equals(action)) {
            excluirCliente(request, response);
        } else {
            try {
				listarClientes(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

    private void listarClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        ClienteDAO dao = new ClienteDAO(connection);
		List<ClienteDTO> clientes = dao.listarClientes();
		request.setAttribute("clientes", clientes);
		request.getRequestDispatcher("clienteCRUD.jsp").forward(request, response);
    }

    private void editarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            ClienteDAO dao = new ClienteDAO(connection);
            ClienteDTO cliente = dao.buscarClientePorId(id);
            request.setAttribute("cliente", cliente);
            request.getRequestDispatcher("clienteCRUD.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void excluirCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            ClienteDAO dao = new ClienteDAO(connection);
            dao.excluirCliente(id);
            response.sendRedirect("ClienteController?action=listar");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
