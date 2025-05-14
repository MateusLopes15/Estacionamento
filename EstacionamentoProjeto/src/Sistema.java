import java.util.Random;

public class Sistema {
    Vaga[] vagas = new Vaga[100];
    
    Veiculo[] veiculos = new Veiculo[5000];
    int numVeiculos;
    
    RegistroEstacionamento[] registros = new RegistroEstacionamento[10000];
    int numRegistros;

    void inicializarVagas() {
        Random r = new Random();
        for (int i = 0; i < vagas.length; i++) {
            vagas[i] = new Vaga();
            vagas[i].numero = i+1;
            vagas[i].estado = Estado.LIVRE;
            int tipo = r.nextInt(3);
            switch (tipo) {
                case 1:
                    vagas[i].tipo = Tipo.UTILITARIO;
                    break;
                case 2:
                    vagas[i].tipo = Tipo.AUTOMOVEL;
                    break;
                case 3:
                    vagas[i].tipo = Tipo.MOTOCICLETA;
                    break;
            }
        }
    }

    void imprimeVagas() {
        for (int i = 0; i < vagas.length; i++) {
            if (vagas[i].veiculo != null) {
                System.out.println("--- Vaga " + (vagas[i].numero) + " ---");
                System.out.println("Placa do veículo: " + vagas[i].veiculo.placa);
                System.out.println("Tipo: " + vagas[i].tipo);
                System.out.println("Estado: " + vagas[i].estado);
            }
        }
    }
}
