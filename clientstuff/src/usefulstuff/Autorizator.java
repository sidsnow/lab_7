package usefulstuff;

import java.io.Console;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Autorizator {
    private static Autorizator instance;

    private Autorizator() {
    }

    public static Autorizator getInstance() {
        if (instance == null) {
            instance = new Autorizator();
        }
        return instance;
    }

    public boolean toAutorize(ObjectInputStream reciever, ObjectOutputStream sender, Scanner console) throws Exception {
        boolean isAutorized = false;

        while (!isAutorized) {
            try {
                System.out.println("Введите: 1) авторизация; 2) регистрация.");
                int choice = Integer.valueOf(console.nextLine());
                switch (choice) {
                    case 1: {
                        sender.writeObject(1); sender.flush();
                        List<String> autorizationInfo = new ArrayList<>();
                        System.out.println("Введите логин.");
                        autorizationInfo.add(console.nextLine());
                        boolean pswchk = false;
                        while (!pswchk) {
                        System.out.println("Введите пароль.");
                        /*    Console consoleForPassword = System.console();
                            char[] pswd = consoleForPassword.readPassword();
                            String password = new String(pswd);
                        autorizationInfo.add(password);*/
                            autorizationInfo.add(console.nextLine());
                        sender.writeObject(autorizationInfo);
                        sender.flush();
                        String recievedInfo = (String) reciever.readObject();
                        if (recievedInfo.equals("success")) {
                            isAutorized = true;
                            User.getInstance().setUserId(Integer.parseInt(reciever.readObject().toString()));
                            pswchk = true;
                        } else {
                            if (recievedInfo.equals("Нет такого пользователя. Зарегистрируйтесь. ")) {
                                System.out.println(recievedInfo);
                                pswchk = true;
                            }
                            else
                            System.out.println(recievedInfo);
                        } }
                        break; }
                    case 2: {
                        sender.writeObject(2); sender.flush();
                        List<String> autorizationInfo = new ArrayList<>();
                        System.out.println("Введите логин.");
                        autorizationInfo.add(console.nextLine());
                        System.out.println("Введите пароль.");
                        autorizationInfo.add(console.nextLine());
                        sender.writeObject(autorizationInfo);
                        sender.flush();
                        System.out.println(reciever.readObject().toString());
                        isAutorized = true;
                        break;
                } }
            } catch (IllegalArgumentException e) {
                continue;
            }
        }
        return isAutorized;
    }

}