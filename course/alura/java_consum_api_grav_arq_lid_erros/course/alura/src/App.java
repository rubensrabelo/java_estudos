import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.google.gson.Gson;

import models.Title;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("Insert a movie for search: ");
        var search = sc.nextLine();

        String url = "https://www.omdbapi.com/?t=" + search + "&apikey=802bd4ce";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        String json = response.body();

        // System.out.println(json);


        Gson gson = new Gson();

        Title title = gson.fromJson(json, Title.class);

        System.out.println(title);

        sc.close();
    }
}
