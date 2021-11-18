package repchain.inter.cooperation.middleware.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.LifeCycle;
import repchain.inter.cooperation.middleware.utils.YamlUtils;

/**
 * @author lhc
 * @version 1.0
 * @className CustomLogContextListener
 * @date 2021年11月15日 4:36 下午
 * @description 描述
 */
public class CustomLogContextListener extends ContextAwareBase implements LoggerContextListener, LifeCycle {

    /**
     * 存储日志路径标识
     */
    public static final String LOG_PAHT_KEY = "log.path";

    @Override
    public boolean isResetResistant() {
        return false;
    }

    @Override
    public void onStart(LoggerContext loggerContext) {

    }

    @Override
    public void onReset(LoggerContext loggerContext) {

    }

    @Override
    public void onStop(LoggerContext loggerContext) {

    }

    @Override
    public void onLevelChange(Logger logger, Level level) {

    }

    @Override
    public void start() {
        String s = YamlUtils.jarPath + "/logs/";
        if (YamlUtils.middleConfig.getMiddleware().getLog() != null) {
            s = YamlUtils.middleConfig.getMiddleware().getLog().getPath();
        }
        System.setProperty(LOG_PAHT_KEY, s);
        Context context = getContext();
        context.putProperty(LOG_PAHT_KEY, s);
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isStarted() {
        return false;
    }
}
