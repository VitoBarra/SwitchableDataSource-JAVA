package SwitchableDataSurce.DefaultImplementation;

import SwitchableDataSurce.Interfaces.IDataReader;
import SwitchableDataSurce.Interfaces.IDataSaver;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DataIOManager<T>
{
    IDataReader<T> reader;
    IDataSaver<T> Saver;
    Lock ReaderLock;
    Lock SaverLock;

    public DataIOManager(IDataSaver<T> _saver, IDataReader<T> _reader)
    {
        reader = _reader;
        Saver = _saver;
        ReaderLock = new ReentrantLock();
        SaverLock = new ReentrantLock();
    }

    public  void SetSaver(IDataSaver<T> _saver)
    {
        SaverLock.lock();
        Saver = _saver;
        SaverLock.unlock();
    }

    public void SetReader(IDataReader<T> _reader)
    {
        ReaderLock.lock();
        reader = _reader;
        ReaderLock.unlock();
    }

    public void Save(List<T> e)
    {
        SaverLock.lock();
        if (Saver != null)
            Saver.FlushData(e);
        SaverLock.unlock();
    }

    public List<T> ReadList()
    {
        List<T> List = null;
        ReaderLock.lock();
        if (reader != null)
            List = reader.ReadDataList();
        ReaderLock.unlock();
        return List;
    }


}
