package SwitchableDataSurce.DefaultImplementation;

import SwitchableDataSurce.Interfaces.AbstractAutoSaver;
import SwitchableDataSurce.Interfaces.IMemoryObjectStrategy;
import SwitchableDataSurce.Interfaces.IMemoryStrategy;

import java.util.concurrent.TimeUnit;

public class AutoSaverObject<T> extends AbstractAutoSaver<T> implements IMemoryObjectStrategy<T>
{
    IMemoryObjectStrategy<T> Memorization;

    /**
     * @param rate
     * @param unit
     * @param OptionalName : nome for the close notification
     */
    public AutoSaverObject(int rate, TimeUnit unit, String OptionalName , IMemoryObjectStrategy<T> _memorization) {
        super(rate, unit, OptionalName);
        Memorization = _memorization;
    }


    @Override
    public IMemoryStrategy getMemorization() {
        return Memorization;
    }

    @Override
    public T ReadObject() {
        return Memorization.ReadObject();
    }
}
