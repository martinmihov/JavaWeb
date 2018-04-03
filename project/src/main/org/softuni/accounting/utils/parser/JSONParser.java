package org.softuni.accounting.utils.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.softuni.accounting.utils.io.interfaces.FileIO;
import org.softuni.accounting.utils.parser.interfaces.FileParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component(value = "JSONParser")
public class JSONParser implements FileParser {

    private Gson gson;

    @Autowired
    private FileIO fileIO;

    public JSONParser() {
        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public <T> T read(Class<T> objectClass, String file) throws IOException {
        String jsonContent = this.fileIO.read(file);
        T object = this.gson.fromJson(jsonContent, objectClass);
        return object;
    }

    @Override
    public <T> void write(T object, String file) throws IOException {
        String jsonContent = this.gson.toJson(object);
        this.fileIO.write(jsonContent, file);
    }
}
