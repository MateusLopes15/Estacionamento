import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Funcionario {
    Sistema sistema;
    Tarifa tarifa;
    Veiculo veiculo;

    public boolean adicionarVeiculo(String placa, String modelo, String cor, String marca, Tipo tipo) {
        veiculo = new Veiculo();
        for (int i = 0; i < sistema.veiculos.length; i++) {
            if (sistema.veiculos[i] != null && sistema.veiculos[i].placa == placa) {
                return false;
            }

            if (sistema.veiculos[i] == null) {
                veiculo.placa = placa;
                veiculo.modelo = modelo;
                veiculo.cor = cor;
                veiculo.marca = marca;
                veiculo.tipo = tipo;
                sistema.veiculos[i]=veiculo;
            }
        }
        return false;

    }

    public boolean removerVeiculoVaga(String placa, LocalDateTime horasaida) {
        return true;
    }

    public boolean adicionarVeiculoVaga(String placa, LocalDateTime horaentrada) {
        return true;
    }

     boolean validaPlacaMercosul(String placa) {
        Pattern pattern = Pattern.compile("^[a-zA-Z]{3}[0-9][A-Za-z0-9][0-9]{2}$");
        Matcher matcher = pattern.matcher(placa); // Expressão regular para a validação de placas no formato Mercosul
        
        return matcher.matches(); // Retorna se a placa está na conformidade do formato Mercosul
        
    }
}
