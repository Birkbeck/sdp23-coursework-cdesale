package sml.instruction;

import static sml.Registers.Register.EAX;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

class OutInstructionTest {
    private static final String[] ARGS = new String[] {"EAX"};

    private Machine machine;
    private Registers registers;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() throws IOException {
        machine = null;
        registers = null;
        System.setOut(System.out);
        outContent.close();
    }

    @Test
    void executeValid() {
        registers.set(EAX, 5);
        Instruction instruction = new OutInstruction(null, ARGS);
        instruction.execute(machine);
        Assertions.assertEquals("5" + System.lineSeparator(), outContent.toString());
    }
}
