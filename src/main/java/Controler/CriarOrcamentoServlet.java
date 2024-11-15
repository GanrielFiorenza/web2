package Controler;

import java.io.*;

import Model.Orçamento;
import Model.OrcamentoDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.*;

public class CriarOrcamentoServlet extends HttpServlet {
    private Connection connection;

    @Override
    public void init() throws ServletException {
        // Estabelece a conexão com o banco de dados
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MeuBanco", "usuario", "senha");
        } catch (SQLException e) {
            throw new ServletException("Erro ao conectar ao banco de dados", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtendo os dados do formulário
        String projeto = request.getParameter("projeto");
        float valor = Float.parseFloat(request.getParameter("valor"));
        int clienteId = Integer.parseInt(request.getParameter("clienteId"));
        String status = request.getParameter("status");

        // Criando o objeto Orçamento
        Orçamento orcamento = new Orçamento();
        orcamento.setProjeto(projeto);
        orcamento.setValor(valor);
        orcamento.setClientId(clienteId);
        orcamento.setStatus(status);

        // Instanciando o DAO e criando o orçamento
        OrcamentoDAO dao = new OrcamentoDAO(connection);
        try {
            dao.criarOrcamento(orcamento);
            response.sendRedirect("orcamentos");  // Redireciona para a lista de orçamentos
        } catch (SQLException e) {
            throw new ServletException("Erro ao criar o orçamento", e);
        }
    }
}
