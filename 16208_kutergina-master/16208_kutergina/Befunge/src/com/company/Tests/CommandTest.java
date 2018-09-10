package com.company.Tests;

import com.company.Command;
import com.company.Field;
import com.company.Redactor;
import com.company.commands.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandTest {

    Redactor testField;
    Command command;

    @BeforeEach
    void init(){
        testField = new Field("src/com/company/Tests/TestsCommand/EmptyField.txt");
    }

    @AfterEach
    void clearCommand(){
        command = null;
        testField = null;
    }

    @Test
    void  AddTest() throws Exception {
        command = new Add();
        testField.push(3);
        testField.push(4);
        command.execute(testField);
        assertEquals(7, testField.pop());
    }

    @Test
    void BridgeTest() throws Exception {
        command = new Bridge();
        command.execute(testField);
        assertEquals(1,testField.getActualCoordX());
        assertEquals(0,testField.getActualCoordY());
    }

    @Test
    void DivideTest() throws Exception {
        command = new Divide();
        testField.push(10);
        testField.push(5);
        command.execute(testField);
        assertEquals(2, testField.pop());
        assertThrows(ArithmeticException.class,()->{command.execute(testField);});

    }

    @Test
    void DownTest() throws Exception {
        command = new Down();
        command.execute(testField);
        assertEquals(0,testField.getDirectionX());
        assertEquals(1,testField.getDirectionY());
    }

    @Test
    void DupTest() throws Exception {
        command = new Dup();
        testField.push(5);
        command.execute(testField);
        assertEquals(5,testField.pop());
        assertEquals(5,testField.pop());
    }

    @Test
    void EmptyTest() throws Exception {
        command = new Empty();
        command.execute(testField);
        assertEquals(testField, testField);
    }

    @Test
    void GetTest() throws Exception {
        command = new Get();
        testField.setElem(0,0, '&');
        command.execute(testField);
        assertEquals(38, testField.pop());
    }

    @Test
    void GreaterTest() throws Exception {
        command = new Greater();
        testField.push(1);
        testField.push(2);
        command.execute(testField);
        assertEquals(0,testField.pop());
        testField.push(2);
        testField.push(1);
        command.execute(testField);
        assertEquals(1,testField.pop());
    }

    @Test
    void Horizontal_ifTest() throws Exception {
        command = new Horizontal_If();
        testField.push(0);
        testField.push(1);
        testField.push(2);

        command.execute(testField);
        assertEquals(-1,testField.getDirectionX());
        assertEquals(0,testField.getDirectionY());

        command.execute(testField);
        assertEquals(-1,testField.getDirectionX());
        assertEquals(0,testField.getDirectionY());

        command.execute(testField);
        assertEquals(1,testField.getDirectionX());
        assertEquals(0,testField.getDirectionY());

    }

    @Test
    void InputChracterTest() throws Exception {
        command = new InputCharacter();
        try {
            InputStream input = new FileInputStream("src/com/company/Tests/TestsCommand/InputCharacter.txt");
            System.setIn(input);
            command.execute(testField);
            assertEquals(38, testField.pop());
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("\n File can't be open");
        }
    }

    @Test
    void InputValueTest() throws Exception {
        command = new InputValue();
        try {
            InputStream input = new FileInputStream("src/com/company/Tests/TestsCommand/InputValue.txt");
            System.setIn(input);
            command.execute(testField);
            assertEquals(5, testField.pop());
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("\n File can't be open");
        }
    }

    @Test
    void LeftTest() throws Exception {
        command = new Left();
        command.execute(testField);
        assertEquals(-1,testField.getDirectionX());
        assertEquals(0,testField.getDirectionY());
    }

    @Test
    void ModuloTest() throws Exception {
        command = new Modulo();
        testField.push(11);
        testField.push(5);
        command.execute(testField);
        assertEquals(1, testField.pop());
        assertThrows(ArithmeticException.class,()->{command.execute(testField);});
    }

    @Test
    void MultiplyTest() throws Exception {
        command = new Multiply();
        testField.push(2);
        testField.push(3);
        command.execute(testField);
        assertEquals(6, testField.pop());
    }

    @Test
    void NotTest() throws Exception {
        command = new Not();
        testField.push(1);
        testField.push(2);
        command.execute(testField);
        assertEquals(0,testField.pop());
        command.execute(testField);
        assertEquals(0,testField.pop());
        command.execute(testField);
        assertEquals(1, testField.pop());
    }

    @Test
    void PopAsASCIITest() throws Exception {
        command = new PopAsASCII();
        testField.push('&');
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
command.execute(testField);
        assertEquals("&", output.toString());
        output.close();
    }

        @Test
    void PopAsIntegerTest() throws Exception {
        command = new PopAsInteger();
            testField.push(5);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            System.setOut(new PrintStream(output));
            command.execute(testField);
            assertEquals("5", output.toString());
            output.close();
    }

    @Test
    void PopNothingTest() throws Exception {
        command = new PopNothing();
        testField.push(5);
        command.execute(testField);
        assertEquals(0, testField.pop());
    }

    @Test
    void PushTest() throws Exception {
        command = new Push();
        testField.setElem(0, 0, '2');
        command.execute(testField);
        assertEquals(2, testField.pop());
    }

    @Test
    void PutTest() throws Exception {
        command = new Put();
        testField.push(38);
        testField.push(0);
        testField.push(0);
        command.execute(testField);
        assertEquals('&',testField.getElem(0, 0));
    }

    @Test
    void RandomTest() throws Exception {
        command = new Random();
        testField.setDirection(0, 0);
        command.execute(testField);
        assertFalse(0 == testField.getDirectionX()+testField.getDirectionY());
    }

    @Test
    void RightTest() throws Exception {
        command = new Right();
        command.execute(testField);
        assertEquals(1,testField.getDirectionX());
        assertEquals(0,testField.getDirectionY());
    }

    @Test
    void StringmodeTest() throws Exception {
        command = new Stringmode();
        testField.setElem(0 ,0, '"');
        testField.setElem(1 ,0, '&');
        assertThrows(IllegalArgumentException.class,()->{command.execute(testField);});
        testField.setElem(2 ,0, '"');
        command.execute(testField);
        assertEquals('&',(char)testField.pop());

    }

    @Test
    void SubtractTest() throws Exception {
        command = new Subtract();
        testField.push(5);
        testField.push(4);
        command.execute(testField);
        assertEquals(1, testField.pop());
    }

    @Test
    void SwapTest() throws Exception {
        command = new Swap();
        testField.push(5);
        testField.push(4);
        command.execute(testField);
        assertEquals(5, testField.pop());
        assertEquals(4, testField.pop());
    }

    @Test
    void UpTest() throws Exception {
        command = new Up();
        command.execute(testField);
        assertEquals(0,testField.getDirectionX());
        assertEquals(-1,testField.getDirectionY());

    }

    @Test
    void Vertical_ifTest() throws Exception {
        command = new Vertical_If();
        testField.push(0);
        testField.push(1);
        testField.push(2);

        command.execute(testField);
        assertEquals(0,testField.getDirectionX());
        assertEquals(-1,testField.getDirectionY());

        command.execute(testField);
        assertEquals(0,testField.getDirectionX());
        assertEquals(-1,testField.getDirectionY());

        command.execute(testField);
        assertEquals(0,testField.getDirectionX());
        assertEquals(1,testField.getDirectionY());

    }
}
