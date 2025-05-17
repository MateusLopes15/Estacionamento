import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class Funcionario {
    Sistema sistema;
    Tarifa tarifa;
    Veiculo veiculo;
    RegistroEstacionamento registroEstacionamento;

    public void initEstacionamento() {
        // Veiculo veiculo;
        // LocalDateTime entrada;
        // LocalDateTime saida;
        // double valorCobrado;
        for (int i = 0; i < 10; i++) {
            LocalDateTime entradaAleatoria = gerarLocalDateTimeAleatorio();
            LocalDateTime saidaAleatoria;
            Veiculo veicular = new Veiculo();
            if (sistema.veiculos[i] != null) {
                veicular = sistema.veiculos[i];
            }

            // Garantir que a saída seja posterior à entrada
            do {
                saidaAleatoria = gerarLocalDateTimeAleatorio();
            } while (saidaAleatoria.isBefore(entradaAleatoria) || saidaAleatoria.isEqual(entradaAleatoria));

            long horasDeDuracao = ChronoUnit.HOURS.between(entradaAleatoria, saidaAleatoria);
            sistema.numRegistros++;

            sistema.registros[i] = new RegistroEstacionamento();
            if (sistema.registros[i].veiculo == null) {

                // sistema.registros[i].veiculo = veicular;
                sistema.registros[i].entrada = entradaAleatoria;
                sistema.registros[i].saida = saidaAleatoria;
                sistema.registros[i].veiculo = veicular;
                if (veicular.tipo != null) {
                    switch (veicular.tipo) {
                        case UTILITARIO:
                            sistema.registros[i].valorCobrado = horasDeDuracao * tarifa.tarifaHora[0];
                            break;
                        case AUTOMOVEL:
                            sistema.registros[i].valorCobrado = horasDeDuracao * tarifa.tarifaHora[1];
                            break;
                        case MOTOCICLETA:
                            sistema.registros[i].valorCobrado = horasDeDuracao * tarifa.tarifaHora[2];
                            break;

                    }
                }

            }
        }

    }

    public static LocalDateTime gerarLocalDateTimeAleatorio() {
        Random random = new Random();

        int ano = 2020 + random.nextInt(6);
        int mes = 1 + random.nextInt(12);
        int dia = 1 + random.nextInt(LocalDate.of(ano, mes, 1).lengthOfMonth());

        int hora = random.nextInt(24);
        int minuto = random.nextInt(60);
        int segundo = random.nextInt(60);

        return LocalDateTime.of(ano, mes, dia, hora, minuto, segundo);
    }

    public void initVeiculos() {
        Random random = new Random();

        String[] cores = { "CINZA", "VERMELHO", "PRATA", "PRETO", "AZUL" };
        String[] automoveis = { "GOL/VOLKSWAGEN", "ONIX/CHEVROLET", "HB20/HYUNDAI", "COROLLA/TOYOTA",
                "CIVIC/HONDA",
                "CRONOS/FIAT", "SANDERO/RENAULT", "VERSA/NISSAN", "FOCUS/FORD", "ARGO/FIAT" };
        String[] utilitarios = { "HILUX/TOYOTA", "STRADA/FIAT", "DUSTER/RENAULT", "TORO/FIAT", "RANGER/FORD",
                "S10/CHEVROLET", "FRONTIER/NISSAN", "L200/MITSUBISHI", "AMAROK/VOLKSWAGEN", "TIGUAN/VOLKSWAGEN" };
        String[] motocicletas = { "CG 160/HONDA", "FAZER 250/YAMAHA", "BIZ 125/HONDA", "XRE 300/HONDA", "MT-03/YAMAHA",
                "POP 110i/HONDA", "NMAX 160/YAMAHA", "BROZ 160/HONDA", "Z400/KAWASAKI", "DUKE 390/KTM" };

        for (int i = 0; i < 10; i++) {
            Veiculo veiculo = new Veiculo();

            String placa = gerarPlacaMercosul(random);
            veiculo.alteraPlaca(placa);

            String[] modeloMarca;
            int r;
            switch (random.nextInt(3)) {
                case 0:
                    veiculo.alteraTipo(Tipo.UTILITARIO);
                    r = random.nextInt(utilitarios.length);
                    modeloMarca = utilitarios[r].split("/");
                    break;
                case 1:
                    veiculo.alteraTipo(Tipo.AUTOMOVEL);
                    r = random.nextInt(automoveis.length);
                    modeloMarca = automoveis[r].split("/");
                    break;
                default:
                    r = random.nextInt(motocicletas.length);
                    modeloMarca = motocicletas[r].split("/");
                    veiculo.alteraTipo(Tipo.MOTOCICLETA);
            }

            veiculo.alteraModelo(modeloMarca[0]);
            veiculo.alteraMarca(modeloMarca[1]);
            veiculo.cor = cores[random.nextInt(cores.length)];

            adicionarVeiculo(veiculo);
            adicionarVeiculoVaga(placa);
        }
    }

    private String gerarPlacaMercosul(Random random) {
        StringBuilder sb = new StringBuilder();

        // Gera uma placa no formato LLLNLNN, L = Letra, N = Número

        // 3 letras
        for (int i = 0; i < 3; i++) {
            sb.append((char) ('A' + random.nextInt(26)));
        }

        // 1 número
        sb.append(random.nextInt(10));

        // 1 letra
        sb.append((char) ('A' + random.nextInt(26)));

        // 2 números
        sb.append(random.nextInt(10));
        sb.append(random.nextInt(10));

        return sb.toString();
    }

    public boolean adicionarVeiculo(Veiculo veiculo) {
        for (int j = 0; j < sistema.numVeiculos; j++) {
            if (sistema.veiculos[j] != null && sistema.veiculos[j].placa == veiculo.placa) {
                return false;
            }
        }
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

    public Veiculo retornarVeiculo(String placa) {
        Veiculo veicular = null;
        for (int i = 0; i < sistema.numVeiculos; i++) {
            if (sistema.veiculos[i] != null && sistema.veiculos[i].placa.equals(placa)) {
                veicular = sistema.veiculos[i];
            }
        }
        return veicular;
    }

    public boolean adicionarVeiculoVaga(String placa) { // Verificar se tem vagas para esse tipo de veiculo
                                                        // primeiramente
                                                        // Verificar qual a vaga mais próxima para preencher do tipo de
                                                        // veiculo
        Veiculo veicular = new Veiculo();
        veicular = retornarVeiculo(placa);
        for (int i = 0; i < sistema.numVagas; i++) {
            if (veicular.tipo == sistema.vagas[i].tipo && sistema.vagas[i].estado.equals(Estado.LIVRE)
                    && !verificaVeiculoEstacionado(placa)) {
                sistema.vagas[i].veiculo = veicular;
                sistema.vagas[i].estado = Estado.OCUPADO;
                sistema.numRegistros++;
                sistema.adicionaVeiculoRegistro(veicular);
                return true;
            }
        }

        System.out.println("Não tem vagas para esse veículo");
        return false;
    }

    public boolean verificaVeiculoEstacionado(String placa) {
        for (int i = 0; i < sistema.numVagas; i++) {
            if (sistema.vagas[i].veiculo != null && placa.equals(sistema.vagas[i].veiculo.placa)) {
                return true;
            }
        }
        return false;
    }
}
