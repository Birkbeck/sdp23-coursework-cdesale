package sml;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents a collection of identifiers that can be used to identify {@link Instruction}s.
 *
 * @author Chaitali Desale
 */
public final class Labels {
	private final Map<String, Integer> labels = new HashMap<>();

	/**
	 * Adds a label with the associated address to the map.
	 *
	 * @param label the label
	 * @param address the address the label refers to
	 */
	public void addLabel(String label, int address) {
		Objects.requireNonNull(label);

		if (labels.containsKey(label)) {
			throw new RuntimeException("A label with name " + label + " already exists");
		}

		labels.put(label, address);
	}

	/**
	 * Returns the address associated with the label.
	 *
	 * @param label the label
	 * @return the address the label refers to
	 */
	public int getAddress(String label) {
		// Where can NullPointerException be thrown here?
		// A NullPointerException can be thrown here is a label doesn't exist for any instruction. One way we can handle
		// this is by returning the default value of program counter update.
		return labels.getOrDefault(label, Instruction.NORMAL_PROGRAM_COUNTER_UPDATE);
	}

	/**
	 * Representation of this instance,
	 * in the form "[label -> address, label -> address, ..., label -> address]"
	 *
	 * @return the string representation of the labels map
	 */
	@Override
	public String toString() {
		return labels.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
				.map(e -> e.getKey() + " -> " + e.getValue())
				.collect(Collectors.joining(", ", "[", "]")) ;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Labels other = (Labels) o;
		if (labels.keySet().size() == other.labels.size()) {
			return labels.keySet().stream()
					.noneMatch(key -> !other.labels.containsKey(key)
							|| !labels.get(key).equals(other.labels.get(key)));
		}

		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(labels);
	}

	/**
	 * Removes the labels
	 */
	public void reset() {
		labels.clear();
	}
}
