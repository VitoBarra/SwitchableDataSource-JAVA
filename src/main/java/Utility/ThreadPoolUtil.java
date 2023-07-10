package VitoBarra.JavaUtil.Other;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUtil
{
    public static List<Runnable> TryAwaitTermination(ExecutorService ThreadPool, int time, TimeUnit timeUnit,String ThreadPoolName)
    {
        var IsTerminatedSuccessfully = false;

        System.out.println(String.format("%15s | Closing Thread Pool in %3d %s",ThreadPoolName,time,timeUnit.toString()) );
        ThreadPool.shutdown();
        try
        {
            IsTerminatedSuccessfully = ThreadPool.awaitTermination(time, timeUnit);
        } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }

        if (IsTerminatedSuccessfully)
            return null;
        else
        {
            System.out.println("abort waiting");
            return  ThreadPool.shutdownNow();
        }
    }
    public static List<Runnable> TryAwaitTermination(ExecutorService ThreadPool, int time, TimeUnit timeUnit)
    {
        return TryAwaitTermination(ThreadPool, time,  timeUnit, "");
    }


}
