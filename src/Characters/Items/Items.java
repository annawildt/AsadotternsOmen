package Characters.Items;

import java.util.List;

public class Items {
    private List<Item> itemList;

    public Items(List<Item> itemList) {
        this.itemList = itemList;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void addItem(Item item) {
        this.itemList.add(item);
    }

    public void removeItem(Item item) {
        if (this.itemList.contains(item)) {
            this.itemList.remove(item);
        }
    }
}
