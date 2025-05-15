public class Sistema {
    Vaga[] vagas = new Vaga[100];
    int numVagas;

    Veiculo[] veiculos = new Veiculo[5000];
    int numVeiculos;

    RegistroEstacionamento[] registros = new RegistroEstacionamento[10000];
    int numRegistros;

    int larguraColuna = 14; // 14 - Tamanho mínimo e default

    Vaga encontrarVaga(int numVaga) {
        if (numVaga > 0) {
            for (int i = 0; i < numVagas; i++) {
                if (numVaga == vagas[i].numero) {
                    return vagas[i];
                }
            }
        }
        return null;
    }

    public int getLarguraColuna() {
        return larguraColuna;
    }
    public void setLarguraColuna(int larguraColuna) {
        this.larguraColuna = larguraColuna;
    }
}
