import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Veiculo {
    String placa;
    String modelo;
    String cor;
    String marca;
    Tipo tipo;

    boolean alteraPlaca(String novaPlaca) {
        if (Veiculo.validaPlacaMercosul(novaPlaca)) {
            this.placa = novaPlaca;
            return true;
        }
        return false;
    }

    static boolean validaPlacaMercosul(String placa) {
        Pattern pattern = Pattern.compile("^[A-Z]{3}[0-9][A-Z][0-9]{2}$");
        Matcher matcher = pattern.matcher(placa); // Expressão regular para a validação de placas no formato Mercosul
        return (placa != null && matcher.matches()); // Retorna se a placa está na conformidade do formato Mercosul
    }

    void listarVeiculo(String formatacaoColunas) {
        System.out.printf(formatacaoColunas, this.placa, this.modelo, this.cor, this.marca, this.tipo);
        System.out.println();
    }

    void alteraModelo(String novoModelo) {
        this.modelo = novoModelo;
    }

    void alteraCor(String novaCor) {
        this.cor = novaCor;
    }

    void alteraMarca(String novaMarca) {
        this.marca = novaMarca;
    }

    void alteraTipo(Tipo novoTipo) {
        this.tipo = novoTipo;
    }

    String recebePlaca() {
        return this.placa;
    }

    String recebeModelo() {
        return this.modelo;
    }
}
