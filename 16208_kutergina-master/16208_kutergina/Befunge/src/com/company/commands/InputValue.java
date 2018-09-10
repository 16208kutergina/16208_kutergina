package com.company.commands;

import com.company.Command;
import com.company.Redactor;

import java.util.Scanner;
import java.util.logging.Logger;

public class InputValue implements Command {
    private static Logger log = Logger.getLogger(InputValue.class.getName());

    @Override
    public void execute(Redactor field) {
        log.fine("InputValue.execute()");
        Scanner sc = new Scanner(System.in);
        field.push(Integer.parseInt(sc.nextLine()));
    }
}
