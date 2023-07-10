package SwitchableDataSurce.Interfaces;

import java.util.List;

public interface IMemoryListStrategy<T>  extends  IMemoryStrategy<T>{

    List<T> ReadList();
}
