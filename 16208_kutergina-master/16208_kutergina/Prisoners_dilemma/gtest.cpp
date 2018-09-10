#include "strategy.h"
#include "parser.h"
#include "detailed.h"
#include "tournament.h"
#include "start.h"
#include "Strategy/AlCapone.h"
#include "Strategy/DarthVader.h"
#include "gtest/gtest.h"

TEST(Detailed__Test, Startgame_Test) {
    std::vector <Strategy*> players;
    players.push_back (f->create("DarthVader"));
    players.push_back (f->create("AlCapone"));
    players.push_back (f->create("AlCapone"));
    Matrix game_matrix;
    int count_level = 2;
    Detailed game(players, game_matrix, count_level);
    game.startGame();
    std::vector <std::array <Steps, 3> > level_steps(2);
    level_steps[0][0] = D;
    level_steps[0][1] = C;
    level_steps[0][2] = C;
    level_steps[1][0] = D;
    level_steps[1][1] = C;
    level_steps[1][2] = C;
    std::vector <std::array <int, 3> > level_points(2);
    level_points[0][0] = 5;
    level_points[0][1] = 2;
    level_points[0][2] = 2;
    level_points[1][0] = 5;
    level_points[1][1] = 2;
    level_points[1][2] = 2;
    std::array <int, 3> players_points;
    players_points[0] = 10;
    players_points[1] = 4;
    players_points[2] = 4;
    std::string winner = "AlCapone, AlCapone";

    ASSERT_EQ (level_points, game.getLevel_Points());
    ASSERT_EQ(level_steps, game.getLevel_Steps());
    ASSERT_EQ(players, game.getPlayers());
    ASSERT_EQ(players_points, game.getPlayers_Points());
    ASSERT_EQ(winner, game.getWinner());
}

TEST(Tournament_Test, Startgame_Test){

    std::vector<Strategy*> players;
    players.push_back(f->create("DarthVader"));
    players.push_back(f->create("AlCapone"));
    players.push_back(f->create("AlCapone"));
    Matrix game_matrix;
    int count_level=1;
    Tournament game(players, game_matrix,count_level);
    game.startGame();
    std::string winner="AlCapone, AlCapone";
    ASSERT_EQ(winner,game.getLevel_winners().at(0));
    ASSERT_EQ(winner,game.getWinner());
}

TEST(Factory_Test, Create_FalseName){
    ASSERT_ANY_THROW(f->create("FalseName"));
}

TEST(Start_Test, Void_Test){
    std::vector<std::shared_ptr<Strategy> > strategies;
    std::shared_ptr<Strategy> strategy (f->create("DarthVader"));
    strategies.push_back(strategy);
    strategies.push_back(strategy);
    strategies.push_back(strategy);
    ASSERT_NO_THROW(Start("*fast",strategies,2));
    ASSERT_NO_THROW(Start("*detailed",strategies,2));
    strategies.push_back(strategy);
    ASSERT_ANY_THROW(Start("*fast",strategies,2));
    ASSERT_NO_THROW(Start("*tournament",strategies,2));
}

int main(int argc, char** argv)
{
    ::testing::InitGoogleTest(&argc, argv);
    return RUN_ALL_TESTS();

}
