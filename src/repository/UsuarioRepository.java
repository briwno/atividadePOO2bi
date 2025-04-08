package repository;

import entity.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UsuarioRepository {

    public boolean idExiste(int id) {
        String query = "SELECT COUNT(*) FROM usuario WHERE id = ?";
        try (Connection conexao = Database.conectar();
                PreparedStatement stmt = conexao.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar ID: " + e.getMessage());
        }
        return false;
    }

    public Usuario consultarUsuarioPorId(int id) {
        String query = "SELECT * FROM usuario WHERE id = ?";
        try (Connection conexao = Database.conectar();
                PreparedStatement stmt = conexao.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getInt("idade"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar usuário: " + e.getMessage());
        }
        return null;
    }

    // Método para verificar se o CPF existe no banco
    public boolean cpfExiste(String cpf) {
        String query = "SELECT COUNT(*) FROM usuario WHERE cpf = ?";
        try (Connection conexao = Database.conectar();
                PreparedStatement stmt = conexao.prepareStatement(query)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar CPF: " + e.getMessage());
        }
        return false;
    }

    public boolean cadastrarUsuario(Usuario usuario) {
        if (idExiste(usuario.getId())) {
            System.out.println(" Erro: ID já cadastrado!");
            return false;
        }

        if (cpfExiste(usuario.getCpf())) {
            System.out.println(" Erro: CPF já cadastrado!");
            return false;
        }

        String query = "INSERT INTO usuario (id, nome, cpf, idade) VALUES (?, ?, ?, ?)";
        try (Connection conexao = Database.conectar();
                PreparedStatement stmt = conexao.prepareStatement(query)) {

            stmt.setInt(1, usuario.getId());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getCpf());
            stmt.setInt(4, usuario.getIdade());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println(" Usuário cadastrado com sucesso!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
        return false;
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM usuario";

        try (Connection conexao = Database.conectar();
                PreparedStatement stmt = conexao.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getInt("idade"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }
        return usuarios;
    }

    public Usuario consultarUsuarioPorCpf(String cpf) {
        String query = "SELECT * FROM usuario WHERE cpf = ?";
        try (Connection conexao = Database.conectar();
                PreparedStatement stmt = conexao.prepareStatement(query)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getInt("idade"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar usuário: " + e.getMessage());
        }
        return null;

    }

    public boolean removerUsuario(int id) {
        if (!idExiste(id)) {
            System.out.println(" Erro: ID não encontrado!");
            return false;
        }

        String query = "DELETE FROM usuario WHERE id = ?";
        try (Connection conexao = Database.conectar();
                PreparedStatement stmt = conexao.prepareStatement(query)) {

            stmt.setInt(1, id);
        } catch (SQLException e) {
            System.out.println("Erro ao remover usuário: " + e.getMessage());
        }
        return false;
    }

    public boolean editarUsuario(int id, String nome, String cpf, int idade) {
        if (!idExiste(id)) {
            System.out.println(" Erro: ID não encontrado!");
            return false;
        }

        if (cpfExiste(cpf)) {
            System.out.println(" Erro: CPF já cadastrado!");
            return false;
        }

        String query = "UPDATE usuario SET nome = ?, cpf = ?, idade = ? WHERE id = ?";
        try (Connection conexao = Database.conectar();
                PreparedStatement stmt = conexao.prepareStatement(query)) {

            stmt.setString(1, nome);
            stmt.setString(2, cpf);
            stmt.setInt(3, idade);
            stmt.setInt(4, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Usuário editado com sucesso!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao editar usuário: " + e.getMessage());
        }
        return false;
    }







}
