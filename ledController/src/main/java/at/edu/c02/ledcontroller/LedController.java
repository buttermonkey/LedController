package at.edu.c02.ledcontroller;

import java.io.BufferedReader;
import java.io.IOException;

public interface LedController {
    void demo() throws IOException;

     LedStatus[] getGroupLeds() throws IOException;


    void reportGroupStatus() throws IOException;

    void reportLedStatus(BufferedReader reader) throws IOException;
}
