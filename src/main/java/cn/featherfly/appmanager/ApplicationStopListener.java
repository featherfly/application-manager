
package cn.featherfly.appmanager;

/**
 * <p>
 * Application
 * </p>
 *
 * @author zhongj
 */
@FunctionalInterface
public interface ApplicationStopListener<A extends Application<A>> {

    void onStop(ApplicationEvent<A> event);

}
