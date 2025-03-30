package service;

import java.util.List;

import entity.Usuario;
import repository.UsuarioRepository;

public class UsuarioService {
    private UsuarioRepository repository = new UsuarioRepository();

    private boolean validarCPF(String cpf) {
        return cpf.matches("\\d{11}"); 
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

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = repository.listarUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("⚠️ Nenhum usuário encontrado.");
        } else {
            System.out.println("Lista de Usuários:");
            for (Usuario usuario : usuarios) {
                System.out.println("---------ID: " + usuario.getId() + "------------");
                System.out.println("Nome: " + usuario.getNome());
                System.out.println("CPF: " + usuario.getCpf());
                System.out.println("Idade: " + usuario.getIdade());
                System.out.println("-------------------------");
            }
        }
        return usuarios;
    }

    public boolean consultarUsuarioPorCpf(String cpf) {
        if (!validarCPF(cpf)) {
            System.out.println("⚠️ Erro: CPF inválido! Deve conter apenas 11 dígitos numéricos.");
            return false;
        }

        Usuario usuario = repository.consultarUsuarioPorCpf(cpf);
        if (usuario != null) {

            System.out.println("Usuário com o cpf: '" + usuario.getCpf() + "' Encontrado com sucesso!");
            System.out.println("ID: " + usuario.getId());
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Idade: " + usuario.getIdade());

            return true;
        } else {
            System.out.println("⚠️ Usuário não encontrado.");
            return false;
        }
    }

    public boolean removerUsuario(int id) {
        if (repository.removerUsuario(id)) {
            System.out.println("✅ Usuário removido com sucesso!");
            return true;
        } else {
            System.out.println("⚠️ Erro: Usuário não encontrado ou falha ao remover.");
            return false;
        }
    }

    public boolean editarUsuario(int id, String nome, String cpf, int idade) {
        if (repository.editarUsuario(id, nome, cpf, idade)) {
            System.out.println("✅ Usuário editado com sucesso!");
            return true;
        } else {
            System.out.println("⚠️ Erro: Usuário não encontrado ou falha ao editar.");
            return false;
        }
    }







}
