//#include <iostream>
//#include <vector>
//#include "strategy.h"
//#include "factory.h"
//#include "Strategy/DarthVader.h"
//#include "Strategy/Moriarty.h"
//#include "Strategy/AlCapone.h"
//#include "Strategy/Chikatilo.h"
//#include "Strategy/Drakula.h"
//#include "Strategy/Voldemort.h"
//#include "start.h"


//int main(int argc, char *argv[] )
//{
//    std::string mode;
//    for(int i = 1; i < argc; i++){
//        if(argv[i][0] == '*'){
//            mode = argv[i];
//        }
//    }

//    if(mode.empty()){
//        std::cout<<"Please, give me mode!"<<std::endl;
//        return -1;
//    }

//    unsigned count_level = 0;
//    for(int i = 0; i < argc; i++) {
//        if(atoi(argv[i]) != 0){
//            count_level = atoi(argv[i]);
//        }
//    }

//    if (argc < 6){
//        std::cerr << "Please give me units!" << std::endl;
//        return -1;
//    }

//    Factory <Strategy, Strategy*(*)(), std::string> * f = Factory<Strategy, Strategy*(*)(), std::string>::get_instance();
//    std::vector<std::shared_ptr<Strategy> > strategies;

//    for(int i = 0; i < argc; i++){
//        if(f->contains(argv[i])){
//            std::shared_ptr<Strategy> strategy (f->create(argv[i]));
//            strategies.push_back(strategy);
//        }
//    }

//    Start(mode, strategies, count_level);
//}

