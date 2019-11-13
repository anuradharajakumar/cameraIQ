package test.cameriq.interview.Response;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import test.cameriq.interview.Exception.ElementNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public class ResultSet<T> {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private T data;

    private ResultSet()
    {
        this.timestamp = LocalDateTime.now();
    }

    public ResultSet( T o, HttpStatus status ) throws ElementNotFoundException
    {
        this();
        if ( o == null || ( o instanceof List && ( (List) o ).isEmpty() ) )
            throw new ElementNotFoundException( "No Content Found" );
        this.status = status;
        this.data = o;
    }


    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
