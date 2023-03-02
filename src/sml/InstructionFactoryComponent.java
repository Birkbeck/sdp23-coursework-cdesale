package sml;

import dagger.Component;
import sml.instruction.InstructionModule;

@Component(modules = InstructionModule.class)
public interface InstructionFactoryComponent {
    InstructionFactory getInstructionFactory();
}
