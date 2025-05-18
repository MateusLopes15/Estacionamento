public enum Tipo {
    UTILITARIO,
    AUTOMOVEL,
    MOTOCICLETA;

    public static Tipo stringParaTipo(String entrada) {
        if (entrada == null)
            return null;
        switch (entrada.trim().toLowerCase()) {
            case "utilitario", "1":
                return UTILITARIO;
            case "automovel", "2":
                return AUTOMOVEL;
            case "motocicleta", "3":
                return MOTOCICLETA;
            default:
                return null;
        }
    }

    public static void listarOpcoes() {
        System.out.println("1 - Utilitário");
        System.out.println("2 - Automóvel");
        System.out.println("3 - Motocicleta");
    }
}