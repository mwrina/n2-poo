import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PecaCRUD crud = new PecaCRUD();
        boolean continuar = true;

        while(true) {
            while(continuar) {
                System.out.printf("\n");
                System.out.println("O que você deseja fazer? (Digite o número da operação)");
                System.out.println("1. Cadastrar peça");
                System.out.println("2. Listar peças cadastradas");
                System.out.println("3. Buscar peça específica");
                System.out.println("4. Alterar cadastro de peça");
                System.out.println("5. Excluir peça");
                System.out.println("0. Sair");
                int op = scanner.nextInt();
                int codigo;
                switch (op) {
                    case 0:
                        System.out.println("Saindo...");
                        continuar = false;
                        break;
                    case 1:
                        codigo = -1;
                        boolean codigoValido = false;

                        while(!codigoValido) {
                            System.out.println("Qual o código da peça a ser cadastrada?");
                            codigo = scanner.nextInt();
                            if (crud.existeCodigo(codigo)) {
                                System.out.println("Código já cadastrado! Tente novamente.");
                            } else {
                                codigoValido = true;
                            }
                        }

                        System.out.println("Qual o nome da peça a ser cadastrada?");
                        String nome = scanner.next();
                        System.out.println("Qual o preço base da peça?");
                        double preco = scanner.nextDouble();
                        System.out.println("A peça é brasileira? (S/N)");
                        String tipo = scanner.next();
                        Object peca;
                        if (tipo.equalsIgnoreCase("s")) {
                            peca = new PecaGenuina(codigo, nome, preco);
                        } else {
                            peca = new PecaSegundaLinha(codigo, nome, preco);
                        }

                        try {
                            crud.createPeca((Peca)peca);
                        } catch (Exception var13) {
                            Exception e = var13;
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 2:
                        crud.readAllPecas();
                        break;
                    case 3:
                        System.out.println("Informe o código da peça que deseja buscar: ");
                        codigo = scanner.nextInt();
                        crud.readPecaEspecifica(codigo);
                        break;
                    case 4:
                        System.out.println("Informe o código da peça que deseja alterar: ");
                        codigo = scanner.nextInt();
                        Peca peca = crud.readPecaEspecifica(codigo); 
                        if (peca != null) {
                            System.out.println("Novo nome da peça:");
                            String novoNome = scanner.next();
                            System.out.println("Novo preço da peça:");
                            double novoPreco = scanner.nextDouble();
                            peca.setNome(novoNome);
                            peca.setPreco(novoPreco);
                            try {
                                crud.updatePeca(peca);
                                System.out.println("Peça atualizada com sucesso!");
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        } else {
                            System.out.println("Peça não encontrada.");
                        }
                        break;
                    case 5:
                        System.out.println("Informe o código da peça que deseja excluir: ");
                        codigo = scanner.nextInt();
                        try {
                            crud.deletePeca(codigo);
                            System.out.println("Peça excluída com sucesso!");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente. (Lembre-se: Você precisa escrever o número da operação)");
                }
            }

            scanner.close();
            return;
        }
    }
}
