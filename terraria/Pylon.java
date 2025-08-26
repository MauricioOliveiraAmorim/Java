package terraria;

// Classe única com comportamento polimórfico baseado no tipo
class Pylon {
    private String bioma; // Nome do bioma
    private boolean ativo; // Indica se o Pylon está ativo
    private TipoPylon tipo; // Enum para representar o tipo de Pylon

    public Pylon(String bioma, TipoPylon tipo) {
        this.bioma = bioma;
        this.tipo = tipo;
        this.ativo = false; // Inicialmente inativo
    }

    // Ativa o Pylon com comportamento dinâmico
    public void ativar() {
        this.ativo = true;
        System.out.println("Pylon do bioma " + bioma + " ativado!");
        System.out.println(tipo.mensagemAtivacao()); // Mensagem personalizada pelo tipo
    }

    // Desativa o Pylon
    public void desativar() {
        this.ativo = false;
        System.out.println("Pylon do bioma " + bioma + " desativado!");
    }

    // Verifica se o Pylon está ativo
    public boolean isAtivo() {
        return ativo;
    }

    @Override
    public String toString() {
        return "Pylon [" + tipo + "] - " + (ativo ? "Ativo" : "Inativo");
    }
}

// Enum que define os tipos de Pylon e seus comportamentos personalizados
 enum TipoPylon {
    CAVERNA("Caverna") {
        @Override
        public String mensagemAtivacao() {
            return "Luzes misteriosas brilham no Pylon da Caverna!";
        }
    },
    FLORESTA("Floresta") {
        @Override
        public String mensagemAtivacao() {
            return "O Pylon da Floresta atrai pássaros ao redor!";
        }
    },
    DESERTO("Deserto") {
        @Override
        public String mensagemAtivacao() {
            return "O calor intenso ativa o Pylon do Deserto!";
        }
    },
    COGUMELO("Cogumelo") {
        @Override
        public String mensagemAtivacao() {
            return "O Pylon de Cogumelo brilha com um tom bioluminescente!";
        }
    },
    NEVE("Neve") {
        @Override
        public String mensagemAtivacao() {
            return "Cristais de gelo formam reflexos ao ativar o Pylon de Neve!";
        }
    },
    JUNGLE("Jungle") {
        @Override
        public String mensagemAtivacao() {
            return "A selva ecoa os sons da natureza ao ativar o Pylon!";
        }
    },
    OCEANO("Oceano") {
        @Override
        public String mensagemAtivacao() {
            return "Ondas suaves rodeiam o Pylon do Oceano ao ser ativado!";
        }
    },
    HALLOW("Hallow") {
        @Override
        public String mensagemAtivacao() {
            return "Luzes mágicas e encantadoras envolvem o Pylon de Hallow!";
        }
    };

    private final String nome;

    TipoPylon(String nome) {
        this.nome = nome;
    }


    @Override
    public String toString() {
        return nome;
    }

    // Método abstrato para mensagens específicas de ativação
    public abstract String mensagemAtivacao();
}
