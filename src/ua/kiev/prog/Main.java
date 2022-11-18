package ua.kiev.prog;

import ua.kiev.prog.exceptions.NoLoginException;
import ua.kiev.prog.authorization.Authorization;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            String login = Authorization.authorization(sc);

            Thread th = new Thread(new GetThread());
            th.setDaemon(true);
            th.start();

            System.out.println("Enter your message: ");
            while (true) {
                String text = sc.nextLine();
                if (text.isEmpty()) break;

                Message m = new Message(login, text);
                int res = m.send(Utils.getURL() + "/add");

                if (res != 200) { // 200 OK
                    System.out.println("HTTP error occured: " + res);
                    return;
                }
            }
        } catch (NoLoginException e) {
            e.getMessage();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
