package SwitchableDataSurce.Interfaces;

import java.util.List;

public interface IDataSaver<T>
{
    void FlushData(List<T> DataList);
}
