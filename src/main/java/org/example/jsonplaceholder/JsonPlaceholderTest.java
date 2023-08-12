package org.example.jsonplaceholder;

import java.io.IOException;

public class JsonPlaceholderTest {
    public static void main(String[] args) throws IOException {
        OpenTasksFetcher.fetchAndPrintOpenTasks(1);
        CommentFetcher.fetchAndSaveComments(2);
        JsonPlaceholderClient.getAllUsers();
        JsonPlaceholderClient.getUserById(1);
        JsonPlaceholderClient.getUserByUsername("Antonette");
        JsonPlaceholderClient.updateExistingUser(1);
        JsonPlaceholderClient.deleteUser(10);
        JsonPlaceholderClient.createNewUser();
    }
}
