package com.markpederson.portugueseverbtrainer;

import java.util.HashMap;
import java.util.Map;

public class Verb {
    String infinitive;
    String stem;
    String ending;

    String[] pres = new String[4];
    String[] pret = new String[4];
    String[] imperf = new String[4];
    String[] pluperf = new String[4];
    String[] fut = new String[4];
    String[] cond = new String[4];

    String[] subj_pres = new String[4];
    //    String[] subj_pret = new String[4];
    String[] subj_imp = new String[4];
    //    String[] subj_plu = new String[4];
    String[] subj_fut = new String[4];

    String[] imperative;
    String gerund;
    String participle;

    public Verb(String infin) {
        infinitive = infin;
        stem = infin.substring(0, infin.length()-2);
        ending = infin.substring(infin.length()-2);

        String[] pres_endings = {"o", "a", "amos", "am"};
        String[] pret_endings = {"ei", "ou", "amos", "aram"};
        String[] imperf_endings = {"ava", "ava", "ávamos", "avam"};
        String[] pluperf_endings = {"ara", "ara", "áramos", "aram"};
        String[] fut_endings = {"ei", "á", "emos", "ão"};
        String[] cond_endings = {"ia", "ia", "íamos", "iam"};
        String[] subj_pres_endings = {"e", "e", "emos", "em"};
//            String[] subj_pret_endings = {};
        String[] subj_imp_endings = {"asse", "asse", "ássemos", "assem"};
//            String[] subg_plu_endings = {};
        String[] subj_fut_endings = {"es", "mos", "des", "em"};

        if (ending.equals("er")) {
            pres_endings = new String[]{"o", "e", "emos", "em"};
            pret_endings = new String[]{"i", "eu", "emos", "eram"};
            imperf_endings = new String[]{"ia", "ia", "íamos", "iam"};
            pluperf_endings = new String[]{"era", "era", "êramos", "eram"};
            subj_pres_endings = new String[]{"a", "a", "amos", "am"};
//            subj_pret_endings = new String[]{};
            subj_imp_endings = new String[]{"esse", "esse", "êssemos", "essem"};
//            subg_plu_endings = new String[]{};
        } else if (ending.equals("ir")) {
            pres_endings = new String[]{"o", "e", "imos", "em"};
            pret_endings = new String[]{"i", "iu", "imos", "iram"};
            imperf_endings = new String[]{"ia", "ia", "íamos", "iam"};
            pluperf_endings = new String[]{"ira", "ira", "íramos", "iram"};
            subj_pres_endings = new String[]{"a", "a", "amos", "am"};
//            subj_pret_endings = new String[]{};
            subj_imp_endings = new String[]{"isse", "isse", "íssemos", "issem"};
//            subg_plu_endings = new String[]{};
        }

        for (int i = 0; i < 4; i++) {
            pres[i] = stem + pres_endings[i];
            pret[i] = stem + pret_endings[i];
            imperf[i] = stem + imperf_endings[i];
            pluperf[i] = stem + pluperf_endings[i];
            fut[i] = infinitive + fut_endings[i];
            cond[i] = infinitive + cond_endings[i];
            subj_pres[i] = stem + subj_pres_endings[i];
            subj_imp[i] = stem + subj_imp_endings[i];
            subj_fut[i] = infinitive + subj_fut_endings[i];
        }
    }

    public void print_tense(String[] forms, String name) {
        Map<Integer, String> person_ref = new HashMap<>();
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
