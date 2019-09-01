package com.company;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        //Первое задание.
        String[] str = new String[]{"сон", "еда", "машина", "сон", "квартира", "квартира", "еда", "работа", "работа","работа",
                "сон", "стол", "квартира"};

        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length; i++) {
            if(map.containsKey(str[i])) {
                map.put(str[i], map.get(str[i]) + 1);
            }else {
                map.put(str[i], 1);
            }

        }
        //Получаем уникальный список.
        System.out.println(map);
        System.out.println(); //Пустую строчку



        //Задание 2. Создаем справочник.
        TelephoneBook dir = new TelephoneBook();

        //Добавление новых абонентов
        dir.add("Иванов", "89998690001");
        dir.add("Петров", "89998690051");
        dir.add("Кошкин", "89998690013");
        dir.add("Иванов", "89998690021");
        dir.add("Иванов", "89998690011");


        //Выводим номера людей с фамилиеф "Иванов"
        System.out.println(dir.get("Иванов"));
    }


}
