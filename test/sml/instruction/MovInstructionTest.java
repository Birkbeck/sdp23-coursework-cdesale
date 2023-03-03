package sml.instruction;

import static sml.Registers.Register.EAX;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

public class MovInstructionTest {
    private static final String[] ARGS = new String[] {"EAX", "5"};

    private Machine machine;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
    }

    @AfterEach
    void tearDown() {
        machine = null;
    }

    @Test
    void executeValid() {
        Instruction instruction = new MovInstruction(null, ARGS);
        instruction.execute(machine);
        Assertions.assertEquals(5, machine.getRegisters().get(EAX));
    }
}
