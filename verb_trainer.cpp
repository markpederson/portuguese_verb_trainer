#include <iostream>
#include <vector>
#include <string>
#include <map>
#include <fstream>
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

vector<string> read_words_file(string file_name) {
    string word;
    ifstream words_file;
    words_file.open(file_name);

    if(!words_file) {
        cout << "feck";
    }

    vector<string> words_list;
    while (words_file >> word) {
        words_list.push_back(word);
    }

    return words_list;
}

void print_words_list(vector<string> words_list) {
    for (int i = 0; i < words_list.size(); i++) {
        cout << words_list[i] << "\n";
    }
}

string get_random_word(vector<string> words_list) {
    int word_index = rand() % words_list.size();
    return words_list[word_index];
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

    vector<string> words_list = read_words_file("1000_words_list.txt");
    // print_words_list(words_list);
    // run_test(verb_list, conjugated, verb_forms);

    vector<string> random_words;
    srand(time(NULL));
    for (int i = 0; i < 1000; i++) {
        random_words.push_back(get_random_word(words_list));
    }

    int count = 0;
    string user_input;
    while (true) {
        cin >> user_input;
        if (user_input == "q()") {
            break;
        } else {
            cout << random_words[count] << "\n";
            count++;
        }
    }
}