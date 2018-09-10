package com.company.commands;

import com.company.Command;
import com.company.Redactor;

import java.util.logging.Logger;

public class Swap implements Command {
    private static Logger log = Logger.getLogger(Swap.class.getName());

    @Override
    public void execute(Redactor field) {
        log.fine("Swap.execute()");
        int a = field.pop();
        int b = field.pop();
        field.push(a);
        field.push(b);
    }
}
