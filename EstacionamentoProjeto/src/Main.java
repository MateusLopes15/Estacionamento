import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Sistema sistema;
    static Tarifa tarifa;
    static Gestor gestor;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        init();

        while (true) {
            System.out.println("--------------------------------------------------------");
            System.out.println("---- SISTEMA DE CONTROLE DO PÁTIO DE ESTACIONAMENTO ----");
            System.out.println("--------------------------------------------------------");
            System.out.println();

            System.out.println("Selecione a sua opção: ");
            System.out.println("1 - Sistema de Atendimento");
            System.out.println("2 - Sistema de Gestão");
            System.out.println("3 - Sair");
            int escolha = escolhaUsuario(sc);
            switch (escolha) {
                case 1:
                    // INSIRA A FUNÇÃO DO SISTEMA DE ATENDIMENTO AQUI
                    break;
                case 2:
                    sistemaGestao(sc);
                    break;
                case 3:
                    sc.close();
                    return;
                default:
                    System.out.println("Entrada inválida.");
            }
        }
    }

    static void init() {
        sistema = new Sistema();
        tarifa = new Tarifa();
        gestor = new Gestor();
        gestor.sistema = sistema;
        gestor.tarifa = tarifa;
        Random r = new Random();

        for (int i = 0; i < 10; i++) {
            int tipoRandom = r.nextInt(3);
            Tipo tipo = null;
            switch (tipoRandom) {
                case 0:
                    tipo = Tipo.UTILITARIO;
                    break;
                case 1:
                    tipo = Tipo.AUTOMOVEL;
                    break;
                case 2:
                    tipo = Tipo.MOTOCICLETA;
            }
            gestor.cadastrarVaga(i + 1, tipo);
        }
    }

    static void sistemaGestao(Scanner sc) {
        while (true) {
            System.out.println("--------------------------------------------------------");
            System.out.println("----- SISTEMA DE GESTÃO DO PÁTIO DE ESTACIONAMENTO -----");
            System.out.println("--------------------------------------------------------");
            System.out.println("1. Gerenciar vagas");
            System.out.println("2. Gerenciar listagens");
            System.out.println("3. Alterar tarifas ");
            System.out.println("4. Retornar ao menu principal");
            int escolha = escolhaUsuario(sc);
            switch (escolha) {
                case 1:
                    sistemaGestaoGerenciarVagas(sc);
                    break;
                case 2:
                    // INSERIR A FUNCIONALIDADE DO GERENCIAMENTO DE LISTAGENS AQUI!
                    break;
                case 3:
                    sistemaTarifa(sc);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Entrada inválida. Tente novamente.");
            }
        }

    }

    static int escolhaUsuario(Scanner sc) {
        int escolha;
        while (true) {
            try {
                escolha = sc.nextInt();
                sc.nextLine(); // Consome o '\n'
                return escolha;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Tente novamente.");
                sc.nextLine(); // Consome o '\n' em caso de erro
            }
        }
    }

    static void sistemaGestaoGerenciarVagas(Scanner sc) {
        while (true) {
            System.out.println("--------------------------------------------------------");
            System.out.println("---------------- GERENCIAMENTO DE VAGAS ----------------");
            System.out.println("--------------------------------------------------------");
            System.out.println("Selecione a sua opção: ");
            System.out.println("1 - Cadastrar vaga");
            System.out.println("2 - Excluir vaga");
            System.out.println("3 - Listar vagas");
            System.out.println("4 - Alterar tamanho das colunas");
            System.out.println("5 - Retornar ao menu principal");
            int escolha = escolhaUsuario(sc);
            switch (escolha) {
                case 1:
                    sistemaGestaoCadastrarVaga(sc);
                    break;
                case 2:
                    sistemaGestaoExcluirVaga(sc);
                    break;
                case 3:
                    gestor.listarVagas();
                    break;
                case 4:
                    sistemaGestaoAlterarLarguraColunas(sc);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Entrada inválida.");
            }
        }
    }

    static void sistemaGestaoCadastrarVaga(Scanner sc) {
        int numVaga = -1;
        while (numVaga < 0) {
            System.out.println("Digite o número da vaga a ser cadastrada: ");
            numVaga = escolhaUsuario(sc);
        }
        Tipo tipo = null;
        while (true) {
            System.out.println("Digite o tipo da vaga (utilitario, automovel, motocicleta): ");
            String tipoString = sc.nextLine().trim().toLowerCase();
            switch (tipoString) {
                case "utilitario":
                    tipo = Tipo.UTILITARIO;
                    break;
                case "automovel":
                    tipo = Tipo.AUTOMOVEL;
                    break;
                case "motocicleta":
                    tipo = Tipo.MOTOCICLETA;
                    break;
                default:
                    System.out.println("Tipo inválido. Tente novamente");
            }

            if (gestor.cadastrarVaga(numVaga, tipo)) {
                System.out.println("Vaga cadastrada com sucesso!");
            } else {
                System.out.println("Já existe uma vaga com esse número. Não foi possível realizar o cadastro.");
            }
            return;
        }
    }

    static void sistemaGestaoExcluirVaga(Scanner sc) {
        System.out.println("Digite o número da vaga a ser excluída: ");
        int numVaga = escolhaUsuario(sc);
        if (gestor.excluirVaga(numVaga)) {
            System.out.println("Vaga excluída com sucesso!");
        } else {
            System.out.println("Número não encontrado. Não foi possível excluir a sua vaga.");
        }
    }

    static void sistemaGestaoAlterarLarguraColunas(Scanner sc) {
        while (true) {
            System.out.println("Largura atual das colunas: ");
            System.out.println("COLUNA 1\tCOLUNA 2\tCOLUNA 3\t COLUNA 4");
            for (int i = 0; i < gestor.largurasColunaVagas.length; i++) {
                System.out.print(gestor.largurasColunaVagas[i] + "\t\t");
            }

            System.out.println("\n\nAlterar a largura de qual coluna?");
            System.out.println("1 - Coluna 1");
            System.out.println("2 - Coluna 2");
            System.out.println("3 - Coluna 3");
            System.out.println("4 - Coluna 4");
            System.out.println("5 - Retornar ao menu de gestão");

            int escolha = escolhaUsuario(sc);
            if (escolha > 0 && escolha < 5) {
                System.out.println("Digite o novo tamanho da coluna. O tamanho mínimo é 14.");
                int tamanho = escolhaUsuario(sc);

                if (tamanho < 14) {
                    System.out.println("Tamanho inválido.");
                    continue;
                }

                gestor.largurasColunaVagas[escolha - 1] = tamanho;
                System.out.println("Largura alterada com sucesso!");

            } else if (escolha == 5) {
                return;
            } else {
                System.out.println("Entrada inválida. Tente novamente.");
            }
        }
    }

    static void sistemaTarifa(Scanner sc) {
        while (true) {
            System.out.println("--------------------------------------------------------");
            System.out.println("--------------- GERENCIAMENTO DE TARIFAS ---------------");
            System.out.println("--------------------------------------------------------");

            System.out.println("UTILITÁRIO:");
            System.out.printf("Tarifa fixa: R$%.2f\n", gestor.tarifa.tarifaFixa[0]);
            System.out.printf("Tarifa por hora: R$%.2f\n\n", gestor.tarifa.tarifaHora[0]);

            System.out.println("AUTOMÓVEL:");
            System.out.printf("Tarifa fixa: R$%.2f\n", gestor.tarifa.tarifaFixa[1]);
            System.out.printf("Tarifa por hora: R$%.2f\n\n", gestor.tarifa.tarifaHora[1]);

            System.out.println("MOTOCICLETA:");
            System.out.printf("Tarifa fixa: R$%.2f\n", gestor.tarifa.tarifaFixa[2]);
            System.out.printf("Tarifa por hora: R$%.2f\n\n", gestor.tarifa.tarifaHora[2]);

            System.out.println("Selecione a sua opção:");
            System.out.println("1. Alterar tarifas");
            System.out.println("2. Retornar ao menu");

            int escolha = escolhaUsuario(sc);
            if (escolha == 1) {
                sistemaTarifaAlterarTarifa(sc);
            } else if (escolha == 2) {
                return;
            } else {
                System.out.println("Entrada inválida.");
            }
        }
    }

    static void sistemaTarifaAlterarTarifa(Scanner sc) {
        System.out.println("Qual tarifa você deseja alterar?");
        System.out.println("1. Utilitário");
        System.out.println("2. Automóvel");
        System.out.println("3. Motocicleta");
        System.out.println("4. Retornar");

        int escolha = escolhaUsuario(sc);
        double tarifa = -1;

        if (escolha > 0 && escolha < 4) {
            System.out.println("Qual a tarifa que você deseja alterar?");
            System.out.println("1. Tarifa fixa");
            System.out.println("2. Tarifa por hora");
            System.out.println("3. Retornar");
            int tarifaEscolha = escolhaUsuario(sc);
            if (tarifaEscolha == 1 || tarifaEscolha == 2) {
                while (tarifa < 0) {
                    System.out.println("Digite o valor da nova tarifa (em R$): ");
                    tarifa = sc.nextDouble();
                }
            }

            if (tarifaEscolha == 1) {
                gestor.tarifa.tarifaFixa[escolha - 1] = tarifa;
            } else if (tarifaEscolha == 2) {
                gestor.tarifa.tarifaHora[escolha - 1] = tarifa;
            }
            System.out.println("Valor alterado com sucesso.");
        }
    }
}
