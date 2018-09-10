package com.company.commands;

import com.company.Command;
import com.company.Redactor;

import java.util.logging.Logger;

public class Empty implements Command {
    private static Logger log = Logger.getLogger(Empty.class.getName());
    @Override
    public void execute(Redactor field) {
        log.fine("Empty.execute()");
    }
}
