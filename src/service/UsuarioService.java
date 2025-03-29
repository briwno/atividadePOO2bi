package service;

import java.util.List;

import entity.Usuario;
import repository.UsuarioRepository;

public class UsuarioService {
    private UsuarioRepository repository = new UsuarioRepository();

    private boolean validarCPF(String cpf) {
        return cpf.matches("\\d{11}"); // Apenas números e exatamente 11 dígitos
    }

    public boolean cadastrarUsuario(int id, String nome, String cpf, int idade) {
        if (nome.isBlank()) {
            System.out.println("⚠️ Erro: O nome não pode estar vazio!");
            return false;
        }

        if (!validarCPF(cpf)) {
            System.out.println("⚠️ Erro: CPF inválido! Deve conter apenas 11 dígitos numéricos.");
            return false;
        }

        if (idade < 0 || idade > 120) {
            System.out.println("⚠️ Erro: Idade inválida! Informe um valor entre 0 e 120.");
            return false;
        }

        Usuario usuario = new Usuario(id, nome, cpf, idade);
        if (repository.cadastrarUsuario(usuario)) {
            System.out.println("✅ Usuário cadastrado com sucesso!");
            return true;
        } else {
            System.out.println("❌ Falha ao cadastrar usuário.");
            return false;
        }
    }

    
}
