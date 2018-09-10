#ifndef PARSER
#define PARSER

#include <iostream>
#include <fstream>
#include <vector>

const std::string namefile = "/home/anna/PD_rewrite/text.txt";

static std::vector <std::string> readfile() {
    std::vector <std::string> str1;
    std::ifstream file(namefile);
    if (!file.is_open())
        throw (std::string) "File can not be opened!\n";
    else {
        std::string s;
        while(!file.eof()){
            file >> s;
            str1.push_back(s);
        }
    }
    file.close();
    return str1;
}

struct Matrix {
    int steps [8][3];
    int points [8][3];

    Matrix() {
        std::vector <std::string> str1 = readfile();
        int letter [8 * 3]; int let = 0;
        int figure [8 * 3]; int fig = 0;
        for(int i = 0; i < str1.size() - 1; i++) {
            if(str1.at(i) == "C") {
                letter[let] = 1;
                let++;
            }
            else
                if(str1.at(i) == "D") {
                    letter[let] = 0;
                    let++;
                }
                else
                    if(str1.at(i) != "=>" && str1.at(i) != "C" && str1.at(i) != "D") {
                        figure[fig] = atoi(str1.at(i).c_str());
                        fig++;
                    }
        }
        for(int i = 0; i < 8 * 3; i++){
            steps [(int) (i / 3.)] [i % 3] = letter [i];
            points [(int) (i / 3.)] [i % 3] = figure [i];
        }
    }
};


#endif // PARSER

