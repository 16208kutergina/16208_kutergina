package com.company.commands;

import com.company.Command;
import com.company.Redactor;

import java.util.logging.Logger;

public class Up implements Command {
    private static Logger log = Logger.getLogger(Up.class.getName());

    @Override
    public void execute(Redactor field) {
        log.fine("Up.execute()");
        field.setDirection(0, -1);
    }
}
