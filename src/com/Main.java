package com;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static String rand_form(Verb[] verb_list) {
        int rand_verb_index = (int) (Math.random() * (verb_list.length - 1));
        int rand_tense_index = (int) (Math.random() * 3);
        int rand_person_index = (int) (Math.random() * 3);

        Verb base_verb = verb_list[rand_verb_index];

        Map<Integer, String[]> tense_ref = new HashMap<Integer, String[]>();
        tense_ref.put(0, base_verb.pres);
        tense_ref.put(1, base_verb.imperf);
        tense_ref.put(2, base_verb.pret);
        tense_ref.put(3, base_verb.fut);

        String[] tense_forms = tense_ref.get(rand_tense_index);
        return tense_forms[rand_person_index];

    }
    public static void main(String[] args) {
        String[] raw_verb_list = {"amar", "beber", "cantar", "chegar", "comprar", "estudar", "falar", "ficar"};
                                  // "comer", "conhecer", "correr", "vender", "viver",
                                  // "dormir", "partir"};

        Verb[] verb_list= new Verb[raw_verb_list.length];
        for (int i = 0; i < raw_verb_list.length; i++) {
            verb_list[i] = new Verb(raw_verb_list[i]);
        }

        while (true) {
            Scanner sc = new Scanner(System.in);
            String user_input = sc.nextLine();
            if (user_input.equals("q()")) {
                break;
            } else {
                System.out.println(rand_form(verb_list));
            }
        }
    }
}