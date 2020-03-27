package payload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Command;

public class CommandPayload implements PayLoad{
    private Command command;

    public Command getCommand() {
        return command;
    }

    public CommandPayload(Command command) {
        this.command = command;
    }

    @Override
    public String convertToJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(new CommandPayload(command));
    }

}
