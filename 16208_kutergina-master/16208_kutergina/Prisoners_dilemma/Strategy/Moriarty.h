#ifndef MORIARTY
#define MORIARTY

#include "../strategy.h"
#include "../factory.h"
#include <vector>

class Moriarty : public Strategy{

    std::vector <Steps> stepsOne;
    std::vector <Steps> stepsTwo;

public:

    Moriarty() : Strategy("Moriarty") {}

    Steps Step(Steps stepOne, Steps stepTwo) override {
        stepsOne.push_back(stepOne);
        stepsTwo.push_back(stepTwo);
        int countD = 0;
        for(int i = 0; i < stepsOne.size(); i++){
            if(stepsOne.at(i) == D) {
                countD++;
            }
            else {
                countD--;
            }
            if(stepsTwo.at(i) == D) {
                countD++;
            }
            else {
                countD--;
            }
        }if (countD < 0) return D;
        return C;
    }

    Steps Step() override {
        return D;
    }
};

Strategy * createM(){
    return new Moriarty;
}

namespace {
bool moriarty = Factory <Strategy, Strategy*(*)(), std::string>::get_instance()->registr("Moriarty", createM);
}


#endif // MORIARTY

