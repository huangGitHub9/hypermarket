package cn.xu.core.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SchedulerJob implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("服务器启动了");
        //执行计划
        try {
            execScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("服务器结束了");
    }

    public static void execScheduler() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //创建jobDetail实例，绑定Job实现类
      // 指明job的名称，所在组的名称，以及绑定job类
        JobDetail job = JobBuilder.newJob(MyJob.class)
                .withIdentity("job1", "group1")
                .build();

        //秒 分 小时 日期 月份 星期 年
        //      使用cornTrigger规则  每天00点24分
        Trigger trigger=TriggerBuilder.newTrigger().withIdentity("cornTrigger", "triggerGroup")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 * 9 * * ? *"))
                .startNow().build();
      // 把作业和触发器注册到任务调度中
        scheduler.scheduleJob(job, trigger);

        scheduler.start();

    }
}
