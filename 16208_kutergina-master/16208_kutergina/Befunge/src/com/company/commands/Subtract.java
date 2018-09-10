package com.company.commands;

import com.company.Command;
import com.company.Redactor;

import java.util.logging.Logger;

public class Subtract implements Command {
    private static Logger log = Logger.getLogger(Subtract.class.getName());

    @Override
    public void execute(Redactor field) {
        log.fine("Subtract.execute()");
        int a = field.pop();
        int b = field.pop();
        field.push(b - a);
    }
}
