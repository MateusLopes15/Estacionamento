import java.util.Random;
import java.time.LocalDateTime;

public class Gestor {
    Sistema sistema;
    Tarifa tarifa;

    public boolean cadastrarVaga(int numVaga, Tipo tipo) {
        for (int i = 0; i < sistema.vagas.length; i++) {
            if (sistema.vagas[i] != null && sistema.vagas[i].numero == numVaga) {
                return false;
            }

            if (sistema.vagas[i] == null) {
                Vaga vaga = new Vaga();
                vaga.numero = numVaga;
                vaga.tipo = tipo;
                vaga.estado = Estado.LIVRE;
                sistema.vagas[i] = vaga;
                sistema.numVagas++;
                return true;
            }
        }
        return false;
    }

    public boolean excluirVaga(int numVaga) {
        for (int i = 0; i < sistema.numVagas; i++) {
            if (sistema.vagas[i].numero == numVaga) {
                for (int j = i; j < sistema.numVagas - 1; j++) {
                    sistema.vagas[j] = sistema.vagas[j + 1];
                }
                sistema.vagas[sistema.numVagas - 1] = null;
                sistema.numVagas--;
                return true;
            }
        }
        return false;
    }

    public void listarVagas() {
        final int NUM_CAMPOS_VAGAS = 4;
        String formatacaoColunas = retornaStringFormatacao(NUM_CAMPOS_VAGAS);

        System.out.println("--------------------------------------------------------");
        System.out.println("------------------ LISTAGEM DAS VAGAS ------------------");
        System.out.println("--------------------------------------------------------");
        System.out.printf(formatacaoColunas, "VAGA", "NUMERO", "TIPO", "ESTADO");
        System.out.println();

        for (int i = 0; i < sistema.numVagas; i++) {
            if (sistema.vagas[i] != null) {
                sistema.vagas[i].listarVaga(formatacaoColunas, i);
            }
        }
    }

    public boolean alteraTamanhoColuna(int novoTamanho) {
        if (novoTamanho >= 14) {
            sistema.larguraColuna = novoTamanho;
            return true;
        } else {
            return false;
        }
    }

        public void listarRegistros(LocalDateTime inicio, LocalDateTime fim) {
        final int NUM_CAMPOS_REGISTRO = 6;
        String formatacaoColunas = retornaStringFormatacao(NUM_CAMPOS_REGISTRO);

        System.out.println("--------------------------------------------------------");
        System.out.println("---------------- LISTAGEM DOS REGISTROS ----------------");
        System.out.println("--------------------------------------------------------");
        System.out.printf(formatacaoColunas, "DATA ENTRADA", "HORA ENTRADA", "DATA SAÍDA", "HORA SAÍDA", "PLACA", "MODELO");
        System.out.println();

        for (int i = 0; i < sistema.numRegistros; i++) {
            RegistroEstacionamento registro = sistema.registros[i];

            if (registro != null) {
                LocalDateTime entrada = registro.entrada;
                LocalDateTime saida = registro.saida;
                boolean deveListar = false;

                if (inicio == null && fim == null) {
                    // Sem filtro, lista todos os registros
                    deveListar = true;
                } 
                else if (inicio != null && fim != null) {
                    // Lista se entrada >= inicio && saida <= fim
                    if (entrada != null && saida != null &&
                        !entrada.isBefore(inicio) && !saida.isAfter(fim)) {
                        deveListar = true;
                    }
                } 
                else if (inicio != null && fim == null) {
                    // Lista registros sem saída (ainda no estacionamento) com entrada >= inicio
                    if (entrada != null && saida == null && !entrada.isBefore(inicio)) {
                        deveListar = true;
                    }
                }

                if (deveListar) {
                    registro.listarRegistro(formatacaoColunas);
                }
            }
        }
    }


    public void listarVeiculos() {
        final int NUM_CAMPOS_VEICULO = 5;
        String formatacaoColunas = retornaStringFormatacao(NUM_CAMPOS_VEICULO);
        System.out.println("--------------------------------------------------------");
        System.out.println("---------------- LISTAGEM DOS VEÍCULOS ----------------");
        System.out.println("--------------------------------------------------------");
        System.out.printf(formatacaoColunas, "PLACA", "MODELO", "COR", "MARCA", "TIPO");
        System.out.println();
        for (int i = 0; i < sistema.numVeiculos; i++) {
            if (sistema.veiculos[i] != null)
                sistema.veiculos[i].listarVeiculo(formatacaoColunas);
        }
    }

    public void initVagas() { // Gera 50 vagas automaticamente, função destinada a ser utilizada com a função
                              // init() do main()
        Random r = new Random();
        for (int i = 0; i < sistema.vagas.length / 2; i++) {
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
            cadastrarVaga(i + 1, tipo);
        }
    }

    private String retornaStringFormatacao(int tamanho) {
        StringBuilder formatacaoBuilder = new StringBuilder();
        for (int i = 0; i < tamanho; i++) {
            formatacaoBuilder.append("%-").append(sistema.larguraColuna).append("s");
        }
        String formatacao = formatacaoBuilder.toString();
        return formatacao;
    }

    void menuVagas() {
        System.out.println("--------------------------------------------------------");
        System.out.println("----- SISTEMA DE GESTÃO DO PÁTIO DE ESTACIONAMENTO -----");
        System.out.println("--------------------------------------------------------");
        System.out.println("1. Gerenciar vagas");
        System.out.println("2. Gerenciar listagens");
        System.out.println("3. Alterar tarifas ");
        System.out.println("4. Alterar tamanho das colunas nas listagens");
        System.out.println("5. Retornar ao menu principal");
    }

    void menuGerenciamentoVagas() {
        System.out.println("--------------------------------------------------------");
        System.out.println("---------------- GERENCIAMENTO DE VAGAS ----------------");
        System.out.println("--------------------------------------------------------");
        System.out.println("Selecione a sua opção: ");
        System.out.println("1 - Cadastrar vaga");
        System.out.println("2 - Excluir vaga");
        System.out.println("3 - Retornar ao menu principal");
    }

    void menuListagens() {
        System.out.println("--------------------------------------------------------");
        System.out.println("------------------- MENU DE LISTAGENS ------------------");
        System.out.println("--------------------------------------------------------");
        System.out.println("1 - Listar todas vagas cadastradas");
        System.out.println("2 - Listar todos registros cadastrados");
        System.out.println("3 - Listar os registros em um intervalo de tempo");
        System.out.println("4 - Listar todos os veículos estacionados atualmente");
        System.out.println("5 - Listar todos veículos cadastrados");
        System.out.println("6 - Voltar");
    }
}
