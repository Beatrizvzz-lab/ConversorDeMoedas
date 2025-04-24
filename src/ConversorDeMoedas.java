import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ConversorDeMoedas {

    public static void main(String[] args) {
        // aqui estou criando um objeto Scanner para ler a entrada do usuário.
        Scanner scanner = new Scanner(System.in);
        // aqui cria um cliente HTTP para realizar as requisições.
        HttpClient client = HttpClient.newHttpClient();
        // Variável de controle para o loop principal.
        boolean continuar = true;
        // Loop principal que permite ao usuário continuar convertendo moedas até escolher sair.
        while (continuar) {
            exibirMenu();
            int opcao = scanner.nextInt();
            continuar = (opcao != 7);
            // Se o usuário escolheu a opção 7 (Sair), o loop é interrompido.
            if (!continuar) {
                System.out.println("Saindo do conversor. Obrigado!");
                continue; // apenas sai do loop na próxima iteração
            }

            System.out.print("Digite o valor para conversão: ");
            double valor = scanner.nextDouble();

            String[] moedas = obterMoedas(opcao);
            if (moedas == null) {
                System.out.println("Opção inválida.");
                continue;
            }
            //aqui estamos convertendo as moedas e armazenando o resultado.
            double resultado = converterMoeda(client, moedas[0], moedas[1], valor);
            if (resultado >= 0) {
                // Exibe o valor convertido.
                System.out.printf(">> %.2f %s = %.2f %s\n", valor, moedas[0], resultado, moedas[1]);
            } else {
                // Se ocorrer um erro na conversão, exibe uma mensagem de erro.
                System.out.println("Erro ao obter os dados da API.");
            }
        }

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n=== Conversor de Moedas ===");
        System.out.println("1. USD -> BRL");
        System.out.println("2. EUR -> BRL");
        System.out.println("3. ARS -> BRL");
        System.out.println("4. BRL -> USD");
        System.out.println("5. BRL -> CLP");
        System.out.println("6. BRL -> COP");
        System.out.println("7. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static String[] obterMoedas(int opcao) {
        return switch (opcao) {
            case 1 -> new String[]{"USD", "BRL"};
            case 2 -> new String[]{"EUR", "BRL"};
            case 3 -> new String[]{"ARS", "BRL"};
            case 4 -> new String[]{"BRL", "USD"};
            case 5 -> new String[]{"BRL", "CLP"};
            case 6 -> new String[]{"BRL", "COP"};
            default -> null;
        };
    }

    private static double converterMoeda(HttpClient client, String de, String para, double valor) {
        try {
            String apiKey = System.getenv("API_KEY_EXCHANGE");
            String url = String.format("https://v6.exchangerate-api.com/v6/%s/latest/%s", apiKey, de);
            // Cria uma requisição GET para a URL da API.
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
            // Envia a requisição e recebe a resposta como string.
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // Converte a resposta JSON em um objeto JsonObject.
            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            // Verifica se a resposta teve sucesso
            if (!json.get("result").getAsString().equals("success")) {
                System.out.println("A API retornou um erro.");
                return -1;
            }

            JsonObject taxas = json.getAsJsonObject("conversion_rates");
            double taxa = taxas.get(para).getAsDouble();

            return valor * taxa;

        } catch (Exception e) {
            System.out.println("Erro na requisição: " + e.getMessage());
            return -1;
        }
    }
}
