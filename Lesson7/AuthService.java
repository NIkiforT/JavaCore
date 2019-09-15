package com.company;

import java.util.ArrayList;
import java.util.List;

public interface AuthService {
    void start();
    String getNickByLoginPass(String login, String pass);
    void stop();

    String getNick(String part);

    boolean getNickOne(String nick);
}

