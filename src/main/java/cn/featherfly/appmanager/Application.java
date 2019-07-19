
package cn.featherfly.appmanager;

import java.util.List;

/**
 * <p>
 * Application
 * </p>
 *
 * @author zhongj
 */
public interface Application<A extends Application<A>> {

    public enum State {
        RUNNING, SHUTDOWN
    }

    void start();

    void start(ApplicationStartListener<A> applicationListener);

    void stop();

    void stop(ApplicationStopListener<A> applicationListener);

    /**
     * addListener
     *
     * @param <E>
     * @param applicationListener
     */
    void addListener(ApplicationListener<A> applicationListener);

    /**
     * removeListener
     *
     * @param <E>
     * @param applicationListener
     * @return
     */
    boolean removeListener(ApplicationListener<A> applicationListener);

    /**
     * getListeners
     *
     * @param <E>
     * @return
     */
    List<ApplicationListener<A>> getListeners();

    State getState();

}
