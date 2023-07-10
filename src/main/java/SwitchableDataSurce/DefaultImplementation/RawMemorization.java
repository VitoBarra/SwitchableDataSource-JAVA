package SwitchableDataSurce.DefaultImplementation;

import SwitchableDataSurce.Interfaces.AbstractMemorization;
import SwitchableDataSurce.Interfaces.IMemoryListStrategy;

import java.util.ArrayList;
import java.util.List;

public class RawMemorization<T> extends AbstractMemorization<T> implements IMemoryListStrategy<T> {
    List<T> Data;
    DataIOManager<T> Strategy;


    public RawMemorization(DataIOManager<T> _strategy) {
        this(_strategy, true);
    }

    public RawMemorization(DataIOManager<T> _strategy, Boolean LazyRead) {
        Strategy = _strategy;
        if (!LazyRead) Initialize();
    }

    @Override
    public synchronized void AddOrModify(T e) {
        Initialize();
        Data.stream().filter(x -> x.equals(e)).findFirst().ifPresent(a -> Data.remove(a));
        Data.add(e);
        DirtBit = true;
    }


    @Override
    public synchronized void Save() {
        if (!IsInitialized || !DirtBit || Data.size() == 0) return;
        Strategy.Save(Data);
        DirtBit = false;
    }

    @Override
    public List<T> ReadList() {
        Initialize();
        return Data;
    }


    @Override
    protected void InitializeStrategy() {
        var ReadData = Strategy.ReadList();
        Data = ReadData == null ? new ArrayList<>() : ReadData;
    }

    @Override
    public void SaveAndClose() {
        Save();
    }
}
