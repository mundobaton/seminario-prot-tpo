package edu.uade.seminario.tpo.config.injectors;

import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.modelmapper.ModelMapper;

public class UtilsModule extends AbstractModule {

    @Override
    protected void configure() {

    }

    @Provides
    public Gson provideGson() {
        return new Gson();
    }

    @Provides
    public ModelMapper provideMapper() {
        return new ModelMapper();
    }
}
