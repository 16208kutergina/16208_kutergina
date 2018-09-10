package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.exit;
import static java.lang.System.in;

/**
 * Класс для разбора данных конфигурационных файлов
 * @autor Анна Кутергина
 */

public class Parser{
    private static Logger log = Logger.getLogger(Parser.class.getName());
    /**Получение информации о доступных командах
     * возможно возникновение ошибок при чтении файла*/
    static public HashMap<String, String> getFactoryData(String filename) {
        try {
            InputStream input = Parser.class.getResourceAsStream(filename);
            HashMap<String, String> commands = new HashMap<>();
            Properties props = new Properties();
            props.load(input);
            input.close();
            Set<String> keys = props.stringPropertyNames();
            int i = 0;
            for (String x : keys) {
                commands.put(x, props.getProperty(x));
            }
            log.fine("Config factory");
            return commands;
        } catch (IOException e) {
            log.log(Level.SEVERE,"error of reading from the input stream");
            return null;
        }
    }
    /**Получение данных программы на языке Befunge
     * возможно возникновение ошибок при чтении файла
     * или несоответствия размера файла*/
    static public char[][] getProgram(String filename){
        char[][] info = new char[85][25];
        try{
            FileInputStream reader = new FileInputStream(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(reader));
            String strLine;
            int y = 0;
            for(int i = 0; i < info.length; i++) {
                for (int j = 0; j < info[0].length; j++) {
                    info[i][j] = ' ';
                }
            }
            while ((strLine = br.readLine()) != null){
                for(int i = 0; i < strLine.length();i++){
                    if(i > info.length) {
                        throw new IOException();
                    }
                    info[i][y] = strLine.charAt(i);
                }
                y++;
                if(y > info[0].length){
                    throw new IOException();
                }
            }
            log.fine("Program got");
            return info;
        } catch (FileNotFoundException e) {
            System.out.println("Give me a program file! ");
            log.log(Level.SEVERE, "File not found");
            Scanner sc = new Scanner(System.in);
            filename = sc.nextLine();
            return getProgram(filename);
        } catch (IOException e) {
            log.log(Level.SEVERE, "Exeption size of string or readLine");
            return null;
        }
    }

}
