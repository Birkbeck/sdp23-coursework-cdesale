package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import sml.Registers.Register;

import java.util.Objects;

/**
 * A subclass of {@link Instruction} that handles the "out" SML instruction.
 *
 * @author Chaitali Desale
 */
public class OutInstruction extends Instruction {
    private final RegisterName source;

    /** Value of opcode for out instruction. */
    public static final String OP_CODE = "out";

    /** Constructor to help with Reflection API. */
    public OutInstruction(String label, String[] args) {
        super(label, OP_CODE);
        this.source = Register.valueOf(args[0]);
    }

    /** Execute method for out instruction. */
    @Override
    public int execute(Machine m) {
        int value2 = m.getRegisters().get(source);
        System.out.println(value2);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutInstruction that = (OutInstruction) o;
        return Objects.equals(source, that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source);
    }
}
