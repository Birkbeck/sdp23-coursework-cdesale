package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * A subclass of {@link Instruction} that handles the "mov" SML instruction.
 *
 * @author Chaitali Desale
 */
public class MovInstruction extends Instruction {
    private final RegisterName result;
    private final int value;

    public static final String OP_CODE = "mov";

    public MovInstruction(String label, RegisterName result, int value) {
        super(label, OP_CODE);
        this.result = result;
        this.value = value;
    }

    @Override
    public int execute(Machine m) {
        int value = m.getRegisters().get(result);
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