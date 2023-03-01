package sml;

/**
 * A factory to help initialise {@link Instruction} subclass objects.
 *
 * @author Chaitali Desale
 */
public interface InstructionFactory {

    /**
     * Returns an initialised object on {@link Instruction} subclass that handles the supplied {@code opcode}.
     *
     * @param opcode the {@link String} operational code for which the {@link Instruction} is associated
     * @param instructionLineScanner an {@link InstructionLineScanner} to use for parsing the instruction line
     * @param label the {@link String} label associated with the
     * @return the associated {@link Instruction}
     */
    Instruction getInstruction(String opcode, InstructionLineScanner instructionLineScanner, String label);
}
