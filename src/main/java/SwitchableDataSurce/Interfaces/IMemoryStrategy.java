package SwitchableDataSurce.Interfaces;

import java.util.List;

public interface IMemoryStrategy<T>
{
    void AddOrModify(T e);

    void Save();

    void SaveAndClose();
}
