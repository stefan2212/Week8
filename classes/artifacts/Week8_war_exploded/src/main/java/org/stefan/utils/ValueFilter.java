package org.stefan.utils;


public class ValueFilter {

    private String value;
    private boolean selected;

    public ValueFilter(String value, boolean selected) {
        this.value = value;
        this.selected = selected;
    }

    public String getValue() {
        return value;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setValue(String value) {


        this.value = value;
    }

    @Override
    public String toString() {
        return "ValueFilter{" +
                "value='" + value + '\'' +
                ", selected=" + selected +
                '}';
    }


}
