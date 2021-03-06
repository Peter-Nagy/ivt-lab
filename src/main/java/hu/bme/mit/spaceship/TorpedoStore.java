package hu.bme.mit.spaceship;

import java.util.Random;

/**
* Class storing and managing the torpedos of a ship
*/
public class TorpedoStore implements ITorpedoStore {

  private int torpedos = 0;
  private Random generator = new Random();

  public TorpedoStore(int numberOfTorpedos){
    this.torpedos = numberOfTorpedos;
  }

  @Override
  public boolean fire(int numberOfTorpedos){
    if(numberOfTorpedos < 1 || numberOfTorpedos > this.torpedos){
      throw new IllegalArgumentException("numberOfTorpedos");
    }

    boolean success = false;

    //simulate random overheating of the launcher bay which prevents firing
    double r = generator.nextDouble();

    if (r > 0.1) {
      // successful firing
      this.torpedos -= numberOfTorpedos;
      success = true;
    } else {
      // failure
      success = false;
    }

    return success;
  }

  @Override
  public boolean isEmpty(){
    return this.torpedos <= 0;
  }

  @Override
  public int getNumberOfTorpedos() {
    return this.torpedos;
  }
}
