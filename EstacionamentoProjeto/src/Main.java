import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--------------------------------------------------------");
        System.out.println("---- SISTEMA DE CONTROLE DO PÁTIO DE ESTACIONAMENTO ----");
        System.out.println("--------------------------------------------------------");

        while (true) {
            System.out.println("Selecione a sua opção: ");
            System.out.println("1 - Sistema de Atendimento");
            System.out.println("2 - Sistema de Gestão");
            System.out.println("3 - SAIR");
            
            int escolha;

            try {
                escolha = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
                sc.nextLine(); // Consome a entrada inválida
                continue; // Pula a interação atual
            }

            switch (escolha) {
                case 1:
                    System.out.println("--------------------------------------------------------");
                    System.out.println("------- SISTEMA DE ATENDIMENTO DO ESTACIONAMENTO -------");
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Selecione a sua opção: ");
                    System.out.println("1 - Entrada de veículos");
                    System.out.println("2 - Saída de veículos");
                    break;
                case 2:
                    break;
                case 3:
                    sc.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamenteuma das opções abaixo: ");
            }
        }
    }
}
