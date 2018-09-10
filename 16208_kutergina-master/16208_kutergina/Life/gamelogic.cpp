#include "gamelogic.h"




GameLogic::GameLogic()
{}

GameLogic::~GameLogic()
{}

void GameLogic::resize(size_t size){
    this->size = size;
    mainField.resize(size + 2);
    nextField.resize(size + 2);
    for (size_t i = 0; i < size + 2; i++){
        mainField[i].resize(size + 2, false);
        nextField[i].resize(size + 2, false);
    }
}


size_t GameLogic::getSize()
{
    return size;
}


void GameLogic::clear()
{
    for (size_t i = 0; i < size + 2; i++){
        std::fill(mainField[i].begin(), mainField[i].end(), false);
    }
}

void GameLogic::setCell(size_t x, size_t y)
{
    mainField[x][y] = !mainField[x][y];
}

bool GameLogic::getCell(size_t x, size_t y)
{
    return mainField[x][y];
}

size_t GameLogic::countNeighbors(size_t x, size_t y)
{
    size_t power = 0;
    for(size_t i = 0; i < neighborOffset.size(); i++){
        power += mainField[x + neighborOffset[i][0]][y + neighborOffset[i][1]];
    }
    return power;
}

bool GameLogic::simulateCell(size_t x, size_t y, size_t birth, size_t life){
    if (countNeighbors(x,y) == birth || (countNeighbors(x,y) == life && mainField[x][y])){
        return true;
    }
    return false;
}

void GameLogic::simulate(size_t birth, size_t life){
    for (size_t x = 1; x <= size; x++){
        for(size_t y = 1; y <= size; y++){
            nextField[x][y] = simulateCell(x, y, birth, life);
        }
    }
    std::swap(mainField, nextField);
}



