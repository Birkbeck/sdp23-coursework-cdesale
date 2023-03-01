package sml;

/**
 * An interface to help scan/parse an instruction line.
 *
 * @author Chaitali Desale
 */
public interface InstructionLineScanner {

    /**
     * Return the first word of the instruction line and remove it from line.
     *
     * @return a {@link String} representing the scanned word
     */
    String scan();
}
