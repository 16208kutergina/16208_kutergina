package com.company.commands;

import com.company.Command;
import com.company.Redactor;

import java.util.logging.Logger;

public class PopAsInteger implements Command {
    private static Logger log = Logger.getLogger(PopAsInteger.class.getName());

    @Override
    public void execute(Redactor field) {
       log.fine("PopASInteger.execute()");
       System.out.print(field.pop());
    }
}