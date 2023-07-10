package SwitchableDataSurce.DefaultImplementation;

import SwitchableDataSurce.Interfaces.IDataAppender;
import SwitchableDataSurce.Interfaces.IDataReader;
import SwitchableDataSurce.Interfaces.IDataSaver;

import java.util.List;

public class DataIOManagerAppendable<T> extends DataIOManager<T>
{
    IDataAppender<T> Appender;

    public DataIOManagerAppendable(IDataSaver<T> _saver, IDataReader<T> _reader, IDataAppender<T> _appender)
    {
        super(_saver, _reader);
        Appender = _appender;
    }



    public void SetAppendable(IDataAppender<T> _app)
    {
        if (_app == null) return;

        SaverLock.lock();
        Appender = _app;
        SaverLock.unlock();
    }


    public void Append(T e)
    {
        if (e == null) return;
        SaverLock.lock();
        if (Saver != null)
            Appender. Append(e);
        SaverLock.unlock();
    }

    public void Append(List<T> e)
    {
        if (e == null) return;
        SaverLock.lock();
        if (Saver != null)
            Appender.Append(e);
        SaverLock.unlock();
    }


}
