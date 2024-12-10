package com.example;

import java.util.Scanner;

public class Menu {

    private final Consulta consulta = new Consulta();
    private final CalculadoraDeMoeda calculadora = new CalculadoraDeMoeda();

    public void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n==== Conversor de Moedas ====");
            exibirOpcoes();
            System.out.print("Escolha uma opção: ");
            
            int escolha = scanner.nextInt();
            if (escolha == 0) {
                System.out.println("Saindo...");
                break;
            }

            String moedaDestino = obterMoedaDestino(escolha);
            if (moedaDestino == null) {
                System.out.println("Opção inválida!");
                continue;
            }

            System.out.print("Digite a moeda de origem (ex: BRL): ");
            String moedaOrigem = scanner.next();

            // Busca taxas de câmbio
            Moeda moeda = consulta.obterTaxas(moedaOrigem);
            Double taxa = moeda.getConversionRates().get(moedaDestino);
            if (taxa == null) {
                System.out.println("Não foi possível obter a taxa de câmbio para " + moedaDestino + ".");
                continue;
            }

            System.out.println("Taxa de câmbio para " + moedaDestino + ": " + taxa);
            System.out.print("Digite o valor que deseja converter: ");
            double valor = scanner.nextDouble();

            // Calcula conversão
            double valorConvertido = calculadora.calcularConversao(valor, taxa);
            System.out.printf("O valor convertido para %s é: %.2f%n", moedaDestino, valorConvertido);
        }
        scanner.close();
    }

    private void exibirOpcoes() {
        System.out.println("[0] - Sair");
        System.out.println("[1] - Converter valor para Dólar Americano (USD)");
        System.out.println("[2] - Converter valor para Euro (EUR)");
        System.out.println("[3] - Converter valor para Iene (JPY)");
        System.out.println("[4] - Converter valor para Dólar Canadense (CAD)");
        System.out.println("[5] - Converter valor para Peso Argentino (ARS)");
    }

    private String obterMoedaDestino(int opcao) {
        return switch (opcao) {
            case 1 -> "USD";
            case 2 -> "EUR";
            case 3 -> "JPY";
            case 4 -> "CAD";
            case 5 -> "ARS";
            default -> null;
        };
    }
}
