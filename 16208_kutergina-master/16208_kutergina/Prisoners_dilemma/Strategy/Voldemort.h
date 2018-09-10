#ifndef VOLDEMORT
#define VOLDEMORT

#include "../strategy.h"
#include "../factory.h"
#include <random>

class Voldemort:public Strategy{

public:
    Voldemort() : Strategy("Voldemort") {}

    Steps Step(Steps stepOne, Steps stepTwo) override {
        int random = rand();
        if(random % 2 == 1){
            return C;}
        return D;
    }

    Steps Step() override {
        return C;
    }
};

Strategy * createV(){
    return new Voldemort;
}

namespace {
bool voldemort = Factory <Strategy, Strategy*(*)(), std::string>::get_instance()->registr("Voldemort", createV);
}

#endif // ROBBERTHREE

