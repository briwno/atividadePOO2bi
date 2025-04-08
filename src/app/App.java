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
        }

        System.out.println("\nBem-vindo ao Sistema de Cadastro de Usuários!");
        System.out.println("=============================================");

        do {
            System.out.println("\nMENU PRINCIPAL");
            System.out.println("1 - Cadastrar Usuário");
            System.out.println("2 - Editar Usuário");
            System.out.println("3 - Remover Usuário");
            System.out.println("4 - Consultar Todos os Usuários");
            System.out.println("5 - Consultar Usuário pelo CPF");
            System.out.println("6 - Consultar Usuário pelas Iniciais do Nome");
            System.out.println("7 - Sair");
            System.out.print("\nEscolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer

                switch (opcao) {
                    case 1:
                        System.out.println("\nCADASTRO DE USUÁRIO");
                        System.out.println("----------------------");
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
                        } catch (Exception e) {
                            System.out.println("Erro: Entrada inválida. Por favor, tente novamente.");
                            scanner.nextLine();
                        }
                        break;
                    case 2:
                        System.out.println("\nEDIÇÃO DE USUÁRIO");
                        System.out.println("-------------------");
                        // Lista todos os usuários primeiro
                        if (service.listarUsuarios().isEmpty()) {
                            break;
                        }
                        
                        try {
                            System.out.print("\nDigite o ID do usuário a ser editado: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            
                            System.out.print("Novo Nome: ");
                            String nome = scanner.nextLine();
                            
                            System.out.print("Novo CPF: ");
                            String cpf = scanner.nextLine();
                            
                            System.out.print("Nova Idade: ");
                            int idade = scanner.nextInt();
                            scanner.nextLine();
                            
                            service.editarUsuario(id, nome, cpf, idade);
                        } catch (Exception e) {
                            System.out.println("Erro: Entrada inválida. Por favor, tente novamente.");
                            scanner.nextLine();
                        }
                        break;
                    case 3:
                        System.out.println("\nREMOÇÃO DE USUÁRIO");
                        System.out.println("-------------------");
                        // Lista todos os usuários primeiro
                        if (service.listarUsuarios().isEmpty()) {
                            break;
                        }
                        
                        try {
                            System.out.print("\nDigite o ID do usuário a ser removido: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            service.removerUsuario(id);
                        } catch (Exception e) {
                            System.out.println("Erro: ID inválido. Por favor, tente novamente.");
                            scanner.nextLine();
                        }
                        break;
                    case 4:
                        System.out.println("\nCONSULTA DE USUÁRIOS");
                        System.out.println("----------------------");
                        service.listarUsuarios();
                        break;
                    case 5:
                        System.out.println("\nCONSULTA POR CPF");
                        System.out.println("------------------");
                        System.out.print("Digite o CPF do usuário: ");
                        String cpf = scanner.nextLine();
                        service.consultarUsuarioPorCpf(cpf);
                        break;
                    case 6:
                        System.out.println("\nCONSULTA POR INICIAIS DO NOME");
                        System.out.println("-----------------------------");
                        System.out.print("Digite as iniciais do nome: ");
                        String iniciais = scanner.nextLine();
                        service.consultarUsuariosPorIniciais(iniciais);
                        break;
                    case 7:
                        System.out.println("\nEncerrando o sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida! Por favor, escolha uma opção entre 1 e 7.");
                }
            } catch (Exception e) {
                System.out.println("Erro: Entrada inválida. Por favor, digite um número.");
                scanner.nextLine();
                opcao = 0;
            }
        } while (opcao != 7);

        scanner.close();
    }
}
