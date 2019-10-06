package com.markpederson.portugueseverbtrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    String current_right_answer = "";

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

        Map<Integer, String> tense_name = new HashMap<>();
        tense_name.put(0, "presente"); tense_name.put(1, "perfeito"); tense_name.put(2, "imperfeito");
        tense_name.put(3, "mais que perfeito"); tense_name.put(4, "futuro"); tense_name.put(5, "condicional");
        Map<Integer, String> person_name = new HashMap<>();
        person_name.put(0, "eu"); person_name.put(1, "você"); person_name.put(2, "nós"); person_name.put(3, "vocês");


        int rand_tense_index = index_gen.nextInt(6);
        int rand_person_index = index_gen.nextInt(4);

        if (rand_person_index == 1) {
            int change_pronoun = index_gen.nextInt(3);
            if (change_pronoun == 0) {
                person_name.put(1, "ele");
            } else if (change_pronoun == 1) {
                person_name.put(1, "ela");
            }
        }

        if (rand_person_index == 3) {
            int change_pronoun = index_gen.nextInt(3);
            if (change_pronoun == 0) {
                person_name.put(3, "eles");
            } else if (change_pronoun == 1) {
                person_name.put(3, "elas");
            }
        }

        String[] tense_forms = tense_ref.get(rand_tense_index);
        return tense_forms[rand_person_index] + "-" + tense_name.get(rand_tense_index) + "-"
                + person_name.get(rand_person_index) + "-" + base_verb.infinitive;

    }

    private String init_verb_test(Verb[] verb_list, TextView infin_view, TextView tense_view, TextView person_view, TextView correct_view, EditText user_input) {
        String[] rand_response = rand_form(verb_list).split("-");
        String right_answer = rand_response[0];
        String disp_tense = rand_response[1];
        String disp_person = rand_response[2];
        String disp_infinitive = rand_response[3];

        infin_view.setText(disp_infinitive);
        tense_view.setText(disp_tense);
        person_view.setText(disp_person);

        return right_answer;
    }
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] raw_verb_list = VerbList.master_verb_list;
        final Verb[] verb_list = new Verb[raw_verb_list.length];
        for (int i = 0; i < raw_verb_list.length; i++) {
            verb_list[i] = new Verb(raw_verb_list[i]);
        }

        final TextView tense_view = (TextView) findViewById(R.id.disp_tense);
        final TextView person_view = (TextView) findViewById(R.id.disp_person);
        final TextView infin_view = (TextView) findViewById(R.id.disp_infiniive);
        final TextView correct_view = (TextView) findViewById(R.id.disp_correct);

        final EditText user_input = (EditText) findViewById(R.id.user_input);

        Button submit_button = (Button) findViewById(R.id.submit_button);

        submit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String user_answer = user_input.getText().toString();

                String correct_message = "Correct!";
                String incorrect_message = "Incorrect.";

                if (user_answer.equals(current_right_answer)) {
                    correct_view.setTextColor(Color.parseColor("green"));
                    correct_view.setText(correct_message);
                    user_input.setText("");
                    current_right_answer = init_verb_test(verb_list, infin_view, tense_view, person_view, correct_view, user_input);
                } else {
                    correct_view.setTextColor(Color.parseColor("red"));
                    correct_view.setText(incorrect_message);
                    user_input.setText("");
            }
        }});

//        List<String> reg_verbs_temp = new ArrayList<>();
//        File regular_verbs = new File("C:\\Users\\trump\\projects\\portuguese_verb_trainer\\regular_verbs.txt");
//        try {
//            Scanner in_file = new Scanner(regular_verbs);
//            while (in_file.hasNext()) {
//                reg_verbs_temp.add(in_file.next());
//            }
//        } catch(java.io.IOException e) {
//            System.out.println("error: " + e);
//        }
//
//        String[] raw_verb_list = reg_verbs_temp.toArray(new String[0]);


        current_right_answer = init_verb_test(verb_list, infin_view, tense_view, person_view, correct_view, user_input);
        String saved_user_input = user_input.getText().toString();
    }
}
