package com.company.commands;

import com.company.Command;
import com.company.Redactor;

import java.util.logging.Logger;

public class Dup implements Command {
    private static Logger log = Logger.getLogger(Dup.class.getName());

    @Override
    public void execute(Redactor field) {
        log.fine("Dup.execute()");
            int top = field.pop();
            field.push(top);
            field.push(top);
    }
}