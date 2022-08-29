package by.ilyin.manager.util.observer;

public interface Observable {

    void attach(Observer observer);

    void detach();

    void notifyObserver();

}
