package com;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        String[] raw_verb_list = {"amar", "beber", "cantar", "chegar", "comprar", "estudar", "falar", "ficar",
                                  "comer", "conhecer", "correr", "vender", "viver",
                                  "dormir", "partir"};

        Verb[] verb_list= new Verb[raw_verb_list.length];
        for (int i = 0; i < raw_verb_list.length; i++) {
            verb_list[i] = new Verb(raw_verb_list[i]);
        }

        for (Verb verb : verb_list) {
            verb.print_all();
        }
    }
}