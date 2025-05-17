import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Sistema sistema;
    static Tarifa tarifa;
    static Gestor gestor;
    static Funcionario funcionario;
    static RegistroEstacionamento registroEstacionamento;

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
            System.out.println("3 - Alteração de dados");
            System.out.println("4 - Sair");
            int escolha = escolhaUsuario(sc);
            switch (escolha) {
                case 1:
                    sistemaFuncionario(sc);
                    break;
                case 2:
                    sistemaGestao(sc);
                    break;
                case 3:
                    sistemaAlteracaoDados(sc);
                case 4:
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
        registroEstacionamento = new RegistroEstacionamento();

        gestor.sistema = sistema;
        gestor.tarifa = tarifa;
        funcionario.sistema = sistema;
        funcionario.tarifa = tarifa;
        funcionario.registroEstacionamento = registroEstacionamento;

        gestor.initVagas(); // Cadastra 50 vagas através das funções do gestor
        funcionario.initVeiculos(); // Cadastra veículos
        funcionario.initEstacionamento();

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
                    listagens(sc);
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

    static void listagens(Scanner sc) {
        while (true) {
            gestor.menuListagens();
            int escolha = escolhaUsuario(sc);
            switch (escolha) {
                case 1:
                    gestor.listarVagas();
                    break;
                case 2:
                    gestor.listarRegistros();
                    break;
                case 3:
                    gestor.listarVeiculos();
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

    /*
     * 
     * GESTÃO - ALTERAR TAMANHO DAS COLUNAS NAS LISTAGENS
     * 
     */

    static void sistemaGestaoAlterarLarguraColunas(Scanner sc) {
        while (true) {
            System.out.println("Largura atual das colunas: " + sistema.larguraColuna);
            System.out.print(
                    "Digite o novo valor para a largura das colunas [valor mínimo: 14] (digite 0 para retornar): ");
            int novoValor = escolhaUsuario(sc);
            if (novoValor == 0)
                return;
            else if (novoValor > 14)
                gestor.alteraTamanhoColuna(novoValor);
            else
                System.out.println("Valor inválido. Tente novamente.");
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
            if (escolha == 4) {
                return;
            } else {
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
                    default:
                        System.out.println("Entrada inválida. Tente novamente.");
                }
            }
        }
    }

    /*
     * 
     * FUNÇÕES DO SISTEMA DE ATENDIMENTO
     * 
     */

    static void sistemaFuncionario(Scanner sc) {
        while (true) {
            System.out.println("---------------------------------------------------------------");
            System.out.println("---------- SISTEMA DE ATENDIMENTO DO ESTACIONAMENTO -----------");
            System.out.println("---------------------------------------------------------------");
            System.out.println("Selecione a sua opção: ");
            System.out.println("1 - Entrada de veículos");
            System.out.println("2 - Saída de veículos");
            System.out.println("3 - Cadastro de veículos");
            System.out.println("4 - Voltar");
            int escolha = escolhaUsuario(sc);
            switch (escolha) {
                case 1:
                    sistemaAdicionarVeiculoemVaga(sc);
                    break;
                case 2:
                    removerVeiculoVaga(sc);
                    break;
                case 3:
                    sistemaCadastroVeiculo(sc);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Entrada inválida. Tente novamente.");
            }
        }

    }

    static void sistemaAdicionarVeiculoemVaga(Scanner sc) {
        while (true) {
            System.out.print("Digite a placa do veículo que deseja dar entrada: ");
            String placaVeiculo = sc.nextLine().toUpperCase().trim();
            if (!Veiculo.validaPlacaMercosul(placaVeiculo)) {
                System.out.println("Placa inválida. Deve seguir o padrão Mercosul (AAA1B23)");
                continue;
            }

            if (funcionario.retornarVeiculo(placaVeiculo) == null) {
                System.out.println("A placa inserida não existe no sistema.");
                System.out.println(
                        "Digite 1 para cadastrar o veículo ou 2 para redigitar a placa. Alternativamente, digite 0 para retornar.");

                int choice = escolhaUsuario(sc);
                switch (choice) {
                    case 1:
                        sistemaCadastroVeiculo(sc);
                        System.out.println("Agora, com o veículo cadastrado, você poderá dar a entrada normalmente.");
                        return;
                    case 2:
                        continue;
                    case 0:
                        System.out.println("Operação cancelada.");
                        return;
                    default:
                        System.out.println("Entrada inválida. Tente novamente.");
                }
            } else if (funcionario.verificaVeiculoEstacionado(placaVeiculo)) {
                System.out.println("Veículo já se encontra estacionado.");
                return;
            } else {
                funcionario.adicionarVeiculoVaga(placaVeiculo);
                System.out.println("Entrada do veículo efetuada com sucesso!");
                return;
            }
        }
    }

    static void sistemaCadastroVeiculo(Scanner sc) {
        Veiculo veiculo = new Veiculo();

        System.out.println("-------------------------------------------------------------");
        System.out.println("------------- SISTEMA DE CADASTRO DE VEÍCULOS ---------------");
        System.out.println("-------------------------------------------------------------");

        String placaString;
        while (true) {
            System.out.print("Qual a placa? ");
            placaString = sc.nextLine().trim().toUpperCase();
            if (!Veiculo.validaPlacaMercosul(placaString)) {
                System.out.println("Placa inválida. Deve seguir o padrão Mercosul (AAA1B23).");
            } else if (!(funcionario.retornarVeiculo(placaString) == null)) {
                System.out.println("Essa placa já está cadastrada no sistema.");
                return;
            } else {
                System.out.println("Placa válida");
                break; // placa válida e única
            }
        }

        System.out.print("Qual o modelo? ");
        String modeloString = sc.nextLine().trim().toUpperCase();

        System.out.print("Qual a cor? ");
        String corString = sc.nextLine().trim().toUpperCase();

        System.out.print("Qual a marca? ");
        String marcaString = sc.nextLine().trim().toUpperCase();

        System.out.println("Digite o tipo do veículo (utilitario, automovel, motocicleta): ");
        Tipo tipo = lerTipo(sc);

        // Define os atributos
        veiculo.placa = placaString;
        veiculo.modelo = modeloString;
        veiculo.cor = corString;
        veiculo.marca = marcaString;
        veiculo.tipo = tipo;

        if (funcionario.adicionarVeiculo(veiculo)) {
            System.out.println("Veículo cadastrado com sucesso!");
        } else {
            System.out.println("Erro: já existe um veículo com essas informações. Não foi possível cadastrar.");
        }
    }

    static void removerVeiculoVaga(Scanner sc) {
        System.out.print("Digite a placa do veículo: ");
        sistema.retornaRegistro(sc.nextLine());
    }

    /*
     * 
     * SISTEMA DE ALTERAÇÃO DE DADOS
     * 
     */

    static void sistemaAlteracaoDados(Scanner sc) {
        while (true) {
            System.out.println("--------------------------------------------------------");
            System.out.println("------------- SISTEMA DE ALTERAÇÃO DE DADOS ------------");
            System.out.println("--------------------------------------------------------");
            System.out.println();
            System.out.println("Selecione a sua opção: ");
            System.out.println("1 - Alterar dados dos veículos");
            System.out.println("2 - Alterar dados das vagas");
            System.out.println("3 - Sair");
            int escolha = escolhaUsuario(sc);
            switch (escolha) {
                case 1:
                    editarInformacoesVeiculo(sc);
                    break;
                case 2:
                    alterarDadosVagas(sc);
                    break;
                case 3:
                    return;
            }
        }
    }

    static void editarInformacoesVeiculo(Scanner sc) {
        System.out.println("Digite a placa do veículo que deseja alterar informações");
        String placa = sc.nextLine().toUpperCase();
        Veiculo veiculo = funcionario.retornarVeiculo(placa);
        if (veiculo != null) {
            alterarVeiculo(veiculo, sc);
        } else {
            System.out.println("Não existe um veículo com a placa informada. Tente novamente.");
        }
    }

    static void alterarVeiculo(Veiculo veiculo, Scanner sc) {
        while (true) {
            System.out.println("----------------------------------------------------------------");
            System.out.println("------------- SISTEMA DE ALTERAÇÃO DE VEÍCULO ------------------");
            System.out.println("----------------------------------------------------------------");
            System.out.println("FAÇA TODAS AS ALTERAÇÕES QUE QUISER E DEPOIS ESCOLHA A OPÇÃO DE SAIR");
            System.out.println("PODE FAZER A ALTERAÇÃO DE DIFERENTES PARTES DO VEÍCULO DE UMA VEZ SÓ");

            System.out.println("Deseja modificar:");
            System.out.println("1 - PLACA");
            System.out.println("2 - MODELO");
            System.out.println("3 - MARCA");
            System.out.println("4 - COR");
            System.out.println("5 - TIPO");
            System.out.println("6 - Sair");

            int escolha = escolhaUsuario(sc);
            switch (escolha) {
                case 1:
                    System.out.print("Digite a nova placa: ");
                    String novaPlaca = sc.nextLine().toUpperCase().trim();
                    if (veiculo.alteraPlaca(novaPlaca) && !(funcionario.retornarVeiculo(novaPlaca) == null)) {
                        System.out.println("Placa atualizada com sucesso.");
                    } else {
                        System.out.println("Placa inválida ou já existente no sistema.");
                    }
                    break;
                case 2:
                    System.out.print("Digite o novo modelo: ");
                    String novoModelo = sc.nextLine().toUpperCase().trim();
                    veiculo.alteraModelo(novoModelo);
                    System.out.println("Modelo atualizado com sucesso.");
                    break;
                case 3:
                    System.out.print("Digite a nova marca: ");
                    String novaMarca = sc.nextLine().toUpperCase().trim();
                    veiculo.alteraMarca(novaMarca);
                    System.out.println("Marca atualizada com sucesso.");
                    break;
                case 4:
                    System.out.print("Digite a nova cor: ");
                    String novaCor = sc.nextLine().toUpperCase().trim();
                    veiculo.alteraCor(novaCor);
                    System.out.println("Cor atualizada com sucesso.");
                    break;
                case 5:
                    System.out.println("Digite o novo tipo de veículo: ");
                    Tipo novoTipo = lerTipo(sc);
                    veiculo.alteraTipo(novoTipo);
                    System.out.println("Tipo atualizado com sucesso.");
                    break;
                case 6:
                    return;
            }
        }
    }

    static void alterarDadosVagas(Scanner sc) {
        while (true) {
            System.out.print("Digite o número da vaga que você deseja alterar (digite 0 para retornar ao menu): ");
            int escolha = escolhaUsuario(sc);
            if (escolha == 0)
                return;
            else
                alterarVaga(escolha, sc);
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
