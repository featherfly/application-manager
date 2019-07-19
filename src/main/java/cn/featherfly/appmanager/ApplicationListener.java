
package cn.featherfly.appmanager;

/**
 * <p>
 * Application
 * </p>
 *
 * @author zhongj
 */
public interface ApplicationListener<A extends Application<A>>
        extends ApplicationStartListener<A>, ApplicationStopListener<A> {

}
