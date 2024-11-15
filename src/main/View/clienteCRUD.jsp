<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Model.ClienteDTO" %>
<%@ page import="Model.ClienteDAO" %>
<%@ page import="java.sql.Connection" %>

<html>
<head>
    <title>Gestão de Clientes</title>
</head>
<body>
    <h1>Gestão de Clientes</h1>

    <!-- Formulário para Inserir ou Editar Cliente -->
    <h2><%= request.getAttribute("clienteEdit") == null ? "Inserir Novo Cliente" : "Editar Cliente" %></h2>
    <form action="ClienteInserirServlet" method="POST">
        <input type="hidden" name="id" value="<%= request.getAttribute("clienteEdit") != null ? ((ClienteDTO)request.getAttribute("clienteEdit")).getId() : "" %>">
        <label>Nome:</label>
        <input type="text" name="nome" value="<%= request.getAttribute("clienteEdit") != null ? ((ClienteDTO)request.getAttribute("clienteEdit")).getNome() : "" %>" required/><br/>
        <label>Idade:</label>
        <input type="number" name="idade" value="<%= request.getAttribute("clienteEdit") != null ? ((ClienteDTO)request.getAttribute("clienteEdit")).getIdade() : "" %>" required/><br/>
        <label>Sexo:</label>
        <select name="sexo" required>
            <option value="Masculino" <%= request.getAttribute("clienteEdit") != null && "Masculino".equals(((ClienteDTO)request.getAttribute("clienteEdit")).getSexo()) ? "selected" : "" %>>Masculino</option>
            <option value="Feminino" <%= request.getAttribute("clienteEdit") != null && "Feminino".equals(((ClienteDTO)request.getAttribute("clienteEdit")).getSexo()) ? "selected" : "" %>>Feminino</option>
            <option value="Outro" <%= request.getAttribute("clienteEdit") != null && "Outro".equals(((ClienteDTO)request.getAttribute("clienteEdit")).getSexo()) ? "selected" : "" %>>Outro</option>
        </select><br/>
        <label>Contato:</label>
        <input type="email" name="contato" value="<%= request.getAttribute("clienteEdit") != null ? ((ClienteDTO)request.getAttribute("clienteEdit")).getContato() : "" %>" required/><br/>
        <button type="submit"><%= request.getAttribute("clienteEdit") == null ? "Salvar" : "Atualizar" %></button>
    </form>

    <!-- Listagem dos Clientes -->
    <h2>Lista de Clientes</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Idade</th>
            <th>Sexo</th>
            <th>Contato</th>
            <th>Ações</th>
        </tr>
        <% 
            List<ClienteDTO> clientes = (List<ClienteDTO>) request.getAttribute("clientes");
            for (ClienteDTO cliente : clientes) { 
        %>
            <tr>
                <td><%= cliente.getId() %></td>
                <td><%= cliente.getNome() %></td>
                <td><%= cliente.getIdade() %></td>
                <td><%= cliente.getSexo() %></td>
                <td><%= cliente.getContato() %></td>
                <td>
                    <a href="ClienteEditarServlet?id=<%= cliente.getId() %>">Editar</a> | 
                    <a href="ClienteExcluirServlet?id=<%= cliente.getId() %>">Excluir</a>
                </td>
            </tr>
        <% } %>
    </table>
</body>
</html>
