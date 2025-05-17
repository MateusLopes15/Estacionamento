import java.nio.channels.Pipe.SourceChannel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.Scanner;

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
            Random random = new Random();
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

                            sistema.registros[i].valorCobrado = 12 + horasDeDuracao * tarifa.tarifaHora[0];
                            break;
                        case AUTOMOVEL:
                            sistema.registros[i].valorCobrado = 10 + horasDeDuracao * tarifa.tarifaHora[1];
                            break;
                        case MOTOCICLETA:
                            sistema.registros[i].valorCobrado = 8 + horasDeDuracao * tarifa.tarifaHora[2];
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
            } // inicializar o programa
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
        for(int j=0;j<sistema.numVeiculos;j++){
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

    public boolean verificaExistenciaVeiculo(String placa) {
        for (int i = 0; i < sistema.veiculos.length; i++) {
            if (sistema.veiculos[i] != null && sistema.veiculos[i].placa.equals(placa)) {
                return true;
            }

        }
        return false;
    }

    public Veiculo retornarVeiculo(String placa) {
        Veiculo veicular = new Veiculo();
        for (int i = 0; i < sistema.veiculos.length; i++) {
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
            if (veicular.tipo == sistema.vagas[i].tipo && sistema.vagas[i].estado.equals(Estado.LIVRE)) {

                System.out.println("Véiculo da placa: " + veicular.placa + " cadastrado com sucesso");
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

    

    public boolean alterarVeiculo(String placa) {
        Scanner sc = new Scanner(System.in);
        Veiculo veicular = new Veiculo();
        Tipo tipo= null;
        System.out.println("--------------------------------------------------------");
        System.out.println("-------------SISTEMA DE EDITAR VEÍCULO------------------");  
        System.out.println("--------------------------------------------------------");
        System.out.println("FAÇA TODAS AS ALTERAÇÕES QUE QUISER E DEPOIS ESCOLHA A OPÇÃO DE SAIR");
        System.out.println("PODE FAZER A ALTERAÇÃO DE DIFERENTES PARTES DO VEÍCULO DE UMA VEZ SÓ");
        System.out.println("PRESSIONE ENTER PARA CONTINUAR");
        for (int i = 0; i < sistema.veiculos.length; i++) {
            if (sistema.veiculos[i] != null && sistema.veiculos[i].placa.equals(placa)) {
                veicular = sistema.veiculos[i];
                int adicionar = -1;
                while (adicionar != 0) {
                    sc.nextLine();
                    System.out.println("Deseja modificar:");
                    System.out.println("1 - PLACA");
                    System.out.println("2 - MODELO");
                    System.out.println("3 - MARCA");
                    System.out.println("4 - COR");
                    System.out.println("5 - TIPO");
                    System.out.println("0 - Sair");
                   
                    adicionar = sc.nextInt();
                    switch (adicionar) {
                        case 1:
                            
                            System.out.println("Digite a nova placa");
                            String stringPlaca = sc.nextLine().toUpperCase();
                            if (veiculo.validaPlacaMercosul(stringPlaca)) {
                                veicular.placa = stringPlaca;
                                sistema.veiculos[i] = veicular;
                                break;
                            } else {
                                System.out.println("Placa não válida");
                                break;
                            }
                        case 2:
                            
                            System.out.println("Digite o novo modelo");
                            String stringModelo = sc.nextLine().toUpperCase();
                            veicular.modelo = stringModelo;
                            sistema.veiculos[i] = veicular;
                            break;
                        case 3:
                            
                            System.out.println("Digite a novoa marca");
                            String stringMarca = sc.nextLine().toUpperCase();
                            veicular.modelo = stringMarca;
                            sistema.veiculos[i] = veicular;
                            break;
                        case 4:
                           
                            System.out.println("Digite a nova cor");
                            String stringCor = sc.nextLine().toUpperCase();
                            veicular.modelo = stringCor;
                            sistema.veiculos[i] = veicular;
                            break;
                        case 5:
                           
                            System.out.println("Digite o novo tipo do veículo (utilitario, automovel, motocicleta): ");
                            String tipoString = sc.nextLine().trim().toLowerCase();
                            switch (tipoString) {
                                case "utilitario":
                                    veicular.tipo = Tipo.UTILITARIO;
                                    sistema.veiculos[i] = veicular;
                                    break;
                                case "automovel":
                                    veicular.tipo = Tipo.AUTOMOVEL;
                                    sistema.veiculos[i] = veicular;
                                    break;
                                case "motocicleta":
                                    veicular.tipo = Tipo.MOTOCICLETA;
                                    sistema.veiculos[i] = veicular;
                                    break;
                                default:
                                    System.out.println("Tipo inválido. Tente novamente");
                            }
                        break;
                        
                        case 0:
                        return true;
                        
                        default:
                            System.out.println("Digite uma escolha válida");


                    }
                }
                
                sc.close();
                return true;
            }

        }sc.close();
        return false;

    }

}
