#ifndef GAMEWIDGET_H
#define GAMEWIDGET_H

#include <QWidget>
#include "gamelogic.h"

class GameWidget : public QWidget
{
    Q_OBJECT
public:
    explicit GameWidget(QWidget *parent = 0);
    ~GameWidget();

    QString saveData();
    void loadData(const QString &data);

protected:
    void paintEvent(QPaintEvent *);
    void mousePressEvent(QMouseEvent *e);
    void mouseMoveEvent(QMouseEvent *e);

public slots:
    void startGame(size_t birth, size_t life);
    void stopGame();
    void clear();
    void setInterval(int msec);
    void setCellNumber(const int &s);

private slots:
    void paintGrid(QPainter &p);
    void paintUniverse(QPainter &p);
    void newGeneration();

private:

    QTimer* timer;
    GameLogic game;
    size_t neighborForBirth = 3;
    size_t neighborForLife = 2;


};

#endif // GAMEWIDGET_H
