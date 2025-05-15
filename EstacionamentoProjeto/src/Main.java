import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Sistema sistema;
    static Tarifa tarifa;
    static Gestor gestor;
    static Funcionario funcionario;

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
                    sistemaFuncionario(sc);
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

    /*
     * 
     * INICIALIZAÇÃO DO SISTEMA
     * 
     */

    static void init() {
        // Inicializações dos objetos e atribuições
        sistema = new Sistema();
        tarifa = new Tarifa();
        gestor = new Gestor();
        funcionario = new Funcionario();

        gestor.sistema = sistema;
        gestor.tarifa = tarifa;
        funcionario.sistema = sistema;
        funcionario.tarifa = tarifa;
        geraVagas(); // Cadastra 50 vagas através das funções do gestor

    }

    static void geraVagas() {
        Random r = new Random();
        for (int i = 0; i < gestor.sistema.vagas.length / 2; i++) {
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

    /*
     * 
     * FUNÇÕES DO SISTEMA DE GESTÃO
     * 
     */

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
                    sistemaGestaoTarifa(sc);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Entrada inválida. Tente novamente.");
            }
        }
    }

    /*
     * 
     * GESTÃO - FUNÇÕES DE GERENCIAMENTO DE VAGAS
     * 
     */

    static void sistemaGestaoGerenciarVagas(Scanner sc) {
        while (true) {
            System.out.println("--------------------------------------------------------");
            System.out.println("---------------- GERENCIAMENTO DE VAGAS ----------------");
            System.out.println("--------------------------------------------------------");
            System.out.println("Selecione a sua opção: ");
            System.out.println("1 - Cadastrar vaga");
            System.out.println("2 - Excluir vaga");
            System.out.println("3 - Listar vagas");
            System.out.println("4 - Alterar dados das vagas");
            System.out.println("5 - Alterar tamanho das colunas");
            System.out.println("6 - Retornar ao menu principal");
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
                    sistemaGestaoAlterarDadosVagas(sc);
                    break;
                case 5:
                    sistemaGestaoAlterarLarguraColunas(sc);
                    break;
                case 6:
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

    static void sistemaGestaoAlterarDadosVagas(Scanner sc) {
        while (true) {
            System.out.println("--------------------------------------------------------");
            System.out.println("--------------- ALTERAR DADOS DAS VAGAS ----------------");
            System.out.println("--------------------------------------------------------");
            System.out.println("1. Alterar dados de uma vaga");
            System.out.println("2. Retornar");
            int escolha = escolhaUsuario(sc);

            switch (escolha) {
                case 1:
                    alterarVaga(sc);
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Entrada inválida.");
            }
        }
    }

    static void alterarVaga(Scanner sc) {
        System.out.print("Digite o número da vaga que você deseja alterar: ");
        int numeroVaga = escolhaUsuario(sc);
        Vaga vaga = encontrarVaga(numeroVaga);

        if (vaga == null) {
            System.out.println("Não foi possível encontrar uma vaga com o número especificado. Tente novamente.");
            return;
        }

        System.out.println("Alterar qual dado?");
        System.out.println("1. Número");
        System.out.println("2. Tipo");
        System.out.println("3. Estado");

        int escolha = escolhaUsuario(sc);
        switch (escolha) {
            case 1:
                alterarNumero(sc, vaga);
                System.out.println("Número alterado com sucesso!");
                break;
            case 2:
                alterarTipo(sc, vaga);
                System.out.println("Tipo alterado com sucesso!");
                break;
            case 3:
                alterarEstado(sc, vaga);
                System.out.println("Estado alterado com sucesso!");
                break;
            default:
                System.out.println("Entrada inválida.");
        }
    }

    static void alterarNumero(Scanner sc, Vaga vaga) {
        System.out.println("Digite o novo número da vaga:");
        vaga.numero = escolhaUsuario(sc);
    }

    static void alterarTipo(Scanner sc, Vaga vaga) {
        System.out.println("Escolha o novo tipo da vaga:");
        System.out.println("1 - Utilitário");
        System.out.println("2 - Automóvel");
        System.out.println("3 - Motocicleta");

        int tipo = escolhaUsuario(sc);
        switch (tipo) {
            case 1:
                vaga.tipo = Tipo.UTILITARIO;
                break;
            case 2:
                vaga.tipo = Tipo.AUTOMOVEL;
                break;
            case 3:
                vaga.tipo = Tipo.MOTOCICLETA;
                break;
            default:
                System.out.println("Entrada inválida.");
        }
    }

    static void alterarEstado(Scanner sc, Vaga vaga) {
        System.out.println("Escolha o novo estado da vaga:");
        System.out.println("1. Livre");
        System.out.println("2. Ocupado");

        int estado = escolhaUsuario(sc);
        if (estado == 1) {
            vaga.estado = Estado.LIVRE;
        } else if (estado == 2) {
            vaga.estado = Estado.OCUPADO;
        } else {
            System.out.println("Entrada inválida.");
        }
    }

    static Vaga encontrarVaga(int numVaga) {
        if (numVaga > 0) {
            for (int i = 0; i < gestor.sistema.numVagas; i++) {
                if (numVaga == gestor.sistema.vagas[i].numero) {
                    return gestor.sistema.vagas[i];
                }
            }
        }
        return null;
    }

    /*
     * 
     * GESTÃO - FUNÇÕES DE ALTERAÇÃO DE TARIFA
     * 
     */

    static void sistemaGestaoTarifa(Scanner sc) {
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

    /*
     * 
     * FUNÇÕES DO SISTEMA DE ATENDIMENTO
     * 
     */

    static void sistemaFuncionario(Scanner sc) {
        funcionario = new Funcionario();
        while (true) {
            int escolha = escolhaUsuario(sc);
            System.out.println("--------------------------------------------------------");
            System.out.println("----------SISTEMA DE GESTÃO DO ESTACIONAMENTO-----------");
            System.out.println("--------------------------------------------------------");
            System.out.println("Selecione a sua opção: ");
            System.out.println("1 - Cadastrar veículo");
            System.out.println("2 - Adicionar veículo a uma vaga livre");
            System.out.println("3 - Remover veículo de uma vaga");

            switch (escolha) {
                case 1:
                    sistemaCadastroVeiculo(sc);
                    break;
                case 2:
                    sistemaAdicionarVeiculoemVaga(sc);
                    break;
                case 3:
                    // Remover veiculo de uma vaga
                    break;

                default:
                    System.out.println("Entrada inválida. Tente novamente.");
            }
        }
    }

    static void sistemaCadastroVeiculo(Scanner sc) {
        Tipo tipo = null;
        Funcionario funcionario;
        funcionario = new Funcionario();
        System.out.println("--------------------------------------------------------");
        System.out.println("-------------SISTEMA DE ADICIONAR VEÍCULO---------------"); // depois de testar fazer uma
                                                                                        // parte para voltar
        System.out.println("--------------------------------------------------------");
        String placaString = "BACBACB";
        while (funcionario.validaPlacaMercosul(placaString) == false) {
            System.out.println("Qual a placa?"); // Já verificar se a placa vale logo no começo para evitar problema de
                                                 // ter que digitar tudo novamente
            placaString = sc.nextLine().trim().toUpperCase();
            if (funcionario.validaPlacaMercosul(placaString) == false) {
                System.out.println("Digite uma placa válida");
            } else {
                System.out.println("Placa válida ");
            }
        }

        System.out.println("Qual o modelo?");
        String modeloString = sc.nextLine().toUpperCase();
        System.out.println("Qual a cor?");
        String corString = sc.nextLine().toUpperCase();
        System.out.println("Qual a marca?");
        String marcaString = sc.nextLine().toUpperCase();
        System.out.println("Digite o tipo do veículo (utilitario, automovel, motocicleta): ");
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
        if (funcionario.adicionarVeiculo(placaString, modeloString, corString, marcaString, tipo)) {
            System.out.println("Veículo cadastrada com sucesso!");
        } else {
            System.out.println("Já existe um Veículo existente com essas informações, não foi possível adicionar");
        }
        return;

    }

    static void sistemaAdicionarVeiculoemVaga(Scanner sc) {
        System.out.println("--------------------------------------------------------");
        System.out.println("---------SISTEMA DE ADICIONAR VEÍCULO EM VAGA-----------");
        System.out.println("--------------------------------------------------------");
    }

    /*
     * 
     * FUNÇÕES AUXILIARES
     * 
     */

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
}
