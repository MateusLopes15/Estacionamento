import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Duration;

public class Sistema {
    Tarifa tarifa = new Tarifa();
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

    public void listarVeiculos() {

        for (int i = 0; i < numVeiculos; i++) {
            if (this.veiculos[i] != null) {
                System.out.println("Placa: " + this.veiculos[i].placa);
                System.out.println("Modelo: " + this.veiculos[i].modelo);
                System.out.println("Cor: " + this.veiculos[i].cor);
                System.out.println("Marca: " + this.veiculos[i].marca);
                System.out.println("Tipo: " + this.veiculos[i].tipo);
                System.out.println("------------------");
            }

        }
    }

    public void listarRegistro() {
        for (int i = 0; i < numRegistros; i++) {
            if (this.registros[i] != null) {
                System.out.println("Veiculo: " + this.registros[i].veiculo.placa);
                System.out.println("Data Entrada: " + this.registros[i].entrada.toLocalDate());
                System.out.println("Hora Entrada: " + this.registros[i].entrada.toLocalTime());

                if (this.registros[i].saida != null) {
                    System.out.println("Data Saida: " + this.registros[i].saida.toLocalDate());
                    System.out.println("Hora Saida: " + this.registros[i].saida.toLocalTime());
                } else {
                    System.out.println("Data Saida: Ainda não saiu");
                    System.out.println("Hora Saida: Ainda não saiu");
                }

                System.out.println("-------------------------"); // Separador para melhor visualização
            } else {
                System.out.println("Nenhum Registro cadastrado");
            }
        }
    }

    public boolean adicionaVeiculoRegistro(Veiculo veicular) {// ARRUMAR PRA CONSEGUIR ADICIONAR MAIS DE UMA VEZ O MESMO
                                                              // VEICULO

        for (int j = 0; j < numRegistros; j++) {
            if (registros[j] != null) {
                if (this.registros[j].veiculo.placa == veicular.placa) {
                    if (this.registros[j].entrada != null && this.registros[j].saida == null) {
                        System.out.println(
                                "Esse veículo já foi registrado que entrou e ainda não saiu do estacionamento");
                        return false;
                    } else {
                        for (int i = numRegistros - 1; i < registros.length; i++) {
                            this.registros[i] = new RegistroEstacionamento();
                            if (registros[i].veiculo == null) {

                                this.registros[i].veiculo = veicular;
                                this.registros[i].entrada = LocalDateTime.now();
                                return true;
                            }

                        }

                    }

                }
            }

        }
        if (numRegistros == 1) {
            this.registros[0] = new RegistroEstacionamento();

            this.registros[0].veiculo = veicular;
            this.registros[0].entrada = LocalDateTime.now();
            return true;
        } else {

            for (int i = numRegistros - 1; i < registros.length; i++) {
                this.registros[i] = new RegistroEstacionamento();
                if (registros[i].veiculo == null) {

                    this.registros[i].veiculo = veicular;
                    this.registros[i].entrada = LocalDateTime.now();
                    return true;
                }

            }
        }
        return false;

    }

    // public void removerVeiculoVagaadicionaRegistro(Veiculo veicular){
    // RegistroEstacionamento registrar = new RegistroEstacionamento();
    // registrar = retornaregistro(veicular.placa);

    // if(registrar!=null){

    // }

    // }
    public void retornaregistro(String placa) {

        for (int i = 0; i < numRegistros; i++) {
            if (registros[i] != null) {
                if (registros[i].veiculo.placa != null) {

                    if (registros[i].veiculo.placa.equals(placa)) {

                        registros[i].saida = LocalDateTime.now();

                        LocalTime horaEntrada = registros[i].entrada.toLocalTime();

                        LocalTime horaSaida = registros[i].saida.toLocalTime();

                        Duration duracao = Duration.between(horaEntrada, horaSaida);
                        long horasPermanencia = duracao.toHours();
                        switch(registros[i].veiculo.tipo){
                            case UTILITARIO:

                            registros[i].valorCobrado = 12 + horasPermanencia * tarifa.tarifaHora[0];
                                break;                                              
                            case AUTOMOVEL:
                            registros[i].valorCobrado = 10 + horasPermanencia * tarifa.tarifaHora[1];
                            break;
                            case MOTOCICLETA:
                            registros[i].valorCobrado = 8 + horasPermanencia * tarifa.tarifaHora[2];
                            break;

                        }

                        
                        System.out.println("Para o veículo de placa: " + registros[i].veiculo.placa
                                + " o pagamento é de: R$ " + registros[i].valorCobrado);
                        removeVaga(placa);
                    }
                }
            }
        }
    }
/*
 * VALORES DEFAULT:
 * UTILITÁRIO: R$12,00 fixo + R$4,00 por hora -> Posição 0 no array
 * AUTOMÓVEL: R$10,00 fixo + R$3,00 por hora -> Posição 1 no array
 * MOTOCICLETA: R$ 8,00 fixo + R$2,00 por hora -> Posição 2 no array
 */
    public void removeVaga(String placa) {
        for (int i = 0; i < numVagas; i++) {
            if (vagas[i].veiculo != null) {
                if (vagas[i].veiculo.placa.equals(placa)) {
                    vagas[i].estado = Estado.LIVRE;
                }
            }

        }
    }

}
