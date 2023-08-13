package org.example.jsonplaceholder;

import com.google.gson.Gson;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JsonPlaceholderClient {
    private static final String URL = "https://jsonplaceholder.typicode.com/users";

    public static void createNewUser() throws IOException, InterruptedException {
        Gson gson = new Gson();
        User newUser = gson.fromJson(new FileReader("user.json"), User.class);
        String newUserJson = gson.toJson(newUser);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(newUserJson))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Create New User Response: " + response.body());
    }
    public static void updateExistingUser(int userId) throws IOException {
        Connection.Response response = Jsoup.connect(URL + "/" + userId)
                .method(Connection.Method.PUT)
                .header("Content-Type", "application/json")
                .ignoreContentType(true)
                .execute();

        System.out.println("Update Existing User Response: " + response.body());
    }

    public static void deleteUser(int userId) throws IOException {
        Connection.Response response = Jsoup.connect(URL + "/" + userId)
                .method(Connection.Method.DELETE)
                .header("Content-Type", "application/json")
                .ignoreContentType(true)
                .execute();

        System.out.println("Delete User Response Status Code: " + response.statusCode());
    }

    public static void getAllUsers() throws IOException {
        Document document = Jsoup.connect(URL)
                .header("Accept", "application/json")
                .ignoreContentType(true)
                .get();

        System.out.println("Response: " + document.body().text());
    }

    public static void getUserById(int userId) throws IOException {
        Document document = Jsoup.connect(URL + "/" + userId)
                .header("Accept", "application/json")
                .ignoreContentType(true)
                .get();
        System.out.println("User by ID: " + document.body().text());
    }

    public static void getUserByUsername(String username) throws IOException {
        Document document = Jsoup.connect(URL + "?username=" + username)
                .header("Accept", "application/json")
                .ignoreContentType(true)
                .get();
        System.out.println("User by Username: " + document.body().text());
    }

    public static String getResponseBody(String url) {
        try {
            Connection.Response response = Jsoup.connect(url)
                    .header("Accept", "application/json")
                    .ignoreContentType(true)
                    .execute();
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
