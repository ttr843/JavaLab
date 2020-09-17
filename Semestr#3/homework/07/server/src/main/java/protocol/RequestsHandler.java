package protocol;


import Dto.Dto;
import context.Component;
import dispatcher.RequestsDispatcher;
import lombok.NoArgsConstructor;

// обрабатывает запрос на уровне протокола
@NoArgsConstructor
public class RequestsHandler implements Component {

    private RequestsDispatcher dispatcher;

    // метод-реакция на запрос
    public Response<?> handleRequest(Request request) {

        return Response.build(dispatcher.doDispatch(request));
    }


}
