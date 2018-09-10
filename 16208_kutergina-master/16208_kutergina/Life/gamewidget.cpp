#include <QTimer>
#include <QMouseEvent>
#include <QPainter>
#include "gamewidget.h"

GameWidget::GameWidget(QWidget *parent) :
    QWidget(parent),
    timer(new QTimer(this))
{
    timer->setInterval(100);
    game.resize(50);
    connect(timer, SIGNAL(timeout()), this, SLOT(newGeneration()));
}

GameWidget::~GameWidget()
{}

void GameWidget::startGame(size_t birth, size_t life)
{
    neighborForBirth = birth;
    neighborForLife = life;
    timer->start();
}

void GameWidget::stopGame()
{
    timer->stop();
}

void GameWidget::clear()
{
    game.clear();
    update();
}

void GameWidget::setCellNumber(const int &s)
{
    game.resize(s);
    update();
}


void GameWidget::setInterval(int msec)
{
    timer->setInterval(msec);
}

void GameWidget::newGeneration()
{
    game.simulate(neighborForBirth, neighborForLife);
    update();
}

void GameWidget::paintEvent(QPaintEvent *)
{
    QPainter p(this);
    paintGrid(p);
    paintUniverse(p);
}

void GameWidget::mousePressEvent(QMouseEvent *e)
{
    double cellWidth = (double)width() / game.getSize();
    double cellHeight = (double)height() / game.getSize();
    int k = floor(e->y() / cellHeight) + 1;
    int j = floor(e->x() / cellWidth) + 1;
    game.setCell(j, k);
    update();
}

void GameWidget::mouseMoveEvent(QMouseEvent *e)
{
    double cellWidth = (double) width() / game.getSize();
    double cellHeight = (double) height() / game.getSize();
    int k = floor(e->y() / cellHeight) + 1;
    int j = floor(e->x() / cellWidth) + 1;
    if(k < 0 || k > game.getSize() || j < 0 || j > game.getSize()){
        return;
    }
    if(!game.getCell(j,k)){
        game.setCell(j,k);
        update();
    }
}

void GameWidget::paintGrid(QPainter &p)
{   double cellWidth = (double) width() / game.getSize();
    double cellHeight = (double) height() / game.getSize();
    QRect borders(0, 0, width() - 1, height() - 1);
    p.setPen(Qt::gray);
    for(double k = cellWidth; k <= width(); k += cellWidth)
        p.drawLine(k, 0, k, height());
    for(double k = cellHeight; k <= height(); k += cellHeight)
        p.drawLine(0, k, width(), k);
    p.drawRect(borders);
}

void GameWidget::paintUniverse(QPainter &p)
{
    double cellWidth = (double) width() / game.getSize();
    double cellHeight = (double) height() / game.getSize();
    for(size_t k = 1; k <= game.getSize(); k++) {
        for(size_t j = 1; j <= game.getSize(); j++) {
            if(game.getCell(j, k)) {
                qreal left = (qreal)(cellWidth * j - cellWidth);
                qreal top  = (qreal)(cellHeight * k - cellHeight);
                QRectF r(left, top, (qreal) cellWidth, (qreal) cellHeight);
                p.fillRect(r, QBrush(Qt::black));
            }
        }
    }
}

QString GameWidget::saveData(){

    QString data="";
    data.append(QString::number(game.getSize()));
    data+="\n";
    bool condition = game.getCell(1,1);
    QString codeCondition;
    if (condition){
        codeCondition = "*";
    }
    else {
        codeCondition = "#";
    }
    size_t countCondition = 0;
    for (size_t i = 1; i < game.getSize() + 1; i++ ){
        for (size_t j = 1; j < game.getSize() + 1; j++ ){
            if (game.getCell(i, j) == condition){
                countCondition++;
            }
            else{
                data.append(codeCondition);
                data+=" ";
                data.append(QString::number(countCondition));
                data+=" ";
                countCondition = 1;
                condition = !condition;
                if(codeCondition == "*"){
                    codeCondition = "#";
                }
                else{
                    codeCondition = "*";
                }
            }
        }
    }
    return data;
}

void GameWidget::loadData(const QString &data)
{
    game.clear();
    int numberSimbol = 0;
    int numberCells = 1;
    const QString dead = "#";
    const QString live = "*";
    while(numberSimbol < data.size()){
        QString condition = data[numberSimbol];
        if (condition == dead || condition == live){
            QString strNumberCells = "";
            numberSimbol++;
            while(numberSimbol != data.size() && data[numberSimbol] != live && data[numberSimbol] != dead ) {
                strNumberCells += data[numberSimbol];
                numberSimbol++;
            }
            if(condition == live) {
                for (int i = numberCells; i < numberCells + strNumberCells.toInt(); i++ ){
                    game.setCell(i / game.getSize() + 1 , i % game.getSize());
                }
            }
            numberCells += strNumberCells.toInt();
        }
    }
    update();
}
