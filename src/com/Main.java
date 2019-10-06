package com;

import javax.naming.InterruptedNamingException;
import java.awt.desktop.SystemEventListener;
import java.io.File;
import java.util.*;

public class Main {

    private static String rand_form(Verb[] verb_list) {
        Random index_gen = new Random();
        int rand_verb_index = index_gen.nextInt(verb_list.length);

        Verb base_verb = verb_list[rand_verb_index];

        Map<Integer, String[]> tense_ref = new HashMap<>();
        tense_ref.put(0, base_verb.pres);
        tense_ref.put(1, base_verb.pret);
        tense_ref.put(2, base_verb.imperf);
        tense_ref.put(3, base_verb.pluperf);
        tense_ref.put(4, base_verb.fut);
        tense_ref.put(5, base_verb.cond);

        Map<Integer, String> tense_name = Map.of(0, "present", 1, "preterite", 2, "imperfect", 3, "pluperfect",
                                                 4, "future", 5, "conditional");
        Map<Integer, String> person_name = Map.of(0, "1 sg.", 1, "3 sg.", 2, "1 pl.", 3, "3 pl.");

        int rand_tense_index = index_gen.nextInt(5);
        int rand_person_index = index_gen.nextInt(4);
        String[] tense_forms = tense_ref.get(rand_tense_index);
        return tense_forms[rand_person_index] + "-" + tense_name.get(rand_tense_index) + "-"
                + person_name.get(rand_person_index) + "-" + base_verb.infinitive;

    }

    private static void run_verb_test(Verb[] verb_list) {
        String[] rand_verb = rand_form(verb_list).split("-");
        String right_answer = rand_verb[0];
        String tense = rand_verb[1];
        String person = rand_verb[2];
        String ref_name = rand_verb[3];
        System.out.println(ref_name + ": " + person + " " + tense);
        while (true) {
            Scanner resp = new Scanner(System.in);
            String user_answer = resp.nextLine();
            if (user_answer.equals(right_answer)) {
                System.out.println("Correct!");
                break;
            } else {
                System.out.println("Incorrect. Try again.");
            }
        }
    }
    public static void main(String[] args) {
        List<String> reg_verbs_temp = new ArrayList<>();
        File regular_verbs = new File("C:\\Users\\trump\\projects\\portuguese_verb_trainer\\regular_verbs.txt");
        try {
            Scanner in_file = new Scanner(regular_verbs);
            while (in_file.hasNext()) {
                reg_verbs_temp.add(in_file.next());
            }
        } catch(java.io.IOException e) {
            System.out.println("error: " + e);
        }

        String[] raw_verb_list = reg_verbs_temp.toArray(new String[0]);

        Verb[] verb_list= new Verb[raw_verb_list.length];
        for (int i = 0; i < raw_verb_list.length; i++) {
            verb_list[i] = new Verb(raw_verb_list[i]);
        }

        while (true) {
            System.out.println("\nType Enter to get a new verb or q() to quit.");
            Scanner sc = new Scanner(System.in);
            String user_input = sc.nextLine();
            if (user_input.equals("q()")) {
                break;
            } else {
                run_verb_test(verb_list);
            }
        }
    }
}