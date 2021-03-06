/*
    SpriteLayer.java

    A layer of Sprite objects to be shown on the GameBoard
 */

package spaceranger.sprite;

import java.util.*;

public class SpriteLayer<T extends Sprite> implements Iterable<T> {

    private List<T> list;

    public SpriteLayer() {
        list = Collections.synchronizedList(new ArrayList<T>());
    }

    public void insert(T sprite) {
        list.add(sprite);
    }

    public void remove(T sprite) {
        list.remove(sprite);
    }

    public List<T> getList() {
        return list;
    }

    public Sprite get(int i) {
        return list.get(i);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
