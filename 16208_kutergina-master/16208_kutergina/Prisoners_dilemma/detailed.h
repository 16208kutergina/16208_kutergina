#ifndef DETAILED_H
#define DETAILED_H

#include <array>
#include <iostream>
#include "strategy.h"
#include "parser.h"


class Detailed
{
public:
    Detailed(const std::vector <Strategy*> players, const Matrix game_matrix, int count_level);
    ~Detailed();
    void startGame();

    std::array <int, 3> getPlayers_Points();
    std::vector <std::array <Steps, 3> > getLevel_Steps();
    std::vector <std::array <int, 3> > getLevel_Points();
    std::vector <Strategy*> getPlayers();
    std::string getWinner();

private:
    void stepsToPoints();
    int sumPoints(int number_player);
    void findWinner();

    std::vector <Strategy*> players;
    Matrix game_matrix;
    int count_level;
    std::vector <std::array <Steps, 3> > level_steps;
    std::vector <std::array <int, 3> > level_points;
    std::array <int, 3> players_points;
    std::string winner;

};

#endif // DETAILED_H
