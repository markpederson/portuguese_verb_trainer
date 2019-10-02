#include <iostream>
#include <vector>
#include <string>
#include <map>
using namespace std;

void run_test(vector<string> verb_list, vector<vector<string>> conjugated, map<int, string> verb_forms) {
    int verb_index = rand() % 15;
    int form_index = rand() % 20;
    cout << verb_index + "\n";
    cout << verb_list[verb_index] + ": " + verb_forms[form_index] + "\n";
    string user_input;
    while (true) {
        cin >> user_input;
        if (user_input == "q()") {
            break;
        } else if (user_input == conjugated[verb_index][form_index]) {
            cout << "Correct!\n\n";
            break;
        } else {
            cout << "Incorrect. Try again.\n";
            continue;
        }
    }
}

int main() {

    vector<string> a_endings = {"o", "a", "amos", "am", "ava", "ava", "ávamos", "avam",
                                "ei", "ou", "amos", "aram","ei", "á", "emos", "ão",
                                "ia", "ia", "íamos", "iam"};
    
    vector<string> e_endings = {"o", "e", "emos", "em", "ia" , "ia", "íamos", "îam",
                                "i", "eu", "emos", "eram", "ei", "á", "emos", "ão",
                                "ia", "ia", "íamos", "iam"};
    
    vector<string> i_endings = {"o", "e", "imos", "em", "ia", "ia", "íamos", "iam",
                                "i", "iu", "imos", "iram", "ei", "á", "emos", "ão",
                                "ia", "ia", "íamos", "iam"};

    vector<string> tenses = {"pres.", "imperf.", "perf.", "fut.", "fut. pret."};
    vector<string> persons = {"1 sg.", "3 sg.", "1 pl.", "3 pl."};
    map<int, string> verb_forms;
    int map_index = 0;
    for (int i = 0; i < tenses.size(); i++) {
        for (int j = 0; j < persons.size(); j++) {
                verb_forms[map_index] = persons[j] + " " + tenses[i];
                map_index++;
        }
    }

    vector<string> verb_list = {"amar", "beber", "cantar", "chegar", "comer", "comprar", "conhecer", "correr",
                                "dormir", "estudar", "falar", "ficar", "partir", "vender", "viver"};
    
    vector<vector<string>> conjugated(verb_list.size(), vector<string>(a_endings.size()));

    for (int i = 0; i < verb_list.size(); i++) {
        string verb_stem = verb_list[i].substr(0, verb_list[i].length()-2);
        string verb_ending = verb_list[i].substr(verb_list[i].length()-2, 2);

        vector<string> verb_endings;
        if (verb_ending == "ar") {
            verb_endings = a_endings;
        } else if (verb_ending == "er") {
            verb_endings = e_endings;
        } else {
            verb_endings = i_endings;
        }

        for (int j = 0; j < verb_endings.size(); j++) {
            conjugated[i][j] = verb_stem + verb_endings[j];
        }
    }

    run_test(verb_list, conjugated, verb_forms);
}