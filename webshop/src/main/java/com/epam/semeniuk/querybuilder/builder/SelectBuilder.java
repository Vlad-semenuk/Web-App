package com.epam.semeniuk.querybuilder.builder;

import java.util.List;

public interface SelectBuilder {

    String buildEqualCondition(Object param, String condition, String columnName);

    String buildEqualAllConditions(List<Integer> conditionList, String columnName);

    String buildOrderByCondition(String orderBy);

    String buildLimitOffsetCondition(Integer limit, Integer offset);

    List<Object> getParameters();
}
