package app;

import entity.Usuario;
import service.UsuarioService;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioService service = new UsuarioService();
        int opcao;

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
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Idade: ");
                    int idade = scanner.nextInt();
                    service.cadastrarUsuario(id, nome, cpf, idade);
                    break;
                /* case 2:
                    System.out.print("ID do usuário a ser editado: ");
                    int idEdit = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("1 - Editar nome");
                    System.out.println("2 - Editar CPF");
                    System.out.println("3 - Editar idade");
                    System.out.print("Escolha uma opção: ");
                    int opcaoEdit = scanner.nextInt();
                    scanner.nextLine();
                    switch (opcaoEdit) {
                        case 1:
                            System.out.print("Novo nome: ");
                            String novoNome = scanner.nextLine();
                            service.editarUsuario(idEdit, "nome", novoNome);
                            break;
                        case 2:
                            System.out.print("Novo CPF: ");
                            String novoCpf = scanner.nextLine();
                            service.editarUsuario(idEdit, "cpf", novoCpf);
                            break;
                        case 3:
                            System.out.print("Nova idade: ");
                            int novaIdade = scanner.nextInt();
                            service.editarUsuario(idEdit, "idade", novaIdade);
                            break;
                        default:
                            System.out.println("Opção inválida.");
                    }
                    break; */
                case 4:
                    System.out.println("Usuários cadastrados:");
                    for (Usuario usuario : service.consultarTodosUsuarios()) {
                        System.out.println(usuario);
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
