#ifndef STRATEGY_H
#define STRATEGY_H

#include <iostream>

enum Steps{D,C};

class Strategy {
public:

    Strategy(std::string name);
    virtual std::string getName();
    virtual Steps Step(Steps stepOne, Steps stepTwo) = 0;
    virtual Steps Step() = 0;

private:
    std::string name;

};
#endif // STRATEGY_H
