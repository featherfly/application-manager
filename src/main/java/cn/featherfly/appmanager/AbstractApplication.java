
package cn.featherfly.appmanager;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * AbstractApplication
 * </p>
 *
 * @author zhongj
 */
public abstract class AbstractApplication<A extends Application<A>>
        implements Application<A> {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private State state = State.SHUTDOWN;

    private List<ApplicationListener<A>> applicationListeners = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        start(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(ApplicationStartListener<A> applicationListener) {
        if (state == State.RUNNING) {
            return;
        }
        doStart();
        state = State.RUNNING;
        @SuppressWarnings("unchecked")
        ApplicationEvent<A> event = new ApplicationEvent<>((A) this);
        if (applicationListener != null) {
            applicationListener.onStart(event);
        }
        applicationListeners.forEach(listener -> {
            listener.onStart(event);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() {
        stop(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stop(ApplicationStopListener<A> applicationListener) {
        if (state == State.SHUTDOWN) {
            return;
        }
        doStop();
        state = State.SHUTDOWN;
        @SuppressWarnings("unchecked")
        ApplicationEvent<A> event = new ApplicationEvent<>((A) this);
        if (applicationListener != null) {
            applicationListener.onStop(event);
        }
        applicationListeners.forEach(listener -> {
            listener.onStop(event);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addListener(ApplicationListener<A> applicationListener) {
        applicationListeners.add(applicationListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeListener(ApplicationListener<A> applicationListener) {
        return applicationListeners.remove(applicationListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ApplicationListener<A>> getListeners() {
        return applicationListeners;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public State getState() {
        return state;
    }

    protected abstract void doStart();

    protected abstract void doStop();

}
