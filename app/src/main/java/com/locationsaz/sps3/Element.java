package com.locationsaz.sps3;

public class Element {

    String elementName;
    Element stronger;
    Element weaker;

    public Element() {
    }

    public Element(String elementName) {
        this.elementName = elementName;
    }


    public Element(String elementName, Element stronger, Element weaker) {
        this.elementName = elementName;
        this.stronger = stronger;
        this.weaker = weaker;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public Element getStronger() {
        return stronger;
    }

    public void setStronger(Element stronger) {
        this.stronger = stronger;
    }

    public Element getWeaker() {
        return weaker;
    }

    public void setWeaker(Element weaker) {
        this.weaker = weaker;
    }
}
