package sml.instruction;

import dagger.Binds;
import dagger.Module;
import sml.InstructionFactory;

@Module
public abstract class InstructionModule {

    @Binds
    public abstract InstructionFactory providesInstructionFactory(InstructionFactoryImpl instructionFactoryImpl);
}
