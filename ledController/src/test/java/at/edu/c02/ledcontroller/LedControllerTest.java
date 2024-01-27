package at.edu.c02.ledcontroller;

import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;


public class LedControllerTest {
    /**
     * This test is just here to check if tests get executed. Feel free to delete it when adding your own tests.
     * Take a look at the stack calculator tests again if you are unsure where to start.
     */
    @Test
    public void dummyTest() {
        assertEquals(1, 1);
    }

    @Test
    public void getGroupLedsRequestsStatusOfAllLights() throws IOException {
        ApiService service = mock(ApiService.class);
        when(service.getLights()).thenReturn(new JSONObject("{\"lights\":[{\"on\":false,\"id\":5,\"color\":\"#fff\",\"groupByGroup\":{\"name\":\"F\"}}]}"));

        LedController controller = new LedControllerImpl(service);
        controller.getGroupLeds();

        verify(service).getLights();
        verifyNoMoreInteractions(service);
    }

    @Test
    public void getGroupLedsOnlyReturnsLedsOfGroupG() throws IOException {
        ApiService service = mock(ApiService.class);
        when(service.getLights()).thenReturn(new JSONObject("{\"lights\":[{\"on\":false,\"id\":5,\"color\":\"#fff\",\"groupByGroup\":{\"name\":\"F\"}},{\"on\":false,\"id\":6,\"color\":\"#fff\",\"groupByGroup\":{\"name\":\"G\"}}]}"));

        LedController controller = new LedControllerImpl(service);
        LedStatus[] groupLeds = controller.getGroupLeds();

        assertEquals(1, groupLeds.length);
    }
}
