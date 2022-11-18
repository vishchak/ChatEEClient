package ua.kiev.prog.registration;

import ua.kiev.prog.Utils;
import ua.kiev.prog.exceptions.NoLoginException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Register {
    public static String register(Scanner sc) throws IOException, NoLoginException {
        System.out.println("Create new login:");
        String login = sc.nextLine();
        System.out.println("Create password: ");
        String password = sc.nextLine();
        URL obj = new URL(Utils.getURL() + "/register" + "?login=" + login + "&password=" + password);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        if (conn.getResponseCode() == 201) {
            System.out.println("Registration successful");
            return login;
        }
        if (conn.getResponseCode() == 406) {
            System.out.println("User with login: " + login + " exists. Try again");
            return Register.register(sc);
        }
        throw new NoLoginException("Error occurred");
    }
}
