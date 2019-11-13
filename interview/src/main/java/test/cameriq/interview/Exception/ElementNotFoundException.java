package test.cameriq.interview.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ElementNotFoundException extends RuntimeException{

    public ElementNotFoundException(String msg) {
        super("Element not found Exception!! " + msg);
    }

    public ElementNotFoundException() {
        super();
    }

    public ElementNotFoundException( String message, Throwable cause )
    {
        super( message, cause );
    }


}
