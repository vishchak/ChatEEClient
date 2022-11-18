package ua.kiev.prog.authorization;

import ua.kiev.prog.Utils;
import ua.kiev.prog.exceptions.NoLoginException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Login {
    public static String login(Scanner sc) throws IOException, NoLoginException {
        sc.nextLine();
        System.out.println("Enter login:");
        String login = sc.nextLine();
        System.out.println("Enter password: ");
        String password = sc.nextLine();

        URL obj = new URL(Utils.getURL() + "/login" + "?login=" + login + "&password=" + password);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        if (conn.getResponseCode() == 200) {
            System.out.println("Successfully logged in");
            return login;
        }
        if (conn.getResponseCode() == 401) {
            System.out.println("Wrong password try again");
            return Login.login(sc);
        }
        if (conn.getResponseCode() == 404) {
            System.out.println("No such user - register");
            return Register.register(sc);
        }
        throw new NoLoginException("Error occurred");
    }
}
