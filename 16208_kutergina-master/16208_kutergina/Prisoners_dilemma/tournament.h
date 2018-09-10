
#ifndef TOURNAMENT_H
#define TOURNAMENT_H
#include "detailed.h"
#include "strategy.h"


class Tournament
{
public:
    Tournament(const std::vector<Strategy*> players,const Matrix game_matrix, int count_level);

public:
    void startGame();
    std::vector<std::string> getLevel_winners();
    std::string getWinner();

private:

    std::vector<Strategy*>  players;
    std::vector<std::string> level_winners;
    unsigned count_level;
    Matrix game_matrix;
    std::string winner = "";

};

#endif // TOURNAMENT_H
