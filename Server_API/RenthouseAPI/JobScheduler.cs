using Quartz;
using Quartz.Impl;
using RenthouseAPI.Services;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace RenthouseAPI
{
    public class JobScheduler
    {
        public static void Start()
        {
            IJobDetail newsJob = JobBuilder.Create<NewsJob>()
                .WithIdentity("job1")
                .Build();

            ITrigger trigger = TriggerBuilder.Create()
                .WithDailyTimeIntervalSchedule
                (s =>
                    s.WithIntervalInSeconds(30)
                    .OnEveryDay()
                )
                .ForJob(newsJob)
                .WithIdentity("trigger1")
                .StartNow()
                .WithCronSchedule("0 0/15 * * * ?")
                .Build();

            ISchedulerFactory sf = new StdSchedulerFactory();
            IScheduler sc = sf.GetScheduler();
            sc.ScheduleJob(newsJob, trigger);
            sc.Start();


        }
    }
}