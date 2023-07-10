package SwitchableDataSurce.DefaultImplementation;

import SwitchableDataSurce.Interfaces.AbstractMemorization;
import SwitchableDataSurce.Interfaces.IMemoryListStrategy;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BufferedMemorization<T> extends AbstractMemorization<T> implements IMemoryListStrategy<T> {
    List<T> Saved;
    List<T> Unsaved;
    DataIOManagerAppendable<T> Strategy;


    public BufferedMemorization(DataIOManagerAppendable<T> _strategy, boolean LazyInizilization) {
        Strategy = _strategy;
        if (!LazyInizilization) Initialize();
    }

    @Override
    public synchronized void AddOrModify(T e) {
        Initialize();
        var a = Saved.stream().filter(x -> x.equals(e)).findFirst().orElse(null);
        if (a != null) {
            Unsaved.add(e);
            Saved.remove(e);
        } else {
            var c = Unsaved.stream().filter(x -> x.equals(e)).findFirst().orElse(null);
            if (c == null)
                Unsaved.add(e);
        }
    }

    @Override
    public synchronized void Save() {
        if (!IsInitialized || !DirtBit || Unsaved.size() == 0) return;
        Strategy.Append(Unsaved);
        Saved.addAll(Unsaved);
        Unsaved.clear();
    }

    //TODO: Implement Multiple Reader access
    @Override
    public List<T> ReadList() {
        Initialize();
        return Stream.concat(Saved.stream(), Unsaved.stream()).collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    public void SaveAndClose() {
        Save();
    }


    @Override
    protected void InitializeStrategy() {
        Saved = ReadList();
        Unsaved = new LinkedList<>();
    }
}
