
package cn.featherfly.appmanager;

/**
 * <p>
 * Application
 * </p>
 *
 * @author zhongj
 */
public class ApplicationEvent<A extends Application<A>> {

    private A application;

    /**
     * @param application
     */
    public ApplicationEvent(A application) {
        super();
        this.application = application;
    }

    /**
     * getApplication
     * 
     * @return
     */
    public A getApplication() {
        return application;
    }
}
