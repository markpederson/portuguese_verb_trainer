package com;

import java.util.HashMap;
import java.util.Map;

public class Verb {
    String infinitive;
    String stem;
    String ending;

    String[] pres = new String[4];
    String[] imperf = new String[4];
    String[] pret = new String[4];
    String[] fut = new String[4];
    String[] fut_past;

    String[] subj_pres = new String[4];
    String[] subj_imp = new String[4];
    String[] subj_fut = new String[4];

    String[] imperative;
    String gerund;
    String participle;

    public Verb(String infin) {
        infinitive = infin;
        stem = infin.substring(0, infin.length()-2);
        ending = infin.substring(infin.length()-2);

        if (ending.equals("ar")) {
            String[] pres_endings = {"o", "a", "amos", "am"};
            String[] imperf_endings = {"ava", "ava", "ávamos", "avam"};
            String[] pret_endings = {"ei", "ou", "amos", "aram"};
            String[] fut_endings = {"ei", "á", "emos", "ão"};
            String[] subj_pres_endings = {"e", "e", "emos", "em"};
            String[] subj_imp_endings = {"asse", "asse", "ássemos", "assem"};
            String[] subj_fut_endings = {"ar", "ar", "armos", "arem"};

            for (int i = 0; i < 4; i++) {
                pres[i] = stem + pres_endings[i];
                imperf[i] = stem + imperf_endings[i];
                pret[i] = stem + pret_endings[i];
                fut[i] = infinitive + fut_endings[i];
                subj_pres[i] = stem + subj_pres_endings[i];
                subj_imp[i] = stem + subj_imp_endings[i];
                subj_fut[i] = stem + subj_fut_endings[i];
            }
        }
    }

    public void print_tense(String[] forms, String name) {
        Map<Integer, String> person_ref = new HashMap<Integer, String>();
        person_ref.put(0, "1 sg."); person_ref.put(1, "3 sg."); person_ref.put(2, "1 pl."); person_ref.put(3, "3 pl.");
        int person_index = 0;
        for (int i = 0; i < 4; i++) {
            System.out.println(name + " " + person_ref.get(person_index) + ": " + forms[person_index]);
            person_index++;
        }
        System.out.println("\n");
    }
    
    public void print_all() {
        System.out.println("infinitive: " + infinitive);
        System.out.println("stem: " + stem);
        System.out.println("ending: " + ending);
        System.out.println("\n");
        print_tense(pres, "present");
        print_tense(imperf, "imperfect");
        print_tense(pret, "preterite");
        print_tense(fut, "future");
        print_tense(subj_pres, "present subj.");
        print_tense(subj_imp, "imperfect subj.");
        print_tense(subj_fut, "future subj.");
    }
}
