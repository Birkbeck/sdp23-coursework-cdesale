package sml.instruction;

import sml.Instruction;
import sml.InstructionLineScanner;
import sml.Machine;
import sml.RegisterName;
import sml.Registers.Register;

import java.util.Objects;
import java.util.stream.IntStream;

/**
 * A subclass of {@link Instruction} that handles the "jnz" SML instruction.
 *
 * @author Chaitali Desale
 */
public class JnzInstruction extends Instruction {
    private final RegisterName source;
    private final String nextInstructionLabel;

    public static final String OP_CODE = "jnz";

    public JnzInstruction(String label, InstructionLineScanner instructionLineScanner) {
        super(label, OP_CODE);

        this.source = Register.valueOf(instructionLineScanner.scan());
        this.nextInstructionLabel = instructionLineScanner.scan();
    }

    @Override
    public int execute(Machine m) {
        int sourceValue = m.getRegisters().get(source);
        if (sourceValue == 0) {
            return NORMAL_PROGRAM_COUNTER_UPDATE;
        }

        return IntStream.range(0, m.getProgram().size())
                .filter(index -> nextInstructionLabel.equals(m.getProgram().get(index).getLabel()))
                .findFirst()
                .orElseGet(() -> NORMAL_PROGRAM_COUNTER_UPDATE);
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
