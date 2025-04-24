import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exception.YearConversionErrorException;
import model.Title;
import model.TitleOmdb;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner input = new Scanner(System.in);
        String search = "";
        List<Title> titles = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while (!search.equalsIgnoreCase("quit")) {

            System.out.print("Digite um filme para busca: ");
            search = input.nextLine();

            if(search.equalsIgnoreCase("quit")) break;

            String apiKey = "";
            String address = "https://www.omdbapi.com/?t=" + search.replace(" ", "+") + "&apikey=" + apiKey;

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(address))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();
                System.out.println(json);

                TitleOmdb titleOmdb = gson.fromJson(json, TitleOmdb.class);

                Title title = new Title(titleOmdb);
                System.out.println(title);
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (YearConversionErrorException e) {
                System.out.println(e.getMessage());
            } finally {
                input.close();
            }
        }

        FileWriter fw = new FileWriter("movies.json");
        fw.write(gson.toJson(titles));
        fw.close();

        System.out.println("Fim do programa");
    }
}