package org.softuni.accounting.utils.parser.interfaces;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface FileParser {

    <T> T read(Class<T> objectClass, String file) throws IOException, JAXBException;

    <T> void write(T object, String file) throws IOException, JAXBException;
}
