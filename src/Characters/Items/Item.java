package Characters.Items;

import Characters.Attributes.AttributeEnum;
import Characters.ItemSkillSuper;

public class Item extends ItemSkillSuper {

    public Item(String itemName, String itemDescription, AttributeEnum attributeEnum, int attributeValue) {
        this.name = itemName;
        this.description = itemDescription;
        this.attributeEnum = attributeEnum;
        this.attributeValue = attributeValue;
    }
}
