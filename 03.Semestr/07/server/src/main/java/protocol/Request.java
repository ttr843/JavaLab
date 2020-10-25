package protocol;

import context.Component;
import helper.JsonReader;
import lombok.NoArgsConstructor;
import model.Command;

import java.util.Map;

@NoArgsConstructor
public class Request implements Component {

    private String request;

    private JsonReader jsonReader;

    public void setRequest(String request) {
        this.request = request;
    }

    public String getCommand() {
        jsonReader.setReq(request);
        return jsonReader.getCommand();
    }

    public String getParameter(String name) {
        jsonReader.setReq(request);
        Map<String, String> parameters = jsonReader.getParameters();
        return String.valueOf(parameters.get(name));
    }


}
