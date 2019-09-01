
package cn.featherfly.appmanager;

import java.util.Locale;

import cn.featherfly.common.exception.LocalizedException;

/**
 * <p>
 * ApplicationException
 * </p>
 * 
 * @author zhongj
 */
public class ApplicationException extends LocalizedException {

    private static final long serialVersionUID = 3971861604761216969L;

    /**
     * 
     */
    public ApplicationException() {
        super();
    }

    /**
     * @param message
     * @param locale
     * @param ex
     */
    public ApplicationException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * @param message
     * @param locale
     */
    public ApplicationException(String message, Locale locale) {
        super(message, locale);
    }

    /**
     * @param message
     * @param args
     * @param locale
     * @param ex
     */
    public ApplicationException(String message, Object[] args, Locale locale,
            Throwable ex) {
        super(message, args, locale, ex);
    }

    /**
     * @param message
     * @param args
     * @param locale
     */
    public ApplicationException(String message, Object[] args, Locale locale) {
        super(message, args, locale);
    }

    /**
     * @param message
     * @param args
     * @param ex
     */
    public ApplicationException(String message, Object[] args, Throwable ex) {
        super(message, args, ex);
    }

    /**
     * @param message
     * @param args
     */
    public ApplicationException(String message, Object[] args) {
        super(message, args);
    }

    /**
     * @param message
     * @param ex
     */
    public ApplicationException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * @param message
     */
    public ApplicationException(String message) {
        super(message);
    }

    /**
     * @param ex
     */
    public ApplicationException(Throwable ex) {
        super(ex);
    }

}
