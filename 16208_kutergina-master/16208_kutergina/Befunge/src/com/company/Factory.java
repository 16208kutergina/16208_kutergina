package com.company;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Фабрика команд
 * @autor Анна Кутергина
 */
public class Factory {
    private static Logger log = Logger.getLogger(Factory.class.getName());
    /**Контейнер со списком доступных команд*/
    private HashMap<String, String> commands;
    /**Одиночный объект класса*/
    private static Factory f = null;
    /**Конструктор объекта, получает данные из файла ConfigFactory.txt*/
    private Factory() {
        commands = new HashMap<>(Parser.getFactoryData("ConfigFactory.txt"));
    log.fine("Factory constracted");
    }
    /**Метод для получения доступа к объекту класса*/
    public static Factory instance()  {
        if (null == f) {
            f = new Factory();
        }
        log.fine("Factory.instance()");
        return f;
    }
    /**Получение объекта класса команды по обозначению
     * @param id */
    public Command get(String id) {
        try{String s = "com.company.commands." + commands.get(id);
            Class c = Class.forName(s);
            Object o= c.newInstance();
            log.fine("Get command successful");
            return (Command) o;
        } catch (IllegalAccessException e) {
            log.log(Level.SEVERE, "Invalid arguments");
        } catch (InstantiationException e) {
            log.log(Level.SEVERE, "Attempt to create object of an abstract class or interface");
        } catch (ClassNotFoundException e) {
            log.log(Level.SEVERE,"Class not found");
        }
        return null;
    }
}
