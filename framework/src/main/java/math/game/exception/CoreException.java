package math.game.exception;

public class CoreException extends RuntimeException {
    public static final int NOT_ENOUGH_BALANCE = 1;
    public static final int INVALID_ACTION = 2;
    public static final int INCORECT_BET = 3;
    public static final int INCORECT_PICK_INDEX = 4;
    public static final int UNKNOWN_EXCEPTION = 5;

    private int exceptionCode;

    public CoreException() {
        super();
        exceptionCode = UNKNOWN_EXCEPTION;
    }

    public CoreException(int code) {
        super();
        exceptionCode = code;
    }

    public CoreException(int code, Throwable cause) {
        super(cause);
        exceptionCode = code;
    }

    public int getExceptionCode(){
        return exceptionCode;
    }

    @Override
    public String getMessage() {
        return String.valueOf(exceptionCode);
    }
}
