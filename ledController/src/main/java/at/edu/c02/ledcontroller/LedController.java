package at.edu.c02.ledcontroller;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;

public interface LedController {
    void demo() throws IOException;

     LedStatus[] getGroupLeds() throws IOException;


}
