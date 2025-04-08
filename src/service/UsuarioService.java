package service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Scanner;

import entity.Usuario;
import repository.UsuarioRepository;

public class UsuarioService {
    private UsuarioRepository repository = new UsuarioRepository();

    private boolean validarCPF(String cpf) {
        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");
        
        // Verifica se tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }
        
        // Verifica se todos os dígitos são iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }
        
        return true;
    }

    private boolean validarNome(String nome) {
        // Nome deve ter pelo menos 2 caracteres e não pode conter apenas espaços
        return nome != null && nome.trim().length() >= 2;
    }

    private boolean validarIdade(int idade) {
        return idade >= 0 && idade <= 120;
    }

    private boolean validarId(int id) {
        return id > 0;
    }

    private void exibirUsuario(Usuario usuario) {
        System.out.println("\nDados do Usuário:");
        System.out.println("ID: " + usuario.getId());
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("CPF: " + usuario.getCpf());
        System.out.println("Idade: " + usuario.getIdade());
        System.out.println("-------------------------");
    }

    public boolean cadastrarUsuario(int id, String nome, String cpf, int idade) {
        if (!validarId(id)) {
            System.out.println("Erro: ID inválido! Deve ser um número positivo.");
            return false;
        }

        if (!validarNome(nome)) {
            System.out.println("Erro: Nome inválido! Deve conter pelo menos 2 caracteres.");
            return false;
        }

        if (!validarCPF(cpf)) {
            System.out.println("Erro: CPF inválido! Deve conter 11 dígitos numéricos e ser válido.");
            return false;
        }

        if (!validarIdade(idade)) {
            System.out.println("Erro: Idade inválida! Deve estar entre 0 e 120 anos.");
            return false;
        }

        Usuario usuario = new Usuario(id, nome, cpf, idade);
        if (repository.cadastrarUsuario(usuario)) {
            System.out.println("Usuário cadastrado com sucesso!");
            return true;
        } else {
            System.out.println("Falha ao cadastrar usuário. Verifique se o ID ou CPF já estão cadastrados.");
            return false;
        }
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = repository.listarUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado no sistema.");
        } else {
            // Ordena por nome
            usuarios = usuarios.stream()
                .sorted((u1, u2) -> u1.getNome().compareToIgnoreCase(u2.getNome()))
                .collect(Collectors.toList());

            System.out.println("\nLista de Usuários:");
            for (Usuario usuario : usuarios) {
                exibirUsuario(usuario);
            }
        }
        return usuarios;
    }

    public boolean consultarUsuarioPorCpf(String cpf) {
        if (!validarCPF(cpf)) {
            System.out.println("Erro: CPF inválido! Deve conter 11 dígitos numéricos e ser válido.");
            return false;
        }

        Usuario usuario = repository.consultarUsuarioPorCpf(cpf);
        if (usuario != null) {
            exibirUsuario(usuario);
            return true;
        } else {
            System.out.println("Usuário não encontrado com o CPF informado.");
            return false;
        }
    }

    public boolean consultarUsuariosPorIniciais(String iniciais) {
        if (iniciais == null || iniciais.trim().isEmpty()) {
            System.out.println("Erro: Iniciais inválidas!");
            return false;
        }

        List<Usuario> usuarios = repository.listarUsuarios();
        List<Usuario> usuariosFiltrados = usuarios.stream()
            .filter(u -> u.getNome().toLowerCase().startsWith(iniciais.toLowerCase()))
            .sorted((u1, u2) -> u1.getNome().compareToIgnoreCase(u2.getNome()))
            .collect(Collectors.toList());

        if (usuariosFiltrados.isEmpty()) {
            System.out.println("Nenhum usuário encontrado com as iniciais '" + iniciais + "'.");
            return false;
        } else {
            System.out.println("\nUsuários encontrados com as iniciais '" + iniciais + "':");
            for (Usuario usuario : usuariosFiltrados) {
                exibirUsuario(usuario);
            }
            return true;
        }
    }

    public boolean removerUsuario(int id) {
        if (!validarId(id)) {
            System.out.println("Erro: ID inválido! Deve ser um número positivo.");
            return false;
        }

        Usuario usuario = repository.consultarUsuarioPorId(id);
        if (usuario == null) {
            System.out.println("Usuário não encontrado para o ID: " + id);
            return false;
        }

        exibirUsuario(usuario);
        System.out.print("Deseja realmente remover o usuário? Digite S para Sim e N para Não: ");
        Scanner scanner = new Scanner(System.in);
        String confirmacao = scanner.nextLine().toUpperCase();

        if (confirmacao.equals("S")) {
            if (repository.removerUsuario(id)) {
                System.out.println("Usuário removido com sucesso!");
                return true;
            } else {
                System.out.println("Erro ao remover usuário.");
                return false;
            }
        } else {
            System.out.println("Operação cancelada.");
            return false;
        }
    }

    public boolean editarUsuario(int id, String nome, String cpf, int idade) {
        if (!validarId(id)) {
            System.out.println("Erro: ID inválido! Deve ser um número positivo.");
            return false;
        }

        Usuario usuarioExistente = repository.consultarUsuarioPorId(id);
        if (usuarioExistente == null) {
            System.out.println("Usuário não encontrado para o ID: " + id);
            return false;
        }

        if (!validarNome(nome)) {
            System.out.println("Erro: Nome inválido! Deve conter pelo menos 2 caracteres.");
            return false;
        }

        if (!validarCPF(cpf)) {
            System.out.println("Erro: CPF inválido! Deve conter 11 dígitos numéricos e ser válido.");
            return false;
        }

        if (!validarIdade(idade)) {
            System.out.println("Erro: Idade inválida! Deve estar entre 0 e 120 anos.");
            return false;
        }

        if (repository.editarUsuario(id, nome, cpf, idade)) {
            System.out.println("Usuário editado com sucesso!");
            return true;
        } else {
            System.out.println("Erro: Usuário não encontrado ou falha ao editar.");
            return false;
        }
    }
}
