package com.company.Tests;

import static org.junit.jupiter.api.Assertions.*;

import com.company.Command;
import com.company.Factory;
import com.company.commands.Add;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.transform.Result;
import java.lang.reflect.Field;


public class FactoryTests {
    private Command command;

    @BeforeEach
    public void init(){
    command = new Add();
    }

    @AfterEach
    public void delCommand(){
        command = null;
    }

   @Test
    public void getTest(){
        assertEquals(command.getClass(), Factory.instance().get("+").getClass());
       assertEquals(null, Factory.instance().get("!@"));
    }

}
