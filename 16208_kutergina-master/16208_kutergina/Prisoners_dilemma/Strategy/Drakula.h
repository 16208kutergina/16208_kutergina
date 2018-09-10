#ifndef ROBBERTWO
#define ROBBERTWO

#include "../strategy.h"
#include "../factory.h"
#include <vector>

class Drakula : public Strategy{

    std::vector <Steps> stepsOne;
    std::vector <Steps> stepsTwo;

public:
    Drakula() : Strategy("Drakula") {}

    Steps Step(Steps stepOne, Steps stepTwo) override {
        stepsOne.push_back(stepOne);
        stepsTwo.push_back(stepTwo);
        int countD = 0;
        for(int i = 0; i < stepsOne.size(); i++){
            if(stepsOne.at(i) == D || stepsTwo.at(i) == D)
                countD++;
        }
        if(countD % 3 == 0) return D;
        return C;
    }

    Steps Step() override {
        return C;
    }
};

Strategy * createD(){
    return new Drakula;
}
namespace {
bool drakula = Factory <Strategy, Strategy*(*)(), std::string>::get_instance()->registr("Drakula", createD);
}


#endif
