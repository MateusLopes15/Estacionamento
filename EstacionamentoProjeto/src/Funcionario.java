import java.time.LocalDateTime;

public class Funcionario {
    Sistema sistema;
    Tarifa tarifa;
    Veiculo veiculo;

    

    public boolean adicionarVeiculo(Veiculo veiculo) {
        
        veiculo = new Veiculo();
        
        for (int i = 0; i < sistema.veiculos.length; i++) {
            if (sistema.veiculos[i] != null && sistema.veiculos[i].placa == veiculo.placa) {
                return false;
            }

            if (sistema.veiculos[i] == null) {
                
                sistema.veiculos[i]=veiculo;
            }
        }
        return false;

    }

    public boolean removerVeiculoVaga(String placa, LocalDateTime horasaida) {
        return true;
    }
    public boolean verificaExistenciaVeiculo(String placa){
         for (int i = 0; i < sistema.veiculos.length; i++) {
            if (sistema.veiculos[i] != null && sistema.veiculos[i].placa == veiculo.placa) {
                return true;
            }
            
        }
        return false;
    }
    public boolean adicionarVeiculoVaga(String placa) {
        return true;
    }

     
}
