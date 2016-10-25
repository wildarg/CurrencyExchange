package com.wild.currencyexchange.network;

// Created by Wild on 24.10.2016.

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "gesmes:Envelope", strict=false)
public class RateDataEntity {
    @Element(name = "Cube")
    public RootCube cube;

    public static class RootCube {
        @Element(name = "Cube")
        public TimeCube cube;
    }

    public static class TimeCube {
        @Attribute(name = "time")
        public String time;
        @ElementList(inline = true)
        public List<RateCube> rates;
    }

    @Root(name = "Cube")
    public static class RateCube {
        @Attribute(name = "currency")
        public String currency;
        @Attribute(name = "rate")
        public float rate;
    }
}
