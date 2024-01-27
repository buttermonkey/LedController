package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the actual logic
 */
public class LedControllerImpl implements LedController {
    private final ApiService apiService;

    public LedControllerImpl(ApiService apiService)
    {
        this.apiService = apiService;
    }

    @Override
    public void demo() throws IOException
    {
        // Call `getLights`, the response is a json object in the form `{ "lights": [ { ... }, { ... } ] }`
        JSONObject response = apiService.getLights();
        // get the "lights" array from the response
        JSONArray lights = response.getJSONArray("lights");
        // read the first json object of the lights array
        JSONObject firstLight = lights.getJSONObject(0);
        // read int and string properties of the light
        System.out.println("First light id is: " + firstLight.getInt("id"));
        System.out.println("First light color is: " + firstLight.getString("color"));
    }

    @Override
    public LedStatus[] getGroupLeds() throws IOException {



        // Call `getLights`, the response is a json object in the form `{ "lights": [ { ... }, { ... } ] }`
        JSONObject response = apiService.getLights();
        // get the "lights" array from the response
        JSONArray lights = response.getJSONArray("lights");

        int laenge = lights.length();

        List<LedStatus> ledList = new ArrayList<>();
        // read the lines from json object of the lights array
        for (int i = 0; i < laenge ; i++) {
            JSONObject light = lights.getJSONObject(i);
            LedStatus led = toLedStatus(light);
            if ("G".equals(led.groupName)) {
                ledList.add(led);
            }
        }

        LedStatus[] ledStatuses = new LedStatus[ledList.size()];
        return ledList.toArray(ledStatuses);
    }

    private static LedStatus toLedStatus(JSONObject light) {
        LedStatus led = new LedStatus();
        led.id = light.getInt("id");
        led.color = light.getString("color");
        led.on = light.getBoolean("on");
        JSONObject groupByGroup = light.getJSONObject("groupByGroup");
        led.groupName = groupByGroup.getString("name");
        return led;
    }

    @Override
    public void reportGroupStatus() throws IOException {
        LedStatus[] groupLeds = getGroupLeds();

        for (var led : groupLeds) {
            System.out.println(led);
        }
    }

    @Override
    public void reportLedStatus(BufferedReader reader) throws IOException {
        System.out.println("Please specify LED ID:");
        String input = reader.readLine();
        int ledId = Integer.parseInt(input);

        JSONObject ledJson = apiService.getLight(ledId);
        // get the "lights" array from the response
        JSONArray lights = ledJson.getJSONArray("lights");
        // read the first json object of the lights array
        JSONObject firstLight = lights.getJSONObject(0);
        LedStatus led = toLedStatus(firstLight);
        System.out.println(led);
    }
}
