package exception;

public class YearConversionErrorException extends RuntimeException {
    private String message;

    public YearConversionErrorException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
