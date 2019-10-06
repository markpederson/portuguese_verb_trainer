#include <iostream>
#include <string>
#include <vector>
#include <map>

class reg_verb {
    public:

        std::vector<std::string> present;
        std::vector<std::string> imperf;
        std::vector<std::string> pret;
        std::vector<std::string> fut;
        std::vector<std::string> fut_past;

        std::vector<std::string> subj_pres;
        std::vector<std::string> subj_imp;
        std::vector<std::string> subj_fut;
        
        std::vector<std::string> imperative;
        std::string gerund;
        std::string participle;
        std::string infinitive;

        std::string ending;
        std::string stem;

        reg_verb(std::string infin) {
            ending =  infin.substr(infin.length()-2, 2);
            stem = infin.substr(0, infin.length()-2);
            infinitive = infin;
            if (ending == "ar") {
                present.push_back(stem + "o"); present.push_back(stem + "a"); present.push_back(stem + "amos"); present.push_back(stem + "am");
                imperf.push_back(stem + "ava"); imperf.push_back(stem + "ava"); imperf.push_back(stem + "ávamos"); imperf.push_back(stem + "avam");
                pret.push_back(stem + "ei"); pret.push_back(stem + "ou"); pret.push_back(stem + "amos"); pret.push_back(stem + "aram");
                fut.push_back(infin + "ei"); fut.push_back(infin + "á"); fut.push_back(infin + "emos"); fut.push_back(infin + "ão");
                //fut_past
                subj_pres.push_back(stem + "e"); subj_pres.push_back(stem + "e"); subj_pres.push_back(stem + "emos"); subj_pres.push_back(stem + "em");
                subj_imp.push_back(stem + "asse"); subj_imp.push_back(stem + "asse"); subj_imp.push_back(stem + "ássemos"); subj_imp.push_back(stem + "assem");
                subj_fut.push_back(stem + "ar"); subj_fut.push_back(stem + "ar"); subj_fut.push_back(stem + "armos"); subj_fut.push_back(stem + "arem");
                //imperative
                gerund = stem + "ando";
                participle = stem + "ado";
            }
        }

        void print_persons(std::vector<std::string> forms, std::string tense) {
            std::map<int, std::string> person_ref{ {0, "1 sg."}, {1, "3 sg."}, {2, "1 pl."}, {3, "2 pl."} };
            int person_index = 0;
            for (std::vector<std::string>::iterator it = forms.begin(); it != forms.end(); ++it) {
                std::cout << tense << " " << person_ref[person_index] << ": " << *it << "\n";
                person_index++;
            }
            std::cout << "\n";
        }

        void print_forms() {
            std::cout << "\n";
            std::cout << "infinitive: " << infinitive << "\n";
            std::cout << "stem: " << stem  << "-\n";
            std::cout << "ending: -" << ending  << "\n\n";
            print_persons(present, "present");
            print_persons(imperf, "imperfect");
            print_persons(pret, "preterite");
            print_persons(fut, "future");
            print_persons(subj_pres, "present subj.");
            print_persons(subj_imp, "imperfect subj.");
            print_persons(subj_fut, "future subj.");
            std::cout << "gerund: " << gerund << "\n";
            std::cout << "participle: " << participle << "\n";
            std::cout << "\n";
        }
};

int main() {
    reg_verb test("falar");
    test.print_forms();
}