package com.company.commands;

import com.company.Command;
import com.company.Redactor;

import java.util.logging.Logger;


public class Add implements Command {
    private static Logger log = Logger.getLogger(Add.class.getName());

    @Override
    public void execute(Redactor field) {
        log.fine("Add.execute()");
        field.push(field.pop()+ field.pop());
    }
}
