package com.company.commands;

import com.company.Command;
import com.company.Redactor;

import java.util.logging.Logger;

public class PopAsASCII implements Command {
    private static Logger log = Logger.getLogger(PopAsASCII.class.getName());
    @Override
    public void execute(Redactor field) {
        log.fine("PopAsASCII");
        int pop = field.pop();
        System.out.print((char) pop);
    }
}