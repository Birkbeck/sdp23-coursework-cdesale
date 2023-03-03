package sml.instruction;

import static sml.Registers.Register.EAX;
import static sml.Registers.Register.EBX;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

class MulInstructionTest {
    private static final String[] ARGS = new String[] {"EAX", "EBX"};

    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }

    @Test
    void executeValid() {
        registers.set(EAX, 6);
        registers.set(EBX, 2);
        Instruction instruction = new MulInstruction(null, ARGS);
        instruction.execute(machine);
        Assertions.assertEquals(12, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, 10);
        registers.set(EBX, 2);
        Instruction instruction = new MulInstruction(null, ARGS);
        instruction.execute(machine);
        Assertions.assertEquals(20, machine.getRegisters().get(EAX));
    }
}
