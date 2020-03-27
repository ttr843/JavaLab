package helper;


import payload.GetProductsPayload;
import payload.JsonObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import handler.*;


public class CommandDispatcher {

    private String token;

    public CommandDispatcher(String token) {
        this.token = token;
    }

    public String process(String req) {
        if (req.contains("login")) {
            return new LoginHandler().process();
        } else if (req.contains("get messages")) {
            return new CommandHandler(token).process();
        }else if(req.contains("registration")) {
            return new RegistrationHandler().process();
        }
        else if (req.contains("add product")) {
            return new AddProductHandler(token).process();
        } else if(req.contains("get products")) {
            try {
                return  new JsonObject("command", new GetProductsPayload()).convertToJson();
            } catch (JsonProcessingException e) {
                throw new IllegalStateException(e);
            }
        } else if (req.contains("delete")) {
            return new DeleteProductHandler(token).process();
        } else if (req.contains("buy product")) {
            return new BuyProductHandler().process();
        }
        else {
            return new MessageHandler(token).process(req);
        }
    }
}
