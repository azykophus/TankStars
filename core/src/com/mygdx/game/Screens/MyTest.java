package com.mygdx.game.Screens;
import org.junit.Test;

import static com.mygdx.game.Screens.PlayScreen.damage;
import static org.junit.Assert.assertEquals;

public class MyTest {
    @Test
    public void testDamage(){
        assertEquals(50,(int)damage(0,0,0,4,100));
    }
}

