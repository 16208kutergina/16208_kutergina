#ifndef CHIKATILO
#define CHIKATILO

#include "../strategy.h"
#include "../factory.h"
#include <vector>

class Chikatilo : public Strategy{
    std::vector <Steps> stepsOne;
    std::vector <Steps> stepsTwo;

public:
    Chikatilo() : Strategy("Chikatilo") {}

    Steps Step(Steps stepOne, Steps stepTwo) override {
        stepsOne.push_back(stepOne);
        stepsTwo.push_back(stepTwo);
        for(int i = 0; i < stepsOne.size(); i++){
            if(stepsOne.at(i) == D && stepsTwo.at(i) == D)
                return D;
        }
        return C;
    }

    Steps Step() override {
        return D;
    }
};

Strategy * createCh() {
    return new Chikatilo;
}

namespace {
bool chikatilo = Factory <Strategy, Strategy*(*)(), std::string>::get_instance()->registr("Chikatilo", createCh);
}



#endif

