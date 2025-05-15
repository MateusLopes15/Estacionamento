public class Tarifa {
    double[] tarifaHora = new double[] { 4, 3, 2 };

    void menu() {
        System.out.println("--------------------------------------------------------");
        System.out.println("--------------- GERENCIAMENTO DE TARIFAS ---------------");
        System.out.println("--------------------------------------------------------");

        System.out.println("UTILITÁRIO:");
        System.out.printf("Tarifa por hora: R$%.2f\n\n", tarifaHora[0]);

        System.out.println("AUTOMÓVEL:");
        System.out.printf("Tarifa por hora: R$%.2f\n\n", tarifaHora[1]);

        System.out.println("MOTOCICLETA:");
        System.out.printf("Tarifa por hora: R$%.2f\n\n", tarifaHora[2]);

        System.out.println("Qual tarifa você deseja alterar?");
        System.out.println("1. Utilitário");
        System.out.println("2. Automóvel");
        System.out.println("3. Motocicleta");
        System.out.println("4. Retornar");
    }

    void alterarTarifaUtilitario(double tarifa) {
        if (tarifa > 0) tarifaHora[0] = tarifa;
    }

    void alterarTarifaAutomovel(double tarifa) {
        if (tarifa > 0) tarifaHora[1] = tarifa;
    }

    void alterarTarifaMotocicleta(double tarifa) {
        if (tarifa > 0) tarifaHora[2] = tarifa;
    }
}

/*
 * VALORES DEFAULT:
 * UTILITÁRIO: R$12,00 fixo + R$4,00 por hora -> Posição 0 no array
 * AUTOMÓVEL: R$10,00 fixo + R$3,00 por hora -> Posição 1 no array
 * MOTOCICLETA: R$ 8,00 fixo + R$2,00 por hora -> Posição 2 no array
 */
