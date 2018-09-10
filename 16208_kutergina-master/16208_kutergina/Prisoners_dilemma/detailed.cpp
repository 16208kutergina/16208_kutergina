#include "detailed.h"
#include <array>

Detailed::Detailed(const std::vector <Strategy*> players, const Matrix game_matrix, int count_level):
    players(players),
    game_matrix(game_matrix),
    count_level(count_level){
    level_steps.resize(count_level);
    level_points.resize(count_level);
}

Detailed::~Detailed(){
}

void Detailed::startGame(){
    level_steps[0][0] = players.at(0)->Step();
    level_steps[0][1] = players.at(1)->Step();
    level_steps[0][2] = players.at(2)->Step();
    for(int i=1;i<count_level;i++){
        level_steps[i][0] = players.at(0)->Step(level_steps[i-1][1],level_steps[i-1][2]);
        level_steps[i][1] = players.at(1)->Step(level_steps[i-1][0],level_steps[i-1][2]);
        level_steps[i][2] = players.at(2)->Step(level_steps[i-1][0],level_steps[i-1][1]);
    }
    stepsToPoints();
    players_points[0] = sumPoints(0);
    players_points[1] = sumPoints(1);
    players_points[2] = sumPoints(2);
    findWinner();
}

std::array<int, 3> Detailed::getPlayers_Points(){
    return players_points;
}


std::vector<std::array<Steps, 3> > Detailed::getLevel_Steps(){
    return level_steps;
}

std::vector<std::array<int, 3> > Detailed::getLevel_Points(){
    return level_points;
}

std::vector<Strategy*> Detailed::getPlayers(){
    return players;
}

std::string Detailed::getWinner(){
    return winner;
}

void Detailed::stepsToPoints(){
    for(int i = 0; i < count_level; i++){
        for(int j = 0; j < 8; j++){
            if(level_steps[i][0] == game_matrix.steps[j][0] &&
                    level_steps[i][1] == game_matrix.steps[j][1] &&
                    level_steps[i][2] == game_matrix.steps[j][2]){
                level_points[i][0] = game_matrix.points[j][0];
                level_points[i][1] = game_matrix.points[j][1];
                level_points[i][2] = game_matrix.points[j][2];
            }
        }
    }
}

int Detailed::sumPoints(int number_player){
    int point = 0;
    for(int i = 0; i < count_level; i++){
        point += level_points[i][number_player];
    }
    return point;
}

void Detailed::findWinner(){
    std::string name = players.at(0)->getName();
    int min = players_points[0];
    if (min == players_points[1]) {
        name +=", ";
        name += players.at(1)->getName();
    }
    if (min > players_points[1]){
        min = players_points[1];
        name = players.at(1)->getName();
    }
    if(min == players_points[2]) {
        name +=", ";
        name += players.at(2)->getName();
    }
    if(min > players_points[2]){
        min = players_points[2];
        name = players.at(2)->getName();
    }
    winner = name;
}


