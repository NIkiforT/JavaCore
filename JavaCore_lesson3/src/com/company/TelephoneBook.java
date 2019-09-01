package com.company;

import java.util.*;

public class TelephoneBook{

        private Map<String, List<String>> phoneBook;

        public TelephoneBook(){
            this.phoneBook = new HashMap<>();
        }
        public void add(String name, String phoneNumber){

            List<String> phones;

            if(phoneBook.containsKey(name)){
                phones = phoneBook.get(name);
            }else{
                phones = new ArrayList<>();
                phoneBook.put(name,phones);
            }
            phones.add(phoneNumber);
        }

        public List<String> get(String name){
            return phoneBook.get(name);
        }
}
