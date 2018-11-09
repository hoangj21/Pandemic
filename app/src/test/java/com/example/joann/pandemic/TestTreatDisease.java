package com.example.joann.pandemic;

import com.example.joann.pandemic.pandemic.PandemicGameState;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestTreatDisease {
    public void isTreatDisease() {
        PandemicGameState instance = new PandemicGameState();
        int diseaseCubeCount = instance.getPlayer().getCurrentLocation().getDiseaseCubes().size()-1;
        instance.treatDisease(instance.getPlayer(), instance.getPlayer().getCurrentLocation());
        assertEquals(instance.getPlayer().getCurrentLocation().getDiseaseCubes().size(), diseaseCubeCount);

    }

}
