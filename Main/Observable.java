package Main;

public interface Observable {

    void register(Observer o);

    void unregister(Observer o);

    void notifyObserver(Measurement measurement);
}
