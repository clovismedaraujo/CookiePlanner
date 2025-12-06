# 🍪 CookiePlanner: Sistema de Gerenciamento de Vendas e Estoque

## 📝 Descrição do Projeto

O **CookiePlanner** é um sistema desktop simples e intuitivo desenvolvido em Java com JavaFX, projetado para auxiliar no gerenciamento de um pequeno negócio de cookies.

O objetivo principal é fornecer ferramentas básicas para **catalogar produtos**, **controlar o inventário** (estoque) e **registrar as vendas**, fornecendo feedback imediato sobre a receita total.

---

## ✨ Funcionalidades Principais

* **Catálogo de Produtos:**
    * Registro de novos sabores de cookies com seus respectivos preços unitários.
* **Controle de Estoque:**
    * Registro de entrada de novos lotes de cookies no estoque.
    * Visão geral atualizada do estoque.
* **Registro de Vendas:**
    * Realização de vendas com validação automática: impede a venda se o estoque for insuficiente.
    * Atualização em tempo real da **Receita Total** acumulada.
* **Interface Gráfica (GUI):**
    * Desenvolvida utilizando JavaFX e FXML para uma experiência de usuário limpa.
    * Uso de **Alerts** para mensagens de sucesso e feedback de erro, substituindo a saída no console.

---

## 💻 Tecnologias Utilizadas

* **Linguagem:** Java (JDK 11+ recomendado)
* **Framework GUI:** JavaFX
* **Padrão de Design:** MVC (Model-View-Controller)
* **Dependências:**
    * **Jackson Datatype JSR310:** Necessário para serializar e desserializar tipos de data e hora modernos do Java 8 (LocalDateTime).

