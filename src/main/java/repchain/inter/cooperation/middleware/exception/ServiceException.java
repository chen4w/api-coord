package repchain.inter.cooperation.middleware.exception;

/**
 * @author lhc
 * @version 1.0
 * @className ServiceException
 * @date 2021年11月08日 4:17 下午
 * @description 描述
 */
public class ServiceException extends RuntimeException {

    public ServiceException() {

    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
