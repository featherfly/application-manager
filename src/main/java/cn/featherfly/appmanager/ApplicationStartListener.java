
package cn.featherfly.appmanager;

/**
 * <p>
 * Application
 * </p>
 *
 * @author zhongj
 */
@FunctionalInterface
public interface ApplicationStartListener<A extends Application<A>> {

    void onStart(ApplicationEvent<A> event);

}
