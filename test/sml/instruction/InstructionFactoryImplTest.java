package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.InstructionFactory;

class InstructionFactoryImplTest {
    private static final String[] ARGS_FOR_MATH_INSTRUCTIONS = new String[] {"EAX", "EBX"};
    private static final String[] ARGS_FOR_JNZ_INSTRUCTIONS = new String[] {"EAX", "test-label"};
    private static final String[] ARGS_FOR_MOV_INSTRUCTIONS = new String[] {"EAX", "5"};
    private static final String[] ARGS_FOR_OUT_INSTRUCTIONS = new String[] {"EAX"};

    private InstructionFactory instructionFactory;

    @BeforeEach
    void setUp() {
        instructionFactory = new InstructionFactoryImpl();
    }

    @AfterEach
    void tearDown() {
        instructionFactory = null;
    }

    @Test
    void addOpcode_returnsAddInstruction() {
        Instruction addInstruction = instructionFactory.getInstruction("add", null, ARGS_FOR_MATH_INSTRUCTIONS);
        Assertions.assertTrue(addInstruction instanceof AddInstruction);
    }

    @Test
    void divOpcode_returnsDivInstruction() {
        Instruction divInstruction = instructionFactory.getInstruction("div", null, ARGS_FOR_MATH_INSTRUCTIONS);
        Assertions.assertTrue(divInstruction instanceof DivInstruction);
    }

    @Test
    void jnzOpcode_returnsJnzInstruction() {
        Instruction jnzInstruction = instructionFactory.getInstruction("jnz", null, ARGS_FOR_JNZ_INSTRUCTIONS);
        Assertions.assertTrue(jnzInstruction instanceof JnzInstruction);
    }

    @Test
    void movOpcode_returnsMovInstruction() {
        Instruction movInstruction = instructionFactory.getInstruction("mov", null, ARGS_FOR_MOV_INSTRUCTIONS);
        Assertions.assertTrue(movInstruction instanceof MovInstruction);
    }

    @Test
    void mulOpcode_returnsMulInstruction() {
        Instruction mulInstruction = instructionFactory.getInstruction("mul", null, ARGS_FOR_MATH_INSTRUCTIONS);
        Assertions.assertTrue(mulInstruction instanceof MulInstruction);
    }

    @Test
    void outOpcode_returnsOutInstruction() {
        Instruction outInstruction = instructionFactory.getInstruction("out", null, ARGS_FOR_OUT_INSTRUCTIONS);
        Assertions.assertTrue(outInstruction instanceof OutInstruction);
    }

    @Test
    void subOpcode_returnsSubInstruction() {
        Instruction subInstruction = instructionFactory.getInstruction("sub", null, ARGS_FOR_MATH_INSTRUCTIONS);
        Assertions.assertTrue(subInstruction instanceof SubInstruction);
    }
}
