package Elementos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Veiculo {
    private String placa;
    private String modelo;
    private String cor;
    private String marca;
    private Tipo tipo;

    public boolean inicializaVeiculo(String placa, String modelo, String cor, String marca, Tipo tipo) {
        placa = placa.toUpperCase();
        if (validaPlacaMercosul(placa)) {
            this.placa = placa;
            this.modelo = modelo;
            this.cor = cor;
            this.marca = marca;
            this.tipo = tipo;
            return true;
        } else {
            return false;
        }
    }

    public void imprimeInformacoesVeiculo() {
        System.out.println("PLACA:  " + this.placa);
        System.out.println("MODELO: " + this.modelo);
        System.out.println("COR:    " + this.cor);
        System.out.println("MARCA:  " + this.marca);
        System.out.println("TIPO:   " + this.tipo);
    }

    public String getPlaca() {
        return placa;
    }

    public Tipo getTipo() {
        return tipo;
    }

    private boolean validaPlacaMercosul(String placa) {
        Pattern pattern = Pattern.compile("^[a-zA-Z]{3}[0-9][A-Za-z0-9][0-9]{2}$");
        Matcher matcher = pattern.matcher(placa); // Expressão regular para a validação de placas no formato Mercosul
        return matcher.matches(); // Retorna se a placa está na conformidade do formato Mercosul
    }
}
