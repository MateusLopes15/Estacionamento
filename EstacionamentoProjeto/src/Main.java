import java.util.InputMismatchException;
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
            gestor.menuPrincipal();
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
        gestor.initGeraVagas(); // Cadastra 50 vagas através das funções do gestor

    }

    /*
     * 
     * FUNÇÕES DO SISTEMA DE GESTÃO
     * 
     */

    static void sistemaGestao(Scanner sc) {
        while (true) {
            gestor.menuVagas();
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
                    sistemaGestaoAlterarLarguraColunas(sc);
                    break;
                case 5:
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
            gestor.menuGerenciamentoVagas();
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
                    return;
                default:
                    System.out.println("Entrada inválida.");
            }
        }
    }

    static void sistemaGestaoCadastrarVaga(Scanner sc) {
        System.out.print("Digite o número da vaga a ser cadastrada: ");
        int numVaga = escolhaUsuario(sc);
        Tipo tipo = lerTipo(sc);
        if (gestor.cadastrarVaga(numVaga, tipo)) {
            System.out.println("Vaga cadastrada com sucesso!");
        } else {
            System.out.println("Já existe uma vaga com esse número. Não foi possível realizar o cadastro.");
        }
        return;
    }

    static void sistemaGestaoExcluirVaga(Scanner sc) {
        System.out.print("Digite o número da vaga a ser excluída: ");
        int numVaga = escolhaUsuario(sc);
        if (gestor.excluirVaga(numVaga)) {
            System.out.println("Vaga excluída com sucesso!");
        } else {
            System.out.println("Número não encontrado. Não foi possível excluir a sua vaga.");
        }
    }

    static void sistemaGestaoAlterarDadosVagas(Scanner sc) {
        while (true) {
            System.out.print("Digite o número da vaga que você deseja alterar (digite 0 para retornar ao menu): ");
            int escolha = escolhaUsuario(sc);
            if (escolha == 0) 
                return;
            else alterarVaga(escolha, sc);
        }
    }

    static void alterarVaga(int numVaga, Scanner sc) {
        Vaga vaga = sistema.encontrarVaga(numVaga);
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
                System.out.print("Digite o novo valor para o número: ");
                int novoNumero = escolhaUsuario(sc);
                vaga.alterarNumero(novoNumero);
                System.out.println("Número alterado com sucesso!");
                break;
            case 2:
                System.out.print("Digite o novo valor para o tipo (utilitario, automovel, motocicleta): ");
                Tipo novoTipo = lerTipo(sc);
                vaga.alterarTipo(novoTipo);
                System.out.println("Tipo alterado com sucesso!");
                break;
            case 3:
                System.out.print("Digite o novo valor para o estado (livre, ocupado): ");
                Estado novoEstado = lerEstado(sc);
                vaga.alterarEstado(novoEstado);
                System.out.println("Estado alterado com sucesso!");
                break;
            default:
                System.out.println("Entrada inválida.");
        }
    }

    /*
     * 
     * GESTÃO - ALTERAR TAMANHO DAS COLUNAS NAS LISTAGENS
     * 
     */

    static void sistemaGestaoAlterarLarguraColunas(Scanner sc) {
        while (true) {
            System.out.println("Largura atual das colunas: " + sistema.getLarguraColuna());
            System.out.print("Digite o novo valor para a largura das colunas [valor mínimo: 14] (digite 0 para retornar): ");
            int novoValor = escolhaUsuario(sc);
            if (novoValor == 0) return;
            else if (novoValor > 14) gestor.alteraTamanhoColuna(novoValor);
            else System.out.println("Valor inválido. Tente novamente.");
        }
    }



    /*
     * 
     * GESTÃO - FUNÇÕES DE ALTERAÇÃO DE TARIFA
     * 
     */

    static void sistemaGestaoTarifa(Scanner sc) {
        while (true) {
            tarifa.menu();
            int escolha = escolhaUsuario(sc);
            System.out.print("Digite o novo valor da tarifa: ");
            double novoValor = entradaDouble(sc);
            switch (escolha) {
                case 1:
                    tarifa.alterarTarifaUtilitario(novoValor);
                    break;
                case 2:
                    tarifa.alterarTarifaAutomovel(novoValor);
                    break;
                case 3:
                    tarifa.alterarTarifaMotocicleta(novoValor);
                    break;
                case 4:
                    return;
            }
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
        int escolha = -1;
        while (escolha < 0) {
            try {
                escolha = sc.nextInt();
                sc.nextLine(); // Consome o '\n'
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Tente novamente.");
                sc.nextLine(); // Consome o '\n' em caso de erro
            }
        }
        return escolha;
    }

    static double entradaDouble(Scanner sc) {
        double entrada = -1;
        while (entrada < 0) {
            try {
                entrada = sc.nextDouble();
                sc.nextLine(); // Consome o '\n'
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Tente novamente.");
                sc.nextLine(); // Consome o '\n' em caso de erro
            }
        }
        return entrada;
    }

    static Tipo lerTipo(Scanner sc) {
        while (true) {
            System.out.println("Digite o tipo da vaga: ");
            Tipo.listarOpcoes();
            String entrada = sc.nextLine();
            Tipo tipo = Tipo.stringParaTipo(entrada);
            if (tipo != null) {
                return tipo;
            } else {
                System.out.println("Tipo inválido. Tente novamente.");
            }
        }
    }

    static Estado lerEstado(Scanner sc) {
        while (true) {
            System.out.println("Digite o estado da vaga: ");
            Estado.listarOpcoes();
            String entrada = sc.nextLine();
            Estado estado = Estado.stringParaEstado(entrada);
            if (estado != null) {
                return estado;
            } else {
                System.out.println("Tipo inválido. Tente novamente.");
            }
        }
    }
}
