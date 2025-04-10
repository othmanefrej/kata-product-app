package ma.kata.product_app.util;

import ma.kata.product_app.exception.CustomErrorException;
import ma.kata.product_app.model.enums.MessageCode;

public class Util {

    public static CustomErrorException UtilCustomErrorException(MessageCode messageCode) {
        return new CustomErrorException(
                messageCode.getCodeResponse(),
                messageCode.getMessage(),
                messageCode.getStatus()
        );
    }

    public static String extractFieldName(String fullField) {
        String cleanedField = fullField.replaceAll("\\[\\d+\\]", "");
        return cleanedField.substring(cleanedField.lastIndexOf(".") + 1);
    }

}
