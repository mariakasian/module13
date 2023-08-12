package org.example.jsonplaceholder;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;

import static org.example.jsonplaceholder.JsonPlaceholderClient.getResponseBody;

public class CommentFetcher {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    public static void fetchAndSaveComments(int userId) {
        Gson gson = new Gson();

        // Отримуємо інформацію про останній пост користувача
        String userPostsUrl = BASE_URL + "/users/" + userId + "/posts";
        Post[] userPosts = gson.fromJson(getResponseBody(userPostsUrl), Post[].class);

        if (userPosts.length > 0) {
            Post lastPost = userPosts[userPosts.length - 1];
            int postId = lastPost.getId();

            // Отримуємо коментарі до останнього поста
            String postCommentsUrl = BASE_URL + "/posts/" + postId + "/comments";
            String commentsJson = getResponseBody(postCommentsUrl);

            // Записуємо коментарі у файл
            String fileName = "user-" + userId + "-post-" + postId + "-comments.json";
            try (FileWriter writer = new FileWriter(fileName)) {
                writer.write(commentsJson);
                System.out.println("Comments saved to " + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No posts found for user " + userId);
        }
    }
}


