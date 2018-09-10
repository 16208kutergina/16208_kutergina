package com.company.commands;

import com.company.Command;
import com.company.Redactor;

import java.util.logging.Logger;

public class Random implements Command {
    private static Logger log = Logger.getLogger(Random.class.getName());

    @Override
    public void execute(Redactor field) {
        log.fine("Random.execute()");
        java.util.Random r = new java.util.Random(System.currentTimeMillis());
        int x = r.nextInt()%3 - 1;
        int y = r.nextInt()%3 - 1;
        field.setDirection(x, y);
    }
}
