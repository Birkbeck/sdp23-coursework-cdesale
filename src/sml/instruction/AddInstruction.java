package sml.instruction;

import sml.Instruction;
import sml.InstructionLineScanner;
import sml.Machine;
import sml.RegisterName;
import sml.Registers.Register;

import java.util.Objects;

/**
 * A subclass of {@link Instruction} that handles the "add" SML instruction.
 *
 * @author Chaitali Desale
 */
public class AddInstruction extends Instruction {
	private final RegisterName result;
	private final RegisterName source;

	/** Value of opcode for addition instruction. */
	public static final String OP_CODE = "add";

	/** Constructor to help with Reflection API. */
	public AddInstruction(String label, InstructionLineScanner instructionLineScanner) {
		this(label, Register.valueOf(instructionLineScanner.scan()), Register.valueOf(instructionLineScanner.scan()));
	}

	public AddInstruction(String label, RegisterName result, RegisterName source) {
		super(label, OP_CODE);
		this.result = result;
		this.source = source;
	}

	/** Execute method for addition instruction. */
	@Override
	public int execute(Machine m) {
		int value1 = m.getRegisters().get(result);
		int value2 = m.getRegisters().get(source);
		m.getRegisters().set(result, value1 + value2);
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
		AddInstruction that = (AddInstruction) o;
		return Objects.equals(result, that.result) && Objects.equals(source, that.source);
	}

	@Override
	public int hashCode() {
		return Objects.hash(result, source);
	}
}
