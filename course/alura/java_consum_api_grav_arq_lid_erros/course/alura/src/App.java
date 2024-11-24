import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.TitleOmdb;
import models.Title;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("Insert a movie for search: ");
        var search = sc.nextLine();

        String url = "";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        String json = response.body();

        Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .create();
        
        TitleOmdb titleOmdb = gson.fromJson(json, TitleOmdb.class);

        System.out.println(titleOmdb);

        System.out.println();

        Title title = new Title(titleOmdb);

        System.out.println(title);

        sc.close();
    }
}
