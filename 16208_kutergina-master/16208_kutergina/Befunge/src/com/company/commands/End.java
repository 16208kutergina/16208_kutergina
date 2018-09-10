package com.company.commands;

import com.company.Command;
import com.company.Redactor;

import java.util.logging.Logger;

import static java.lang.System.exit;

public class End implements Command {
    private static Logger log = Logger.getLogger(End.class.getName());

    @Override
    public void execute(Redactor field) {
        log.fine("End.execute()");
        exit(0);
    }
}
