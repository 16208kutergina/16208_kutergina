package com.company.commands;

import com.company.Command;
import com.company.Redactor;

import java.util.logging.Logger;

public class Get implements Command {
    private static Logger log = Logger.getLogger(Get.class.getName());

    @Override
    public void execute(Redactor field) {
        log.fine("Get.execute()");
        int y = field.pop();
        int x = field.pop();
        field.push(field.getElem(x, y));
    }
}
