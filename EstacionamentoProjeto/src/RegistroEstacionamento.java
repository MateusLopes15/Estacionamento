import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class RegistroEstacionamento {
    Veiculo veiculo;
    LocalDateTime entrada;
    LocalDateTime saida;
    double valorCobrado;

    void listarRegistro(String formatacaoColunas) {
        String dataEntrada = entrada.toLocalDate().toString();
        String horaEntrada = entrada.toLocalTime().truncatedTo(ChronoUnit.SECONDS).toString();
        String dataSaida = "N/A";
        String horaSaida = "N/A";

        if (saida != null) {
            dataSaida = saida.toLocalDate().toString();
            horaSaida = saida.toLocalTime().truncatedTo(ChronoUnit.SECONDS).toString();
        }

        System.out.printf(formatacaoColunas,
                dataEntrada, horaEntrada, dataSaida, horaSaida,
                veiculo.recebePlaca(), veiculo.recebeModelo());
        System.out.println();
    }

}
