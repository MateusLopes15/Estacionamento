public class Main {
    public static void main(String[] args) {
        System.out.println("--------------------------------------------------------");
        System.out.println("---- SISTEMA DE CONTROLE DO PÁTIO DE ESTACIONAMENTO ----");
        System.out.println("--------------------------------------------------------");

        Veiculo veiculo = new Veiculo();
        veiculo.placa = "ABC";

        Sistema sistema = new Sistema();
        sistema.inicializarVagas();

        sistema.vagas[0].veiculo = veiculo;

        sistema.imprimeVagas();
    }
}
