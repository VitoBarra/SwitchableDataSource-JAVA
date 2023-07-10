package SwitchableDataSurce.Interfaces;

public interface IMemoryObjectStrategy<T> extends  IMemoryStrategy<T>
{
    T ReadObject();
}
