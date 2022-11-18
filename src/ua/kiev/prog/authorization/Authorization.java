package ua.kiev.prog.authorization;

import ua.kiev.prog.exceptions.NoLoginException;

import java.io.IOException;
import java.util.Scanner;

public class Authorization {
    public static String authorization(Scanner sc) throws NoLoginException, IOException {
        System.out.println("Press 1 to login \n" +
                "Press 2 to register");
        int whatToDO = sc.nextInt();
        switch (whatToDO) {
            case 1:
                return Login.login(sc);
            case 2:
                return Register.register(sc);
            default:
                break;
        }
        throw new NoLoginException("Error occurred");
    }
}
