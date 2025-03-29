package repository;

import entity.Usuario;
import java.sql.*;

public class UsuarioRepository {
    private Connection conexao;

    public UsuarioRepository() {
        try {
            conexao = DriverManager.getConnection("127.0.0.1:3306", "root", "1234");
        } catch (SQLException e) {
            System.out.println("❌ Erro ao conectar ao banco: " + e.getMessage());
        }
    }

    public boolean idExiste(int id) {
        String sql = "SELECT id FROM usuarios WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Retorna true se o ID já existir
        } catch (SQLException e) {
            System.out.println("❌ Erro ao verificar ID: " + e.getMessage());
            return false;
        }
    }

    public boolean cpfExiste(String cpf) {
        String sql = "SELECT cpf FROM usuarios WHERE cpf = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Retorna true se o CPF já existir
        } catch (SQLException e) {
            System.out.println("❌ Erro ao verificar CPF: " + e.getMessage());
            return false;
        }
    }

    public boolean cadastrarUsuario(Usuario usuario) {
        if (idExiste(usuario.getId())) {
            System.out.println("⚠️ Erro: ID já cadastrado!");
            return false;
        }

        if (cpfExiste(usuario.getCpf())) {
            System.out.println("⚠️ Erro: CPF já cadastrado!");
            return false;
        }

        String sql = "INSERT INTO usuarios (id, nome, cpf, idade) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, usuario.getId());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getCpf());
            stmt.setInt(4, usuario.getIdade());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("❌ Erro ao cadastrar usuário: " + e.getMessage());
            return false;
        }
    }
}
