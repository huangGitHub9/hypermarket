package cn.xu.core.quartz;

import cn.xu.dygl.user.dao.UserDao;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;

public class MyJob implements Job {


    @Resource
    private UserDao userDao;
    /*
        根据你的时间调度来执行该函数
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        /*
         每天晚上的24:00 去数据库中检查该用户的会员到期时间 是否小于当前时间
         如果小于则不做处理
         否则 降低该用户的角色，跟新该用户的会员开通时间和会员到期时间都值为null
         */
        //findAllByPaging
        //




    }
}
