package org.itl.service.icl;

import org.itl.service.model.BoundingRectangle;
import org.itl.service.model.CharImage;
import org.itl.service.model.Point;

import java.util.*;

public class CharImageSequence {

    private List<CharImage> charImages;

    private SortedMap<Integer, CharImage> map;

    private NavigableSet<Integer> xCoordinatesOfTopLeftPoints;

    CharImageSequence(List<CharImage> charImages) {
        this.charImages = new ArrayList<>();
        this.charImages.addAll(charImages);
        init();
    }

    private void init() {
        this.map = new TreeMap<>();
        this.xCoordinatesOfTopLeftPoints = new TreeSet<>(Comparator.comparingInt(i -> i));

        for (CharImage charImage : charImages) {
            int x = charImage.getBoundingRectangle().getTopLeft().getX();
            if(map.put(x, charImage) != null){
                throw new RuntimeException("Time to refactor!"); // FIXME: !
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
    public CharImageSequence crop(Point topLeft, Point bottomRight) {
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

        return new CharImageSequence(charImages);
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

    /**
     *  This method tries to find the highest y coordinate that
     * intersects any CharImage within the boundaries defined
     * by points A and B along with a maxDistance.
     *  If no such y exits, it returns -1.
     *  FIXME: Maybe a new checked exception ?
     *
     * (A)+------------+(B)
     *    .  |    |    .
     *    .  |    |    .
     *    .  |    |    .
     *    .  |    +(C) .
     *    .  |         .
     *  ..................
     *      /\
     *   maxDistance
     *
     * @param topLeft
     * @param topRight
     * @param maxDistance
     * @return
     */
    public int highestIntersectingY(Point topLeft, Point topRight, int maxDistance) {
        SortedSet<Integer> subSet = xCoordinatesOfTopLeftPoints.subSet(topLeft.getX(), true, topRight.getX(), true);
        int baseY = topLeft.getY();
        int maxY = -1;
        int currentY;
        for(Integer x : subSet) {
            currentY = map.get(x).getBoundingRectangle().getBottomRight().getY();
            if(maxDistance > 0 && (currentY - baseY) > maxDistance) {
                continue;
            }
            if(maxY < currentY) {
                maxY = currentY;
            }
        }
        return maxY;
    }

    public int highestIntersectingY(Point topLeft, Point topRight) {
        return highestIntersectingY(topLeft, topRight, 0);
    }

    /**
     *  This method tries to find the lowest y coordinate that
     * intersects any CharImage within the boundaries defined
     * by points A and B along with a maxDistance.
     *  If no such y exits, it returns -1.
     *  FIXME: Maybe a new checked exception ?
     *
     *   maxDistance
     *      \/
     *  ..................
     *    .  |         .
     *    .  |    +(C) .
     *    .  |    |    .
     *    .  |    |    .
     *    .  |    |    .
     * (A)+------------+(B)
     *
     * @param bottomLeft
     * @param bottomRight
     * @param maxDistance
     * @return
     */
    public int lowestIntersectingY(Point bottomLeft, Point bottomRight, int maxDistance) {
        SortedSet<Integer> subSet = xCoordinatesOfTopLeftPoints.subSet(bottomLeft.getX(), true, bottomRight.getX(), true);
        // if no element found in the slice, then -1
        if(subSet.isEmpty()) {
            return -1;
        }
        int baseY = bottomLeft.getY();
        // it may be the lowest.
        int minY = map.get(subSet.first()).getBoundingRectangle().getTopY();
        int currentY;
        for(Integer x : subSet) {
            currentY = map.get(x).getBoundingRectangle().getTopY();
            if(maxDistance > 0 && (baseY - minY) > maxDistance) {
                continue;
            }
            if(minY > currentY) {
                minY = currentY;
            }
        }
        return minY;
    }

    public int lowestIntersectingY(Point bottomLeft, Point bottomRight) {
        return lowestIntersectingY(bottomLeft, bottomRight, 0);
    }

    private SortedSet<Integer> verticalSlice(int leftX, int rightX) {
        return null;
    }
}
