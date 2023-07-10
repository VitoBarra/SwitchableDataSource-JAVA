package SwitchableDataSurce.Interfaces;

import java.util.List;

public interface IDataAppender<T>
{
    void Append(List<T> DataList);
    void Append(T Data);
}
