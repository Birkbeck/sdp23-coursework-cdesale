package sml;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class that helps translate a set of SML instruction in text file into list of executable Java instructions.
 * <p>
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 *
 * @author Chaitali Desale
 */
public final class Translator {

    /** Source file for the SML code. */
    private final String fileName;

    /** Line contains the characters in the current line that's not been processed yet. */
    private String line = "";

    /** A factory to help with {@link Instruction} object instantiation. */
    private final InstructionFactory instructionFactory;

    public Translator(String fileName) {
        this.fileName =  fileName;
        this.instructionFactory = DaggerInstructionFactoryComponent.create().getInstructionFactory();
    }

    /**
     * Translate the small program in the file into lab (the labels) and prog (the program) return "no errors were
     * detected".
     */
    public void readAndTranslate(Labels labels, List<Instruction> program) throws IOException {
        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            labels.reset();
            program.clear();

            // Each iteration processes line and reads the next input line into "line"
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String label = getLabel();

                Instruction instruction = getInstruction(label);
                if (instruction != null) {
                    if (label != null)
                        labels.addLabel(label, program.size());
                    program.add(instruction);
                }
            }
        }
    }

    /**
     * Translates the current line into an instruction with the given label
     *
     * @param label the instruction label
     * @return the new instruction
     * <p>
     * The input line should consist of a single SML instruction,
     * with its label already removed.
     */
    private Instruction getInstruction(String label) {
        if (line.isEmpty())
            return null;

        String opcode = scan();
        String[] args = getInstructionArgs();
        return instructionFactory.getInstruction(opcode, label, args);
    }

    private String getLabel() {
        String word = scan();
        if (word.endsWith(":"))
            return word.substring(0, word.length() - 1);

        // undo scanning the word
        line = word + " " + line;
        return null;
    }

    /**
     * Return the first word of line and remove it from line.
     * If there is no word, return "".
     */
    private String scan() {
        line = line.trim();

        for (int i = 0; i <= line.length(); i++)
            if (i == line.length() || Character.isWhitespace(line.charAt(i))) {
                String word = line.substring(0, i);
                line = line.substring(i);
                return word;
            }

        return line;
    }

    /** Return the arguments that are to be passed to the {@link Instruction}. */
    private String[] getInstructionArgs() {
        List<String> args = new ArrayList<>();
        for (String arg = scan(); !arg.isEmpty(); arg = scan()) {
            args.add(arg);
        }

        return args.toArray(new String[0]);
    }
}