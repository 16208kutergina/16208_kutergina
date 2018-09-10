package com.company.commands;

import com.company.Command;
import com.company.Redactor;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Modulo implements Command {
    private static Logger log = Logger.getLogger(Modulo.class.getName());

    @Override
    public void execute(Redactor field) {
        int a = field.pop();
        if(a == 0) {
            log.log(Level.SEVERE, "Divide to 0");
            throw new ArithmeticException();
        }
        log.fine("Modulo.execute()");
        int b = field.pop();
        field.push(b%a);
    }
}
