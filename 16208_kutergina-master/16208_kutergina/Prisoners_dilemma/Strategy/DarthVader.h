#ifndef DARTHVADER
#define DARTHVADER
#include"../strategy.h"
#include"../factory.h"

class DarthVader : public Strategy{

public:

    DarthVader() : Strategy("DarthVader") {}

    Steps Step(Steps stepOne, Steps stepTwo) override {
        return D;
    }

    Steps Step() override {
        return D;
    }
};

Strategy * createDV(){
    return new DarthVader;
}


namespace {
bool darthvader = Factory <Strategy, Strategy*(*)(), std::string>::get_instance()->registr("DarthVader", createDV);
}
#endif


