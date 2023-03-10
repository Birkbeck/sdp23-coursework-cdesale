package sml;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A class to represent and maintain the concept of register for our SML language.
 *
 * @author Chaitali Desale
 */
public final class Registers {
    private final Map<Register, Integer> registers = new HashMap<>();

    public enum Register implements RegisterName {
        EAX, EBX, ECX, EDX, ESP, EBP, ESI, EDI;
    }

    public Registers() {
        clear(); // the class is final
    }

    public void clear() {
        for (Register register : Register.values())
            registers.put(register, 0);
    }

    /**
     * Sets the given register to the value.
     *
     * @param register register name
     * @param value new value
     */
    public void set(RegisterName register, int value) {
        registers.put((Register)register, value);
    }

    /**
     * Returns the value stored in the register.
     *
     * @param register register name
     * @return value
     */
    public int get(RegisterName register) {
        return registers.get((Register)register);
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof Registers other) {
            if (registers.keySet().size() == other.registers.size()) {
                return registers.keySet().stream()
                        .noneMatch(key -> !other.registers.containsKey(key)
                                || !registers.get(key).equals(other.registers.get(key)));
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        return registers.hashCode();
    }

    @Override
    public String toString() {
        return registers.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> e.getKey() + " = " + e.getValue())
                .collect(Collectors.joining(", ", "[", "]")) ;
    }
}
