package com.ToxicBakery.logging;

import com.toxicbakery.logging.Arbor;
import com.toxicbakery.logging.Seedling;

public class Main {

    public static void main(String... args) {
        Arbor.sow(new Seedling());
        Arbor.d("Hello %s", "World!");
    }

}
