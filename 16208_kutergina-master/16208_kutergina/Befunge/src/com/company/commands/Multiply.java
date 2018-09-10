package com.company.commands;

import com.company.Command;
import com.company.Redactor;

import java.util.logging.Logger;

public class Multiply implements Command {
    private static Logger log = Logger.getLogger(Multiply.class.getName());

    @Override
    public void execute(Redactor field) {
        log.fine("Multiply.execute()");
        field.push(field.pop() * field.pop());
    }
}
