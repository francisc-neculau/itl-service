package org.itl.service.model;

import java.util.Objects;

public class CharImage {
    private final String identifier;
    private final CharType charType;
    private final int height;
    private final int width;
    private final Point centerOfMass;
    private final BoundingRectangle boundingRectangle;

    public CharImage(final String identifier, CharType charType, final int height, final int width, final Point centerOfMass, final BoundingRectangle boundingRectangle) {
        this.identifier = identifier;
        this.charType = charType;
        this.height = height;
        this.width = width;
        this.centerOfMass = centerOfMass;
        this.boundingRectangle = boundingRectangle;
    }

    public String getIdentifier() {
        return identifier;
    }

    public CharType getCharType() {
        return charType;
    }

    public Point getCenterOfMass() {
        return centerOfMass;
    }

    public BoundingRectangle getBoundingRectangle() {
        return boundingRectangle;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(charType.getName()).append(" ")
            .append("id:").append(identifier).append(" ")
            .append("t:").append(charType).append(" ")
            .append("h:").append(height).append(" ")
            .append("w:").append(width).append(" ");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharImage charImage = (CharImage) o;
        return Objects.equals(identifier, charImage.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }
}
