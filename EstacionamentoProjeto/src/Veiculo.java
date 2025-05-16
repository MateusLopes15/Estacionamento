import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Veiculo {
    String placa;
    String modelo;
    String cor;
    String marca;
    Tipo tipo;
    boolean validaPlacaMercosul(String placa) {
        Pattern pattern = Pattern.compile("^[a-zA-Z]{3}[0-9][A-Za-z0-9][0-9]{2}$");
        Matcher matcher = pattern.matcher(placa); // Expressão regular para a validação de placas no formato Mercosul
        
        return matcher.matches(); // Retorna se a placa está na conformidade do formato Mercosul
        
    }
}
