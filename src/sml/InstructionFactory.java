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
     * @param label the {@link String} label associated with the
     * @param args the arguments that are passed to the instruction
     * @return the associated {@link Instruction}
     */
    Instruction getInstruction(String opcode, String label, String[] args);
}
