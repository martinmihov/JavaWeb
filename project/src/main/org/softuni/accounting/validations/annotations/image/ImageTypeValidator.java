package org.softuni.accounting.validations.annotations.image;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ImageTypeValidator implements ConstraintValidator<ImageType,String> {
    @Override
    public void initialize(ImageType imageType) {

    }

    @Override
    public boolean isValid(String imageType, ConstraintValidatorContext context) {
        boolean isValid = false;
        String extension = imageType.substring(imageType.lastIndexOf('.'));
        String[] allowedContentTypes = {
                "png",
                "jpg",
                "jpeg",
                "gif"
        };
        for (String allowedContentType : allowedContentTypes) {
            if(allowedContentType.equals(extension)){
                isValid = true;
            }
        }
        return isValid;
    }


}
