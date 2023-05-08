package med.voll.api.infra.exception;

import org.springframework.validation.FieldError;

public record DateErrorValidation(String camp, String mensage) {
    public  DateErrorValidation(FieldError error){
        this(error.getField(), error.getDefaultMessage());
    }
}
