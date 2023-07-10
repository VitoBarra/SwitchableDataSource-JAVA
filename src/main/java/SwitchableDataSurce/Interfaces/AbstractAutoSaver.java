package SwitchableDataSurce.Interfaces;

import VitoBarra.Util.ThreadPoolUtil;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class AbstractAutoSaver<T> implements IMemoryStrategy<T> {
    ScheduledExecutorService AutoSaver;
    String Name;

    /**
     * @param rate
     * @param unit
     * @param OptionalName: nome for the close notification
     */
    public AbstractAutoSaver(int rate, TimeUnit unit, String OptionalName) {
        Name = OptionalName;
        AutoSaver = Executors.newSingleThreadScheduledExecutor();
        AutoSaver.scheduleAtFixedRate(this::AutoSave, rate, rate, unit);
    }

    public void AutoSave() {
        Save();
    }


    @Override
    public void AddOrModify(T e) {
        getMemorization().AddOrModify(e);
    }

    @Override
    public void Save() {
        getMemorization().Save();
    }



    @Override
    public void SaveAndClose()
    {
        getMemorization().SaveAndClose();
        ThreadPoolUtil.TryAwaitTermination(AutoSaver, 30, TimeUnit.SECONDS,Name);
    }

    public abstract IMemoryStrategy getMemorization();


}
