package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import sml.Registers.Register;

import java.util.Objects;

/**
 * A subclass of {@link Instruction} that handles the "mul" SML instruction.
 *
 * @author Chaitali Desale
 */
public class MulInstruction extends Instruction {
    private final RegisterName result;
    private final RegisterName source;

    /** Value of opcode for mul instruction. */
    public static final String OP_CODE = "mul";

    /** Constructor to help with Reflection API. */
    public MulInstruction(String label, String[] args) {
        super(label, OP_CODE);
        this.result = Register.valueOf(args[0]);
        this.source = Register.valueOf(args[1]);
    }

    /** Execute method for multiplication instruction. */
    @Override
    public int execute(Machine m) {
        int value1 = m.getRegisters().get(result);
        int value2 = m.getRegisters().get(source);
        m.getRegisters().set(result, value1 * value2);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MulInstruction that = (MulInstruction) o;
        return Objects.equals(result, that.result) && Objects.equals(source, that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, source);
    }
}
