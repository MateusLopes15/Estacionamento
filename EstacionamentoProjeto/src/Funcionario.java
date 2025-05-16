import java.time.LocalDateTime;
import java.util.Random;

public class Funcionario {
    Sistema sistema;
    Tarifa tarifa;
    Veiculo veiculo;

   
    public void initVeiculos() {
        String placa = "";
        String modeloString;
        String corString;
        String marcaString;
        Random random = new Random();
        
        String[] cor = { "CINZA", "VERMELHO", "PRATA", "PRETO", "AZUL" };
        String[] modelo = { "GOL", "ONIX", "HB20", "RENEGADE", "COMPASS", "COROLLA", "HILUX", "STRADA", "ARGO",
                "KWID" };
        String[] marca = { "VOLKSWAGEN", "CHEVROLET", "HYUNDAI", "JEEP", "JEEP", "TOYOTA", "TOYOTA", "FIAT", "FIAT",
                "RENAULT" };
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) { // Cria veículos para
              veiculo = new Veiculo();  
              for (int x = 0; x < 3; x++) {
                int numeroAleatorioo = random.nextInt(26) + 65;
                sb.append((char) numeroAleatorioo);
            }                                                                             // inicializar o programa
            for (int j = 0; j < 4; j++) {// Cria uma placa válida aleatoria
                int numeroAleatorio = random.nextInt(10);
                sb.append(Integer.toString(numeroAleatorio));
            }
            

            placa = sb.toString();
            veiculo.placa = placa;// adiciona placa ao veículo

            sb.setLength(0); // zera o stringbuilder para que não vire apenas uma placa ENORME

            Tipo tipo = null;
            switch (random.nextInt(3)) {
                case 0:
                    tipo = Tipo.UTILITARIO;
                    break;
                case 1:
                    tipo = Tipo.AUTOMOVEL;
                    break;
                case 2:
                    tipo = Tipo.MOTOCICLETA;
                    break;
            }

            corString = cor[random.nextInt(cor.length)];
            modeloString = modelo[random.nextInt(modelo.length)];
            marcaString = marca[random.nextInt(marca.length)];
            veiculo.modelo = modeloString;
            veiculo.cor = corString;
            veiculo.marca = marcaString;
            veiculo.tipo = tipo;
            
            
            adicionarVeiculo(veiculo);

        }

    }

    public boolean adicionarVeiculo(Veiculo veiculo) {

       

        for (int i = 0; i < sistema.veiculos.length; i++) {
            if (sistema.veiculos[i] != null && sistema.veiculos[i].placa == veiculo.placa) {
                return false;
            }

            if (sistema.veiculos[i] == null) {

                sistema.veiculos[i] = veiculo;
                sistema.numVeiculos++;
                return true;
            }
        }
        return false;

    }

    public boolean removerVeiculoVaga(String placa, LocalDateTime horasaida) {
        return true;
    }

    public boolean verificaExistenciaVeiculo(String placa) {
        for (int i = 0; i < sistema.veiculos.length; i++) {
            if (sistema.veiculos[i] != null && sistema.veiculos[i].placa.equals(placa)) {
                return true;
            }

        }
        return false;
    }

    public boolean adicionarVeiculoVaga(String placa) {
        return true;
    }

}
