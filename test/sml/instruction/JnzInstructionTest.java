package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.EAX;

public class JnzInstructionTest {
    private static final String TEST_LABEL = "test-label";

    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();

        // Add a fake instruction in the machine program for the jnz instruction, that is under test, to point to.
        machine.getProgram().add(new JnzInstruction(TEST_LABEL, EAX, TEST_LABEL));
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }

    @Test
    void executeValid_shouldReturnInstructionIndexZero() {
        registers.set(EAX, 1);
        Instruction instruction = new JnzInstruction(null, EAX, TEST_LABEL);
        Assertions.assertEquals(0, instruction.execute(machine));
    }

    @Test
    void executeValid_shouldReturnNormalProgramCounter() {
        registers.set(EAX, 0);
        Instruction instruction = new JnzInstruction(null, EAX, TEST_LABEL);
        Assertions.assertEquals(Instruction.NORMAL_PROGRAM_COUNTER_UPDATE, instruction.execute(machine));
    }
}
