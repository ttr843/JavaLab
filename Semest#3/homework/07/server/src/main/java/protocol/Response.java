package protocol;

// объект, который относится к протоколу

import context.Component;
import lombok.NoArgsConstructor;

// например, HttpServletResponse
@NoArgsConstructor
public class Response<T> implements Component {
    private T data;

    public T getData() {
        return data;
    }

    private Response(T data) {
        this.data = data;
    }

    static <E> Response<E> build(E data) {

        return new Response<>(data);
    }
    
    public String toJson() {
        return null;
    }

}
