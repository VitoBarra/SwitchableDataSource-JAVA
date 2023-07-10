package SwitchableDataSurce.Util;

import SwitchableDataSurce.DefaultImplementation.AutoSaverList;
import SwitchableDataSurce.DefaultImplementation.AutoSaverObject;
import SwitchableDataSurce.Interfaces.IMemoryListStrategy;
import SwitchableDataSurce.Interfaces.IMemoryObjectStrategy;

import java.util.concurrent.TimeUnit;

public class AutoSaverBuilder<T> {
    private int rate = 30;
    private TimeUnit unit = TimeUnit.SECONDS;
    private String optionalName = "";

    public AutoSaverBuilder<T> setRate(int rate) {
        this.rate = rate;
        return this;
    }
    public AutoSaverBuilder<T> setUnit(TimeUnit unit) {
        this.unit = unit;
        return this;
    }

    public AutoSaverBuilder<T> setRate(int times, TimeUnit unit) {
        this.rate = times;
        this.unit = unit;
        return this;
    }

    public AutoSaverBuilder<T> setName(String optionalName) {
        this.optionalName = optionalName;
        return this;
    }

    public AutoSaverList<T> createAutoSaver(IMemoryListStrategy<T> mem) {
        return new AutoSaverList<>(rate, unit, optionalName, mem);
    }
    public AutoSaverObject<T> createAutoSaver(IMemoryObjectStrategy<T> mem) {
        return new AutoSaverObject<>(rate, unit, optionalName, mem);
    }
}