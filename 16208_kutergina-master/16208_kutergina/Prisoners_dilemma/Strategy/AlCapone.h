#ifndef ROBBERONE
#define ROBBERONE

#include"../strategy.h"
#include"../factory.h"

class AlCapone : public Strategy{

public:
    AlCapone() : Strategy("AlCapone") {}

    Steps Step(Steps stepOne, Steps /*stepTwo*/) override {
        return C;
    }

    Steps Step() override {
        return C;
    }
};

Strategy * createAC() {
    return new AlCapone;
}

namespace {
bool alcapone = Factory <Strategy, Strategy * (*)(), std::string> :: get_instance()->registr( "AlCapone", createAC);
}


#endif
