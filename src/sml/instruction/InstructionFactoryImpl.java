package sml.instruction;

import sml.Instruction;
import sml.InstructionFactory;
import sml.InstructionLineScanner;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * A factory class to help initialize {@link sml.Instruction} subclasses using reflection.
 *
 * @author Chaitali Desale
 */
public final class InstructionFactoryImpl implements InstructionFactory {

    /** Name of the properties file that stores all the opcode -> class name mapping. */
    private static final String PROPERTIES_FILE_NAME = "resources/beans.properties";

    private final Properties properties;

    public InstructionFactoryImpl() {
        this.properties = new Properties();
        initProperties();
    }

    @Override
    public Instruction getInstruction(String opcode, InstructionLineScanner instructionLineScanner, String label) {
        Class<?> clazz = getClassForOpcode(opcode);
        return getInitialisedObject(clazz, instructionLineScanner, label);
    }

    /**
     * Reads the opcode -> class name key:value pairs from the {@link #PROPERTIES_FILE_NAME} {@link java.io.File} and
     * loads them into {@link #properties}.
     */
    private void initProperties() {
        try (var fis = new FileInputStream(PROPERTIES_FILE_NAME)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Properties file not found", e);
        }
    }

    /** Returns the class corresponding the supplied {@code opcode}. This is a method for helping with reflection. */
    private Class<?> getClassForOpcode(String opcode) {
        String className = properties.getProperty(opcode);
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Class not found for property with key " + opcode, e);
        }
    }

    /** Returns an initialised object of the supplied {@code clazz}. This is a method for helping with reflection. */
    private <T extends Instruction> T getInitialisedObject(Class<?> clazz,
                                                           InstructionLineScanner instructionLineScanner,
                                                           String label) {
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor(String.class, InstructionLineScanner.class);
            return (T) constructor.newInstance(label, instructionLineScanner);
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException
                 | NoSuchMethodException e) {
            throw new RuntimeException("Error initialising an Instruction subclass object using reflection", e);
        }
    }
}
