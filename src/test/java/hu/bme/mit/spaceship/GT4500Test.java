package hu.bme.mit.spaceship;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class GT4500Test {

  private GT4500 ship;
  private ITorpedoStore mockPrimaryTS;
  private ITorpedoStore mockSecondaryTS;

  @Before
  public void init(){
    this.mockPrimaryTS = mock(TorpedoStore.class);
    this.mockSecondaryTS = mock(TorpedoStore.class);
    this.ship = new GT4500(this.mockPrimaryTS, this.mockSecondaryTS);
  }

  @Test
  public void fireTorpedos_Single_Success(){
    // Arrange
    when(mockPrimaryTS.fire(1)).thenReturn(true);
    when(mockSecondaryTS.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);

    //Verifying the mock
    verify(mockPrimaryTS, times(1)).fire(1);
    verify(mockSecondaryTS, times(0)).fire(1);
  }

  @Test
  public void fireTorpedos_All_Success(){
    // Arrange
    when(mockPrimaryTS.fire(1)).thenReturn(true);
    when(mockSecondaryTS.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.ALL);

    // Assert
    assertEquals(true, result);

    //Verifying the mock
    verify(mockPrimaryTS, times(1)).fire(1);
    verify(mockSecondaryTS, times(1)).fire(1);
  }

  // Test 1
  @Test
  public void fireTwoTorpedosBothLoaded(){
    // Arrange
    when(mockPrimaryTS.fire(1)).thenReturn(true);
    when(mockSecondaryTS.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);

    result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);

    //Verifying the mock
    verify(mockPrimaryTS, times(1)).fire(1);
    verify(mockSecondaryTS, times(1)).fire(1);
  }

  // Test 2
  @Test
  public void fireTwoTorpedosOnlyOneLoaded(){
    // Arrange
    when(mockPrimaryTS.fire(1)).thenReturn(true);
    when(mockSecondaryTS.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);

    result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);

    //Verifying the mock
    verify(mockPrimaryTS, times(1)).fire(1);
    verify(mockSecondaryTS, times(1)).fire(1);
  }

  // Test 3
  @Test
  public void fireTorpedoOnlySecondLoaded(){
    // Arrange
    when(mockPrimaryTS.fire(1)).thenReturn(false);
    when(mockSecondaryTS.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);

    //Verifying the mock
    verify(mockPrimaryTS, times(1)).fire(1);
    verify(mockSecondaryTS, times(0)).fire(1);
  }

  // Test 4
  @Test
  public void fireTwoTorpedosFirstNotLoaded(){
    // Arrange
    when(mockPrimaryTS.isEmpty()).thenReturn(true);
    when(mockSecondaryTS.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);

    result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);

    //Verifying the mock
    verify(mockPrimaryTS, times(0)).fire(1);
    verify(mockSecondaryTS, times(0)).fire(1);
  }

  // Test 5
  @Test
  public void fireAllWhenNotLoaded(){
    // Arrange
    when(mockPrimaryTS.isEmpty()).thenReturn(true);
    when(mockSecondaryTS.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.ALL);

    // Assert
    assertEquals(false, result);

    //Verifying the mock
    verify(mockPrimaryTS, times(0)).fire(1);
    verify(mockSecondaryTS, times(0)).fire(1);
  }

  @Test
  public void fireTwoSingleWhenOnlyFirstIsLoaded(){
    // Arrange
    when(mockPrimaryTS.isEmpty()).thenReturn(false);
    when(mockPrimaryTS.fire(1)).thenReturn(true);
    when(mockSecondaryTS.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);

    assertEquals(true, result);

    result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);

    //Verifying the mock
    verify(mockPrimaryTS, times(2)).fire(1);
    verify(mockSecondaryTS, times(0)).fire(1);
  }


  @Test
  public void fireThreeSingleWhenFirstHasOnlyOne(){
    // Arrange
    when(mockPrimaryTS.isEmpty()).thenReturn(false);
    when(mockPrimaryTS.fire(1)).thenReturn(true);
    when(mockSecondaryTS.fire(1)).thenReturn(true);
    when(mockSecondaryTS.isEmpty()).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedos(FiringMode.SINGLE);

    assertEquals(true, result);

    result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);

    when(mockPrimaryTS.isEmpty()).thenReturn(true);
    when(mockPrimaryTS.fire(1)).thenReturn(false);
    when(mockSecondaryTS.fire(1)).thenReturn(true);
    when(mockSecondaryTS.isEmpty()).thenReturn(false);

    result = ship.fireTorpedos(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);

    //Verifying the mock
    verify(mockPrimaryTS, times(1)).fire(1);
    verify(mockSecondaryTS, times(2)).fire(1);
  }
}
