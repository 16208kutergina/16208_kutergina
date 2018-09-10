package com.company.commands;

import com.company.Command;
import com.company.Redactor;

import java.util.logging.Logger;

public class Put implements Command {
    private static Logger log = Logger.getLogger(Put.class.getName());


    @Override
    public void execute(Redactor field) {
        log.fine("Put.execute()");
        int y = field.pop();
        int x = field.pop();
        field.setElem(x, y, (char) field.pop());
    }
}
