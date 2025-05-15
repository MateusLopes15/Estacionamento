public class Vaga {
    int numero;
    Tipo tipo;
    Estado estado;
    Veiculo veiculo;

    void alterarNumero(int novoNumero) {
        this.numero = novoNumero;
    }

    void alterarTipo(Tipo novoTipo) {
        this.tipo = novoTipo;
    }

    void alterarEstado(Estado novoEstado) {
        this.estado = novoEstado;
    }

    void listarVaga(String formatacaoColunas, int indice) {
        System.out.printf(formatacaoColunas, (indice + 1), numero, tipo, estado);
        System.out.println();
    }
}
