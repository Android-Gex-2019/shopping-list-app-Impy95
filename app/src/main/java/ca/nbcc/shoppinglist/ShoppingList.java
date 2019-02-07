package ca.nbcc.shoppinglist;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ShoppingList implements Serializable {

    private Map shoppingList = new HashMap();

    public void addItem(String item) {
        if (this.shoppingList.containsKey(item)) {
            this.shoppingList.replace(item, (int) this.shoppingList.get(item) + 1);
        } else {
            this.shoppingList.put(item, 1);
        }
    }

    public Map<String, Integer> getShoppingList() {
        return shoppingList;
    }

    public void clearList() {
        this.shoppingList.clear();
    }
}
