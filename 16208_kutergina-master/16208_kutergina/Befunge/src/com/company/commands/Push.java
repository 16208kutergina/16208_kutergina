package com.company.commands;

import com.company.Command;
import com.company.Redactor;

import java.util.logging.Logger;

public class Push implements Command {
    private static Logger log = Logger.getLogger(Push.class.getName());

    @Override
    public void execute(Redactor field) {
        int t = Character.getNumericValue(field.getElem(field.getActualCoordX(), field.getActualCoordY()));
        field.push(t);
        log.fine("Push.execute():" + t);
    }
}
