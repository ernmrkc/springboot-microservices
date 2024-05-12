package org.ernmrkc.customerservice.Modules;

import org.springframework.http.ResponseEntity;

public interface Command<E, B, T> {
    ResponseEntity<T> execute(E entity, B bindingResult);
}
