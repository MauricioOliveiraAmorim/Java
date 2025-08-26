package terraria;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.List;


public class Jogo {
    private static final int USAR_CSV = 0; // 0 para TXT, 1 para CSV
    private static final String TXT_FILE = "usuarios.txt";
    private static final String CSV_FILE = "dados.csv";
    private static Map<String, String> userDatabase = new HashMap<>();

    public static void main(String[] args) {
        criarArquivoInicial();
        carregarDados();

        JFrame frame = new JFrame("Tela de Login");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Nome:");
        JTextField nameField = new JTextField();
        JLabel matriculaLabel = new JLabel("Matrícula:");
        JTextField matriculaField = new JTextField();
        JButton loginButton = new JButton("Entrar");
        JButton registerButton = new JButton("Registrar");

        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(matriculaLabel);
        frame.add(matriculaField);
        frame.add(loginButton);
        frame.add(registerButton);

        loginButton.addActionListener(e -> {
            String nome = nameField.getText().trim();
            String matricula = matriculaField.getText().trim();

            if (userDatabase.containsKey(nome) && userDatabase.get(nome).equals(matricula)) {
                JOptionPane.showMessageDialog(frame, "Login bem-sucedido!");
                frame.dispose(); // Fecha a janela de login
                iniciarJogo(nome); // Avança para o jogo
            } else {
                JOptionPane.showMessageDialog(frame, "Nome ou matrícula incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        registerButton.addActionListener(e -> {
            String nome = nameField.getText().trim();
            String matricula = matriculaField.getText().trim();

            if (!nome.isEmpty() && !matricula.isEmpty() && !userDatabase.containsKey(nome)) {
                userDatabase.put(nome, matricula);
                salvarDados();
                JOptionPane.showMessageDialog(frame, "Usuário registrado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(frame, "Preencha todos os campos ou o nome já existe!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        frame.setVisible(true);
    }

    public static void iniciarJogo(String nome) {
        Scanner scanner = new Scanner(System.in);

        Player jogador = new Player(nome);
        InterfaceNPC interfaceNPC = new MatrizNPC();
        List<Bioma> biomas = List.of(
                new Floresta(),
                new Deserto(),
                new Caverna(),
                new Cogumelo(),
                new Neve(),
                new Jungle(),
                new Oceano(),
                new Hallow()
        );

        int opcao;
        do {
            System.out.println("\nMenu Principal:");
            System.out.println("1 - Pegar Material");
            System.out.println("2 - Construir Casa");
            System.out.println("3 - Mostrar Inventário");
            System.out.println("4 - Teleporte");
            System.out.println("5 - Mostrar Tabela de NPCs");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            try {
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1: // Pegar material
                        pegarMaterial(jogador, scanner);
                        break;

                    case 2: // Construir casa
                        construirCasa(jogador, biomas, scanner);
                        break;

                    case 3: // Mostrar inventário
                        jogador.mostrarInventario();
                        break;

                    case 4: // Teleporte
                        teleportar(biomas, scanner);
                        break;

                    case 5: // Mostrar tabela de NPCs
                        System.out.println("\nTabela de NPCs:");
                        interfaceNPC.exibirBiomas(biomas);
                        break;

                    case 0:
                        System.out.println("Saindo do jogo. Até a próxima, " + nome + "!");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida!");
                scanner.nextLine(); // Limpa entrada
                opcao = -1;
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void pegarMaterial(Player jogador, Scanner scanner) {
        while (true) {
            System.out.println("\nPegar Material:");
            System.out.println("1 - Gel");
            System.out.println("2 - Madeira");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");
            int material = scanner.nextInt();

            if (material == 0) break;
            if (material == 1) jogador.pegarGel();
            else if (material == 2) jogador.pegarMadeira();
            else System.out.println("Opção inválida!");
        }
    }

    public static void construirCasa(Player jogador, List<Bioma> biomas, Scanner scanner) {
        // Verifica se o jogador tem materiais para construir
        if (!jogador.temMateriais()) {
            System.out.println("Materiais insuficientes para construir uma casa!");
            return;
        }

        // Selecionar o bioma
        System.out.println("\nSelecione o bioma onde deseja construir a casa:");
        for (int i = 0; i < biomas.size(); i++) {
            System.out.printf("%d - %s%n", i + 1, biomas.get(i).getNome());
        }

        System.out.print("Número do bioma: ");
        int biomaEscolha = scanner.nextInt();

        if (biomaEscolha < 1 || biomaEscolha > biomas.size()) {
            System.out.println("Bioma inválido!");
            return;
        }

        Bioma biomaSelecionado = biomas.get(biomaEscolha - 1);

        // Filtrar NPCs disponíveis (sem casa) no bioma
        List<NPC> npcsDisponiveis = biomaSelecionado.getNPCs().stream()
                .filter(npc -> !npc.temCasa())
                .toList();

        if (npcsDisponiveis.isEmpty()) {
            System.out.println("Nenhum NPC disponível para este bioma no momento.");
            return;
        }

        // Escolher o NPC
        System.out.println("\nEscolha um NPC para associar à nova casa:");
        for (int i = 0; i < npcsDisponiveis.size(); i++) {
            System.out.printf("%d - %s%n", i + 1, npcsDisponiveis.get(i).getNome());
        }

        System.out.print("Número do NPC: ");
        int npcEscolha = scanner.nextInt();

        if (npcEscolha < 1 || npcEscolha > npcsDisponiveis.size()) {
            System.out.println("NPC inválido!");
            return;
        }

        NPC npcEscolhido = npcsDisponiveis.get(npcEscolha - 1);

        // Construir a casa
        jogador.usarMateriais();
        jogador.setCasasConstruidas(jogador.getCasasConstruidas() + 1);
        npcEscolhido.setCasa(true);

        System.out.printf("Casa construída com sucesso no bioma %s para o NPC %s!%n", 
                          biomaSelecionado.getNome(), npcEscolhido.getNome());

        // ✅ Verificar se todos NPCs do bioma têm casa → ativa o pylon
        boolean todosComCasa = biomaSelecionado.getNPCs().stream().allMatch(NPC::temCasa);
        if (todosComCasa) {
            biomaSelecionado.getPylon().ativar();
        }
    }


    private static void teleportar(List<Bioma> biomas, Scanner scanner) {
        int pylonsAtivos = (int) biomas.stream().filter(b -> b.getPylon().isAtivo()).count();
        if (pylonsAtivos < 2) {
            System.out.println("Teleporte indisponível! É necessário ter pelo menos 2 Pylons ativos.");
            return;
        }

        while (true) {
            System.out.println("\nBiomas disponíveis para teleporte:");
            for (int i = 0; i < biomas.size(); i++) {
                if (biomas.get(i).getPylon().isAtivo()) {
                    System.out.printf("%d - %s\n", i + 1, biomas.get(i).getNome());
                }
            }
            System.out.print("Escolha o Bioma para teleportar (ou digite 0 para voltar): ");
            int escolha = scanner.nextInt();

            if (escolha == 0) break;
            if (escolha < 1 || escolha > biomas.size()) {
                System.out.println("Bioma inválido!");
            } else {
                System.out.printf("Teleportado para o bioma %s!%n", biomas.get(escolha - 1).getNome());
                break;
            }
        }
    }

    private static void criarArquivoInicial() {
        String fileName = USAR_CSV == 1 ? CSV_FILE : TXT_FILE;
        File file = new File(fileName);
        if (!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
                writer.write("anderson;1234\n");
                System.out.println("Arquivo inicial criado com sucesso: " + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void carregarDados() {
        String fileName = USAR_CSV == 1 ? CSV_FILE : TXT_FILE;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 2) {
                    userDatabase.put(partes[0].trim(), partes[1].trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Arquivo " + fileName + " não encontrado.");
        }
    }

    private static void salvarDados() {
        String fileName = USAR_CSV == 1 ? CSV_FILE : TXT_FILE;
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8))) {
            for (Map.Entry<String, String> entry : userDatabase.entrySet()) {
                writer.write(entry.getKey() + ";" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
