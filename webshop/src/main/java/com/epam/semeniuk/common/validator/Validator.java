package com.epam.semeniuk.common.validator;


import java.util.Map;

public interface Validator<T, S> {

 Map<String, String> validate(T request, S service);
}
