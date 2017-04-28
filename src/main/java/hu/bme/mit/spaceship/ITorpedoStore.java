package hu.bme.mit.spaceship;

public interface ITorpedoStore {
    boolean fire(int numberOfTorpedos);

    boolean isEmpty();

    int getNumberOfTorpedos();
}
