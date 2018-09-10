#ifndef START
#define START

#include <memory>//?????
#include <iostream>
#include "parser.h"
#include "detailed.h"
#include "tournament.h"

void Start(const std::string & mode, std::vector<std::shared_ptr <Strategy> > strategies_ptr, unsigned count_level){
    if(strategies_ptr.size() < 3){
        std::cout << "Not enough strategies "<< std::endl;
        return;
    }
    Matrix m;
    std::vector <Strategy*> strategies;
    for(int i = 0; i < strategies_ptr.size(); i++) {
        strategies.push_back(strategies_ptr.at(i).get());
    }
    if(strategies_ptr.size() > 3 && mode != "*tournament") {
        throw std::out_of_range("You can't play, only tournament");
    }
    for(int i = 0; i < strategies.size(); i++) {
        std::cout << i+1 << ". " << strategies.at(i)->getName() << std::endl;
    }
    if(mode == "*detailed" || mode == "*fast") {
        Detailed game(strategies, m, count_level);
        game.startGame();
        if(mode == "*detailed"){
            for(int i = 0; i < count_level; i++){
                std::cin.get();
                std::cout << game.getLevel_Steps()[i][0] << " " <<
                                                            game.getLevel_Steps()[i][1] << " " <<
                                                            game.getLevel_Steps()[i][2] <<"     "<<
                                                            game.getLevel_Points()[i][0]<<" "<<
                                                            game.getLevel_Points()[i][1]<<" "<<
                                                            game.getLevel_Points()[i][2]<<" "<<std::endl;
            }
        }
        std::cout << "Winner(s) of game: " << game.getWinner() << std::endl;
    }
    if(mode == "*tournament") {
        Tournament tournament(strategies, m, count_level);
        tournament.startGame();
        int j = 0;
        for(std::string winner : tournament.getLevel_winners()) {
            std::cout << winner << std::endl;
        }
        std::cout << "The winner(s) of game:" << tournament.getWinner() << std::endl;
    }
}

#endif // START

