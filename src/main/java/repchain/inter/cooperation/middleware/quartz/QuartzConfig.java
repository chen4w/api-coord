package repchain.inter.cooperation.middleware.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class QuartzConfig {

    private static Logger logger = LoggerFactory.getLogger(QuartzConfig.class);

    public static void run() {
        Scheduler scheduler = null;
        try {
            // 初始化一个调度工厂，并实例化一个调度类
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            scheduler = schedulerFactory.getScheduler();
            // 定义一个Job类，命名为job1，并绑定到一个名为group1的组中
            JobDetail job = newJob(CommitJob.class).withIdentity("job1", "group1").build();
            // 实例化一个触发器，命名为trigger1，并绑定到一个名为group1的组中，Job类运行开始时间为runTime（下一分钟）
            Trigger trigger = newTrigger().withIdentity("trigger1", "group1").withSchedule(cronSchedule("*/30 * * * * ?")).build();
            // 告诉quartz使用我们的触发器来调度任务
            Date ft = scheduler.scheduleJob(job, trigger);
            logger.info(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: "
                    + ((CronTrigger) trigger).getCronExpression());

            // 启动调度器(在调度器启动之前，实际上什么都不能运行)
            scheduler.start();
        } catch (Exception e) {
            logger.error("定时任务线程创建异常", e);
        }
        Scheduler finalScheduler = scheduler;
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("*** shutting down quartz");
            try {
                if (finalScheduler != null) {
                    finalScheduler.shutdown(true);
                }
            } catch (SchedulerException e) {
                e.printStackTrace(System.err);
            }
            logger.info("*** quartz shut down");
        }));
    }
}
