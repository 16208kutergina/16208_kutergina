package com.company.commands;

import com.company.Command;
import com.company.Redactor;

import java.util.Scanner;
import java.util.logging.Logger;

public class InputCharacter implements Command {
    private static Logger log = Logger.getLogger(InputCharacter.class.getName());

    @Override
    public void execute(Redactor field) {
        log.fine("InputCharacter.execute()");
        Scanner sc = new Scanner(System.in);
    field.push(sc.nextLine().charAt(0));
    }
}
