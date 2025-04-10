package ma.kata.product_app.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ma.kata.product_app.model.enums.InventoryStatus;

@Converter(autoApply = true)
public class InventoryStatusConverter implements AttributeConverter<InventoryStatus, String> {
    @Override
    public String convertToDatabaseColumn (InventoryStatus attribute){
        return (attribute == null) ? null : attribute.getValue();
    }

    @Override
    public InventoryStatus convertToEntityAttribute (String dbData){
        return (dbData == null) ? null : InventoryStatus.fromValue(dbData);
    }
}
