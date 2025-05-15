public enum Estado {
    LIVRE,
    OCUPADO;

    public static Estado stringParaEstado(String entrada) {
        if (entrada == null) return null;
        switch (entrada.trim().toLowerCase()) {
            case "livre", "1":
                return LIVRE;
            case "ocupado", "2":
                return OCUPADO;
            default:
                return null;
        }
    }

    public static void listarOpcoes() {
        System.out.println("1 - Livre");
        System.out.println("2 - Ocupado");
    }
}
