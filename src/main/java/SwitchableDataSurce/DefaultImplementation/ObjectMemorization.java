package SwitchableDataSurce.DefaultImplementation;

import SwitchableDataSurce.Interfaces.IMemoryListStrategy;
import SwitchableDataSurce.Interfaces.IMemoryObjectStrategy;

public class ObjectMemorization<T> implements IMemoryObjectStrategy<T>
{

    IMemoryListStrategy<T> Strategy;


    public ObjectMemorization(IMemoryListStrategy<T> strategy)
    {
        Strategy = strategy;
    }
    @Override
    public T ReadObject()
    {
        var data = Strategy.ReadList();
        return data.size() > 0 ? data.get(0) : null;
    }

    @Override
    public void AddOrModify(T e) {
        Strategy.AddOrModify(e);
    }

    @Override
    public void Save() {
        Strategy.Save();
    }

    @Override
    public void SaveAndClose() {
        Strategy.SaveAndClose();
    }
}
