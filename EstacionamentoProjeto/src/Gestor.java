public class Gestor {
    Sistema sistema;
    Tarifa tarifa;

    boolean cadastrarVaga(int numVaga, Tipo tipo) {
        for (int i = 0; i < sistema.vagas.length; i++) {
            if (sistema.vagas[i] != null && sistema.vagas[i].numero == numVaga) {
                return false;
            }

            if (sistema.vagas[i] == null) {
                Vaga vaga = new Vaga();
                vaga.numero = numVaga;
                vaga.tipo = tipo;
                vaga.estado = Estado.LIVRE;
                sistema.vagas[i] = vaga;
                sistema.numVagas++;
                return true;
            }
        }
        return false;
    }

    boolean excluirVaga(int numVaga) {
        for (int i = 0; i < sistema.numVagas; i++) {
            if (sistema.vagas[i].numero == numVaga) {
                for (int j = i; j < sistema.numVagas - 1; j++) {
                    sistema.vagas[i] = sistema.vagas[i + 1];
                }
                sistema.vagas[sistema.numVagas - 1] = null;
                sistema.numVagas--;
                return true;
            }
        }
        return false;
    }

    void listarVagas() {
        for (int i = 0; i < sistema.numVagas; i++) {
            if (sistema.vagas[i] != null) {
                System.out.println("----- VAGA " + (i + 1) + " -----");
                System.out.println("Número: " + sistema.vagas[i].numero);
                System.out.println("Tipo: " + sistema.vagas[i].tipo);
                System.out.println("Estado: " + sistema.vagas[i].estado);
            }
        }
    }
}
