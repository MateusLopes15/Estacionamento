public class Sistema {
    Vaga[] vagas = new Vaga[100];
    int numVagas;

    Veiculo[] veiculos = new Veiculo[5000];
    int numVeiculos;

    RegistroEstacionamento[] registros = new RegistroEstacionamento[10000];
    int numRegistros;


public void listarVeiculos(){
    for(int i =0; i<veiculos.length; i++){
    System.out.println("Placa: " + this.veiculos[i].placa);
    System.out.println("Modelo: " + this.veiculos[i].modelo);
    System.out.println("Cor: " + this.veiculos[i].cor);
    System.out.println("Marca: " + this.veiculos[i].marca);
    System.out.println("Tipo: " + this.veiculos[i].tipo);
    System.out.println("------------------");
    }
}
}
