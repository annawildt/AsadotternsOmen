package Characters;

import Characters.Attributes.AttributeEnum;

public abstract class ItemSkillSuper {
    protected String name;
    protected String description;
    protected AttributeEnum attributeEnum;
    protected int attributeValue;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public AttributeEnum getAttributeEnum() {
        return attributeEnum;
    }

    public int getAttributeValue() {
        return attributeValue;
    }
}
