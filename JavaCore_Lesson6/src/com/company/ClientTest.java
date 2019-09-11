package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientTest {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 80);
        System.out.println("Connected");
        DataInputStream in
                = new DataInputStream(socket.getInputStream());
        DataOutputStream out
                = new DataOutputStream(socket.getOutputStream());
        Scanner console = new Scanner(System.in);

        // Отправка на сервер
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (!socket.isClosed()) {
                        String msg = console.nextLine();
                        try {
                            out.writeUTF(msg);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else {
                        break;
                    }
                }
            }
        });
        thread1.start();

        // Чтение с сервера
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (!socket.isClosed()) {
                        if(socket.isClosed()) break;
                        String msgFromServer = null;
                        try {
                            msgFromServer = in.readUTF();
                            System.out.println("Сервер: " + msgFromServer);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else {
                        break;
                    }
                }
            }
        });
        thread2.start();
    }
}
