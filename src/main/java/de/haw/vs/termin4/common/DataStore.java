package de.haw.vs.termin4.common;

import java.util.NoSuchElementException;

public interface DataStore {
    void write(int index, String data);
    String read(int index) throws NoSuchElementException;
}
