package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerTest {
    public static void main(String[] args) {
        try (ServerSocket srv = new ServerSocket(80)){
            Socket socket = srv.accept();
            if(socket != null){
                System.out.println("Client + " + socket.getInetAddress());
            }
            DataInputStream in
                    = new DataInputStream(socket.getInputStream());
            DataOutputStream out
                    = new DataOutputStream(socket.getOutputStream());

            Scanner console = new Scanner(System.in);

            // Чтение с Клиента
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            String messageFromClient = in.readUTF();
                            System.out.println("Клиент: " + messageFromClient);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            thread1.start();

            // Отправка Клиенту
            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String msg = console.nextLine();
                        try {
                            out.writeUTF(msg);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            thread2.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
