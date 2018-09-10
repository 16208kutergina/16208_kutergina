#include "tournament.h"
#include <cstring>

Tournament::Tournament(const std::vector<Strategy*> players, const Matrix game_matrix, int count_level):
    players(players),
    game_matrix(game_matrix),
    count_level(count_level){
}

void Tournament::startGame() {
    std::vector <Strategy*> temporary;
    std::vector <Strategy*> forGame;
    int points[players.size()];
    memset(points, 0, sizeof(points));

    for(int k = 0; k < players.size(); k++) {
        if(k < 2){
            temporary.push_back(players.at(k));
        }
        else{
            for(int i = 0; i < temporary.size() - 1; i++){
                for(int j = i + 1; j < temporary.size(); j++){
                    forGame.push_back(temporary.at(i)); //добавляем стратегии в массив для создания игры
                    forGame.push_back(temporary.at(j));
                    forGame.push_back(players.at(k));
                    Detailed game(forGame,game_matrix,count_level); //создаём игру
                    game.startGame();                              //проводим игру
                    points[i] += game.getPlayers_Points()[0];//прибавляем новые очки к уже имеющимся у стратегий
                    points[j] += game.getPlayers_Points()[1];
                    points[temporary.size()] += game.getPlayers_Points()[2];
                    level_winners.push_back(game.getWinner());     //запоминаем победителя игры
                    forGame.clear();                               //очищаем массив для создания игры
                }
            }
            temporary.push_back(players.at(k));//добавляем новую стратегию в временный вектор стратегий
        }
    }
    int point = points[0];//вычисление победителя турнира
    std::string name = players.at(0)->getName();
    for(int i = 1; i < players.size(); i++){
        if(point == points[i]){
            name += ", ";
            name += players.at(i)->getName();
        }
        if(point > points[i]){
            point = points[i];
            name = players.at(i)->getName();
        }
    }
    winner = name;
}

std::vector<std::string> Tournament::getLevel_winners(){
    return level_winners;
}

std::string Tournament::getWinner(){
    return winner;
}
