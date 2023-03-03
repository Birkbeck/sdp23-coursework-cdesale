package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import sml.Registers.Register;

import java.util.Objects;

/**
 * A subclass of {@link Instruction} that handles the "jnz" SML instruction.
 *
 * @author Chaitali Desale
 */
public class JnzInstruction extends Instruction {
    private final RegisterName source;
    private final String nextInstructionLabel;

    /** Value of opcode for jnz instruction. */
    public static final String OP_CODE = "jnz";

    /** Constructor to help with Reflection API. */
    public JnzInstruction(String label, String[] args) {
        super(label, OP_CODE);
        this.source = Register.valueOf(args[0]);
        this.nextInstructionLabel = args[1];
    }

    /** Execute method for jnz instruction. */
    @Override
    public int execute(Machine m) {
        int sourceValue = m.getRegisters().get(source);
        if (sourceValue == 0) {
            return NORMAL_PROGRAM_COUNTER_UPDATE;
        }

        // Get and return the address of an instruction that matches the next instruction label.
        return m.getLabels().getAddress(nextInstructionLabel);
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + source + " " + nextInstructionLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JnzInstruction that = (JnzInstruction) o;
        return Objects.equals(source, that.source) && Objects.equals(nextInstructionLabel, that.nextInstructionLabel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, nextInstructionLabel);
    }
}
