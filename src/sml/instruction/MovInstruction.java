package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import sml.Registers.Register;

import java.util.Objects;

/**
 * A subclass of {@link Instruction} that handles the "mov" SML instruction.
 *
 * @author Chaitali Desale
 */
public class MovInstruction extends Instruction {
    private final RegisterName result;
    private final int value;

    /** Value of opcode for mov instruction. */
    public static final String OP_CODE = "mov";

    /** Constructor to help with Reflection API. */
    public MovInstruction(String label, String[] args) {
        super(label, OP_CODE);
        this.result = Register.valueOf(args[0]);
        this.value = Integer.parseInt(args[1]);
    }

    /** Execute method for mov instruction. */
    @Override
    public int execute(Machine m) {
        m.getRegisters().set(result, value);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovInstruction that = (MovInstruction) o;
        return value == that.value && Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, value);
    }
}
