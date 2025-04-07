package app;

import repository.Database;
import service.UsuarioService;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioService service = new UsuarioService();
        int opcao;

        try {
            Database.conectar();
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return;
        }
        System.out.println("Bem-vindo ao sistema de cadastro de usuários!");
        System.out.println("Por favor, escolha uma opção:");

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Cadastrar Usuário");
            System.out.println("2 - Editar Usuário");
            System.out.println("3 - Remover Usuário");
            System.out.println("4 - Consultar Todos os Usuários");
            System.out.println("5 - Consultar Usuário pelo CPF");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    try {
                        System.out.print("ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();
                        System.out.print("Idade: ");
                        int idade = scanner.nextInt();
                        scanner.nextLine(); 
                        service.cadastrarUsuario(id, nome, cpf, idade);
                        System.out.println("Usuário cadastrado com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro ao cadastrar usuário. Verifique os dados e tente novamente.");
                        scanner.nextLine(); 
                    }
                    break;
                case 2:
                    try {
                        System.out.print("Digite o ID do usuário a ser editado: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Novo Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Novo CPF: ");
                        String cpf = scanner.nextLine();
                        System.out.print("Nova Idade: ");
                        int idade = scanner.nextInt();
                        scanner.nextLine();
                        service.editarUsuario(id, nome, cpf, idade);
                    } catch (Exception e) {
                        System.out.println("Erro ao editar usuário. Verifique os dados e tente novamente.");
                    }
                    break;
                case 3:
                    try {
                        System.out.print("Digite o ID do usuário a ser removido: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        service.removerUsuario(id);
                    } catch (Exception e) {
                        System.out.println("Erro ao remover usuário. Verifique os dados e tente novamente.");
                    }
                    break;
                case 4:
                    try {
                        service.listarUsuarios();
                    } catch (Exception e) {
                        System.out.println("Erro ao listar usuários. Verifique os dados e tente novamente.");
                    }
                    break;
                case 5:
                    try {
                        System.out.print("Digite o CPF do usuário: ");
                        String cpf = scanner.nextLine();
                        service.consultarUsuarioPorCpf(cpf);
                    } catch (Exception e) {
                        System.out.println("Erro ao consultar usuário. Verifique os dados e tente novamente.");
                    }
                    break;

                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 6);

        scanner.close();
    }
}
