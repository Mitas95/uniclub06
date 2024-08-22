package com.cybersoft.uniclub06.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveFileException extends RuntimeException {
    private String message;

}
