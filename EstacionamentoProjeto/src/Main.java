public class Main {
    public static void main(String[] args) {
        System.out.println("--------------------------------------------------------");
        System.out.println("---- SISTEMA DE CONTROLE DO PÁTIO DE ESTACIONAMENTO ----");
        System.out.println("--------------------------------------------------------");

        Veiculo veiculo = new Veiculo();
        veiculo.placa = "ABC";

        Sistema sistema = new Sistema();

        Gestor gestor = new Gestor();
        gestor.sistema = sistema;
        gestor.cadastrarVaga(0, Tipo.AUTOMOVEL);
        gestor.cadastrarVaga(1, Tipo.MOTOCICLETA);
        gestor.cadastrarVaga(2, Tipo.UTILITARIO);
        gestor.listarVagas();
        System.out.println();
        gestor.excluirVaga(1);
        gestor.listarVagas();
    }
}
