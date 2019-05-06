package html.quartz;

public class Quartzmanager {
//	public void addjob(){
//		try {
//            // Grab the Scheduler instance from the Factory
//            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//            
//            JobDetail job = JobBuilder
//            		  .newJob(CrawlOnTimeJob.class)
//            	      .withIdentity("myJob", "group1") // name "myJob", group "group1"
//            	      .build();
//            
//
//            CronTrigger trigger = TriggerBuilder
//            		  .newTrigger()
//            		  .withIdentity("trigger1", "group1")
//            		  .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(17,58))
//            		  .forJob("myJob","group1")
//            		  .build();
//            
////            CronTrigger trigger2 = TriggerBuilder
////			            .newTrigger()
////			            .withIdentity("trigger2", "group1")
////			            .withSchedule(CronScheduleBuilder.cronSchedule("0 0/2 8-17 * * ?"))
////			            .forJob("myJob", "group1")
////			            .build();
//            // and start it off
//            scheduler.scheduleJob(job, trigger);
//            
//            scheduler.start();
//
////            scheduler.shutdown();
//
//        } catch (SchedulerException se) {
//            se.printStackTrace();
//        }
//	}

}
