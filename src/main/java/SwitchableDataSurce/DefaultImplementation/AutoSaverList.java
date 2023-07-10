package SwitchableDataSurce.DefaultImplementation;

import SwitchableDataSurce.Interfaces.AbstractAutoSaver;
import SwitchableDataSurce.Interfaces.IMemoryListStrategy;
import SwitchableDataSurce.Interfaces.IMemoryStrategy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AutoSaverList<T> extends AbstractAutoSaver<T> implements IMemoryListStrategy<T> {

    IMemoryListStrategy<T> Memorization;

    /**
     * @param rate
     * @param unit
     * @param OptionalName : nome for the close notification
     */
    public AutoSaverList(int rate, TimeUnit unit, String OptionalName , IMemoryListStrategy<T> _memorization) {
        super(rate, unit, OptionalName);
        Memorization = _memorization;
    }

    @Override
    public List<T> ReadList() {
        return Memorization.ReadList();
    }

    @Override
    public IMemoryStrategy getMemorization() {
        return Memorization;
    }
}
