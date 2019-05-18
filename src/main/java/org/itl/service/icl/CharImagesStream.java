package org.itl.service.icl;

import org.itl.service.model.BoundingRectangle;
import org.itl.service.model.CharImage;
import org.itl.service.model.Point;

import java.util.*;
import java.util.function.Function;

public class CharImagesStream {

    private List<CharImage> charImages;

    private Map<Integer, CharImage> map;

    private NavigableSet<Integer> xCoordinatesOfTopLeftPoints;

    CharImagesStream(List<CharImage> charImages) {
        this.charImages = new ArrayList<>();
        this.charImages.addAll(charImages);
        init();
    }

    private void init() {
        this.map = new HashMap<>();
        this.xCoordinatesOfTopLeftPoints = new TreeSet<>(Comparator.comparingInt(i -> i));

        for (CharImage charImage : charImages) {
            int x = charImage.getBoundingRectangle().getTopLeft().getX();
            if(map.put(x, charImage) != null){
                throw new RuntimeException("Time to refactor!");
            };
            xCoordinatesOfTopLeftPoints.add(x);
        }
    }

    public CharImage next() {
        return map.remove(xCoordinatesOfTopLeftPoints.pollFirst());
    }

    public Optional<CharImage> peek() {
        if(xCoordinatesOfTopLeftPoints.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(map.get(xCoordinatesOfTopLeftPoints.first()));
        }
    }

    /**
     * This method will extract and thus consume from the stream
     * all CharImages that are within the bounding points.
     *
     * @param topLeft
     * @param bottomRight
     * @return
     */
    public CharImagesStream crop(Point topLeft, Point bottomRight) {
        SortedSet<Integer> aux = xCoordinatesOfTopLeftPoints.subSet(topLeft.getX(), bottomRight.getX());

        SortedSet<Integer> subSet = new TreeSet<>();
        for (int x : aux) {
            subSet.add(x);
        }

        BoundingRectangle frame = new BoundingRectangle(topLeft, bottomRight);
        List<CharImage> charImages = new ArrayList<>();
        for(Integer x : subSet) {
            CharImage current = map.get(x);
            if(frame.contains(current.getBoundingRectangle())) {
                charImages.add(current);
                map.remove(x);
                xCoordinatesOfTopLeftPoints.remove(x);
            }
        }
//        xCoordinatesOfTopLeftPoints.removeAll(subSet);

        return new CharImagesStream(charImages);
    }

    public boolean hasNext() {
        return !xCoordinatesOfTopLeftPoints.isEmpty();
    }

    public boolean hasSuperscript(CharImage charImage) {
        boolean result = true;

        Optional<CharImage> optionalNext = peek();
        if(optionalNext.isPresent()) {
            CharImage next = optionalNext.get();
            result &= charImage.getBoundingRectangle().isInFirstCadrant(
                    next.getBoundingRectangle(),
                    (int) (0.1 * charImage.getHeight()));
            result &= next.getBoundingRectangle().isHigher(charImage.getBoundingRectangle());
//        result &= charImage.getBoundingRectangle().isNearOnRightside(next.getBoundingRectangle());
        } else {
            return false;
        }
        return result;
    }

    /**
     * (0,0)
     * +--------------------------------------- X Axis
     * |        +----+                  +----+
     * |        |char|                  |char|
     * | ====== | A  |                  | B  | ====== normal line of flow
     * |        |    | y--------------> |    |
     * |        +----+                  +----+
     * |
     * Y Axis
     *
     * CharImage B is the first intersection with y.
     * We return an optional as we might not have any char B.
     *
     * @param y
     * @return Optional of CharImage
     */
    public Optional<CharImage> firstIntersectionWithY(int y) {
        CharImage current = map.get(xCoordinatesOfTopLeftPoints.first());
        while(true) {
            if(yStrikes(y, current)) {
                return Optional.of(current);
            }
            Integer nextX = xCoordinatesOfTopLeftPoints.higher(current.getBoundingRectangle().getMiddleLeft().getX());
            if(nextX == null) {
                return Optional.empty();
            }
            current = map.get(nextX);
        }
    }

    private boolean yStrikes(Integer y, CharImage charImage) {
        return y > charImage.getBoundingRectangle().getTopLeft().getY() &&
                y < charImage.getBoundingRectangle().getBottomRight().getY();
    }

    public int highestIntersectingY(Point bottomLeft, Point bottomRight) {
        SortedSet<Integer> subSet = xCoordinatesOfTopLeftPoints.subSet(bottomLeft.getX(), true, bottomRight.getX(), true);
        // it may be the highest.
        int maxY = 0;
        for(Integer x : subSet) {
            if(maxY < map.get(x).getBoundingRectangle().getBottomRight().getY()) {
                maxY = map.get(x).getBoundingRectangle().getBottomRight().getY();
            }
        }
        if(maxY == 0) {
            throw new RuntimeException("time to refactor");
        }
        return maxY;
    }

    public int lowestIntersectingY(Point bottomLeft, Point bottomRight) {
        SortedSet<Integer> subSet = xCoordinatesOfTopLeftPoints.subSet(bottomLeft.getX(), true, bottomRight.getX(), true);
        // it may be the lowest.
        int minY = map.get(subSet.first()).getBoundingRectangle().getTopLeft().getY();
        for(Integer x : subSet) {
            if(minY > map.get(x).getBoundingRectangle().getTopLeft().getY()) {
                minY = map.get(x).getBoundingRectangle().getTopLeft().getY();
            }
        }
        if(minY == 0) {
            throw new RuntimeException("time to refactor");
        }
        return minY;
    }

    private SortedSet<Integer> verticalSlice(int leftX, int rightX) {
        return null;
    }
}
