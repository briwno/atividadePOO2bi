package repository;

import entity.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
    private List<Usuario> usuarios;

    public UsuarioRepository() {
        usuarios = new ArrayList<>();
    }

    public boolean idExiste(int id) {
        return usuarios.stream().anyMatch(usuario -> usuario.getId() == id);
    }

    public boolean cpfExiste(String cpf) {
        return usuarios.stream().anyMatch(usuario -> usuario.getCpf().equals(cpf));
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

        usuarios.add(usuario);
        return true;
    }
}
