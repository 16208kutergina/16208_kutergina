package com.company.commands;

import com.company.Command;
import com.company.Redactor;

import java.util.logging.Logger;

public class Greater implements Command {
    private static Logger log = Logger.getLogger(Greater.class.getName());

    @Override
    public void execute(Redactor field) {
        log.fine("Greater.execute()");
        int a = field.pop();
        int b = field.pop();
        if(b > a){
         field.push(1);
    }else{
            field.push(0);
        }
    }
}
