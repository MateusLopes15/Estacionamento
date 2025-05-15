public class Gestor {
    Sistema sistema;
    Tarifa tarifa;
    int[] largurasColunaVagas = new int[] { 14, 14, 14, 14 }; // 14 - Tamanho mínimo e default
    // private int[] largurasColunaRegistro; -> USAR ISSO QUANDO MODELAR A LISTAGEM
    // DOS REGISTROS DO ESTACIONAMENTO!

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
                    sistema.vagas[i] = sistema.vagas[i + 1];
                }
                sistema.vagas[sistema.numVagas - 1] = null;
                sistema.numVagas--;
                return true;
            }
        }
        return false;
    }

    public void listarVagas() {
        StringBuilder formatacaoBuilder = new StringBuilder();
        for (int i = 0; i < largurasColunaVagas.length; i++) {
            formatacaoBuilder.append("%-").append(largurasColunaVagas[i]).append("s");
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

    public boolean alteraTamanhoColuna(int posicao, int novoTamanho) {
        if (novoTamanho >= 14) {
            this.largurasColunaVagas[posicao] = novoTamanho;
            return true;
        } else {
            return false;
        }
    }
}
