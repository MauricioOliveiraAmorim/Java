# 🌍 Terraria Java – Jogo de Console com Login, NPCs e Biomas  

Este é um projeto em **Java** inspirado no jogo *Terraria*, desenvolvido com fins de aprendizado.  
O jogo possui sistema de **login e registro**, gerenciamento de **NPCs**, construção de **casas**, ativação de **Pylons** e mecânica básica de **biomas**.  

---

## 📌 Funcionalidades  

- 🔑 **Login e Registro de Usuários** (dados salvos em `.txt` ou `.csv`)  
- 🎮 **Menu interativo** em console  
- 🏗️ **Construção de casas** para NPCs usando materiais  
- 👥 **Sistema de NPCs** com verificação de moradia  
- 🌍 **Biomas disponíveis**: Floresta, Deserto, Caverna, Cogumelo, Neve, Jungle, Oceano e Hallow  
- ✨ **Pylons** que ativam quando os NPCs do bioma estão satisfeitos  
- 🧳 **Inventário do jogador** com coleta de materiais (madeira, gel etc.)  
- 🌀 **Sistema de teleporte** entre biomas (quando há pelo menos 2 Pylons ativos)  

---

## ⚙️ Tecnologias Utilizadas  

- **Java 17+**  
- **Swing (javax.swing)** → Tela inicial de login  
- **Paradigma OO** → Classes para Player, NPC, Bioma, Pylon etc.  
- **Coleções Java** (List, Map)  
- **Manipulação de Arquivos** (`.txt` e `.csv`)  

---

## 🚀 Como Rodar  

1. Clone o repositório:  
   ```bash
   git clone https://github.com/SEU_USUARIO/NOME_DO_REPOSITORIO.git
   cd NOME_DO_REPOSITORIO

2. Compile o projeto:
   ```bash
   javac -d bin src/terraria/*.java

3. Execute o jogo:
   ```bash
   java -cp bin terraria.Jogo

---

## 📌 Melhorias Futuras

- **Adicionar sistema de combate**
- **Implementar felicidade dos NPCs**
- **Interface gráfica completa para o jogo (JavaFX ou outra lib)**
- **Salvar progresso do jogador**

---

## 👤 Autor

**Projeto desenvolvido por [Maurício](https://github.com/MauricioOliveiraAmorim) para estudo de Java, POO e jogos de console.**
