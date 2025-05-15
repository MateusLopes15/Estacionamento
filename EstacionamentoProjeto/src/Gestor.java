import java.util.Random;

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

        StringBuilder formatacaoBuilder = new StringBuilder();
        for (int i = 0; i < NUM_CAMPOS_VAGAS; i++) {
            formatacaoBuilder.append("%-").append(sistema.larguraColuna).append("s");
        }
        String formatacaoColunas = formatacaoBuilder.toString();

        System.out.println("--------------------------------------------------------");
        System.out.println("------------------ LISTAGEM DAS VAGAS ------------------");
        System.out.println("--------------------------------------------------------");
        System.out.printf(formatacaoColunas, "VAGA", "NUMERO", "TIPO", "ESTADO");
        System.out.println();

        for (int i = 0; i < sistema.numVagas; i++) {
            if (sistema.vagas[i] != null) {
                System.out.printf(formatacaoColunas, (i + 1), sistema.vagas[i].numero, sistema.vagas[i].tipo,
                        sistema.vagas[i].estado);
                System.out.println();
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

    public void initGeraVagas() { // Gera 50 vagas automaticamente, função destinada a ser utilizada com a função init() do main()
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

    void menuPrincipal() {
        System.out.println("--------------------------------------------------------");
        System.out.println("---- SISTEMA DE CONTROLE DO PÁTIO DE ESTACIONAMENTO ----");
        System.out.println("--------------------------------------------------------");
        System.out.println();
        System.out.println("Selecione a sua opção: ");
        System.out.println("1 - Sistema de Atendimento");
        System.out.println("2 - Sistema de Gestão");
        System.out.println("3 - Sair");
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
        System.out.println("3 - Listar vagas");
        System.out.println("4 - Alterar dados das vagas");
        System.out.println("5 - Retornar ao menu principal");
    }
}
