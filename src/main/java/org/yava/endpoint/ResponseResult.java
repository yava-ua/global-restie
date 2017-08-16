package org.yava.endpoint;

import lombok.Data;

@Data
public class ResponseResult<E> {
    private boolean success;
    private E data;
    private Exception error;

    protected ResponseResult() {
    }

    ResponseResult( boolean success, E data, Exception error ) {
        this.success = success;
        this.data = data;
        this.error = error;
    }

    public static <E> ResponseResult<E> success( E data ) {
        return new ResponseResult<E>( true, data, null );
    }

    public static ResponseResult failure( Exception error ) {
        return new ResponseResult( false, null, error );
    }
}
