package dispatcher;


import Dto.*;
import context.Component;
import handler.*;
import helper.JWTUtils;
import lombok.NoArgsConstructor;
import model.GetMessagesCommand;
import model.Message;
import model.Product;
import payload.*;
import protocol.Request;
import repository.ProductRepository;
import repository.UserRepository;

import java.util.Date;

@NoArgsConstructor
public class RequestsDispatcher implements Component {
    private UserDto userDto;
    private LoginHandler loginHandler;
    private MessageHandler messageHandler;
    private RegistrationHandler registrationHandler;
    private GetMessagesCommandHandler messagesHandler;
    private GetProductsCommandHandler productsHandler;
    private BuyProductHandler buyProductHandler;
    private String jsonMessage;
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private AddProductHandler addProductHandler;
    private DeleteProductHandler deleteProductHandler;
    private UserJwtDto user;
    private JWTUtils jwtUtils;
    public Dto doDispatch(Request request) {
        String command = request.getCommand();
        if (command.equals("login")) {
            user = loginHandler.handle(new LoginPayload(request.getParameter("login"), request.getParameter("password")));
            return user;
        } else if (command.equals("registration")) {
            user = registrationHandler.handle(new RegistrationPayload(request.getParameter("login"),
                    request.getParameter("password"), request.getParameter("role")));
            return user;
        } else if (user == null) {
            return new ErrorDto();
        } else if (command.equals("message")) {
            Message message = new Message(UserDto.from(userRepository.findByID(user.getId())),
                    request.getParameter("message"),
                    new Date(System.currentTimeMillis()));
            return messageHandler.handle(new MessagePayload(message));
        } else if (command.equals("get messages")) {
            GetMessagesCommand getMessagesCommand = new GetMessagesCommand(Integer.parseInt(request.getParameter("page")),
                    Integer.parseInt(request.getParameter("size")));
            return messagesHandler.handle(new GetMessagesCommandPayload(getMessagesCommand));
        } else if (command.equals("get products")) {
            return productsHandler.handle();
        } else if (command.equals("add product")) {
            if (jwtUtils.get(request.getParameter("token")).getRole().equals("admin")) {
                addProductHandler.handle(new Product(0, request.getParameter("product").split(",")[0].split("=")[1],
                        Integer.parseInt(request.getParameter("product").split(",")[1].split("=")[1])));
            }
        } else if (command.equals("buy product")) {
            Product product = productRepository.findByID(Integer.parseInt(request.getParameter("id")));
            buyProductHandler.handle(product);
        } else if (command.equals("delete product")) {
            if (jwtUtils.get(request.getParameter("token")).getRole().equals("admin")) {
                deleteProductHandler.handle(Integer.parseInt(request.getParameter("id")));
            }
        }
        else throw new IllegalArgumentException();
        return null;
    }
}
