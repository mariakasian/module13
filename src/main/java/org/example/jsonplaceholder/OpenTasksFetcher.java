package org.example.jsonplaceholder;

import com.google.gson.Gson;
import static org.example.jsonplaceholder.JsonPlaceholderClient.getResponseBody;

public class OpenTasksFetcher {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    public static void fetchAndPrintOpenTasks(int userId) {
        Gson gson = new Gson();

        // Отримуємо відкриті задачі користувача
        String userTasksUrl = BASE_URL + "/users/" + userId + "/todos";
        Task[] userTasks = gson.fromJson(getResponseBody(userTasksUrl), Task[].class);

        System.out.println("Open tasks for user " + userId + ":");
        for (Task task : userTasks) {
            if (!task.isCompleted()) {
                System.out.println("Task #" + task.getId() + ": " + task.getTitle());
            }
        }
    }
}

