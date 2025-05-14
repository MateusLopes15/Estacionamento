package Elementos;

public class Vaga {
    public enum Estado {
        LIVRE,
        OCUPADO
    }

    private int numero;
    private Veiculo veiculoEstacionado;
    private Tipo tipo;
    private Estado estado;

    public void ocuparVaga(Veiculo veiculo) {
        if (veiculo != null && estado == Estado.LIVRE && veiculo.getTipo() == this.tipo) {
            this.veiculoEstacionado = veiculo;
            this.estado = Estado.OCUPADO;
        }
    }

    public void liberarVaga() {
        if (veiculoEstacionado != null && estado == Estado.OCUPADO) {
            this.veiculoEstacionado = null;
            this.estado = Estado.LIVRE;
        }
    }

    public void imprimeEstadoVaga() {
        System.out.println("NÚMERO:           " + this.numero);
        System.out.println("PLACA DO VEÍCULO: " + this.veiculoEstacionado.getPlaca());
        System.out.println("TIPO:             " + this.tipo);
        System.out.println("ESTADO:           " + this.numero);

    }
}
