package SwitchableDataSurce.Interfaces;

public abstract class AbstractMemorization<T> implements IMemoryStrategy<T> {
    protected boolean IsInitialized = false;
    protected boolean DirtBit = false;



    protected final void Initialize() {
        if (IsInitialized) return;
        InitializeStrategy();
        IsInitialized = true;
    }

    protected abstract void InitializeStrategy();
}
