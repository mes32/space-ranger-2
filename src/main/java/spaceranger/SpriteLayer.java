/*
    SpriteLayer.java

    A layer of Sprite objects to be shown on the GameBoard
 */

package spaceranger;

import java.util.*;

public class SpriteLayer<T extends Sprite> implements Iterable<Sprite> {

    private java.util.List<Sprite> list = Collections.synchronizedList(new ArrayList());

    SpriteLayer() {

    }

    public void insert(Sprite sprite) {
        list.add(sprite);
    }

    public void remove(Sprite sprite) {
        list.remove(sprite);
    }

    @Override
    public Iterator<Sprite> iterator() {
        return list.iterator();
    }

}
