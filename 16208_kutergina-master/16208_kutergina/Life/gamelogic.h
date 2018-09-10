#ifndef GAMELOGIC_H
#define GAMELOGIC_H
#include <iostream>
#include <vector>

class GameLogic
{
public:
    GameLogic(size_t size);
    GameLogic();
    ~GameLogic();

    size_t getSize();
    void simulate(size_t birth, size_t life);
    void clear();
    void setCell(size_t x, size_t y);
    bool getCell(size_t x, size_t y);
    void resize(size_t size);

private:
    std::vector<std::vector <bool>> mainField;
    std::vector<std::vector <bool>> nextField;
    size_t size = 0;
    const std::vector <std::vector <int>> neighborOffset =
    {{-1, -1}, {0, -1}, {1, -1}, {-1, 0}, {1, 0}, {-1, 1}, {0, 1}, {1, 1}};

    bool simulateCell(size_t x, size_t y, size_t birth, size_t life);
    size_t countNeighbors(size_t x, size_t y);

};

#endif // GAMELOGIC_H
