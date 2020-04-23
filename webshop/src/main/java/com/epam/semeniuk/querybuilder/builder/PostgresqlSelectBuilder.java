package com.epam.semeniuk.querybuilder.builder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class PostgresqlSelectBuilder implements SelectBuilder {

    private static final String ORDER_BY = " ORDER BY ";
    private static final String OR = " OR ";
    private static final String LIMIT = " LIMIT ";
    private static final String OFFSET = " OFFSET ";

    private List<Object> parameters = new ArrayList<>();

    @Override
    public String buildEqualCondition(Object param, String condition, String columnName) {
        StringBuilder query = new StringBuilder();
        query.append(columnName).append(condition).append("?");
        parameters.add(param);
        return query.toString();
    }


    @Override
    public String buildEqualAllConditions(List<Integer> conditionList, String columnName) {
        StringBuilder query = new StringBuilder();
        appendFromList(query, conditionList, columnName);
        return query.toString();
    }

    @Override
    public String buildOrderByCondition(String orderBy) {
        StringBuilder query = new StringBuilder();
        query.append(ORDER_BY).append(orderBy);
        return query.toString();
    }

    @Override
    public String buildLimitOffsetCondition(Integer limit, Integer offset) {
        StringBuilder query = new StringBuilder();
        query.append(LIMIT).append("?").append(OFFSET)
                .append("?");
        parameters.add(limit);
        parameters.add(limit * offset);
        return query.toString();
    }

    @Override
    public List<Object> getParameters() {
        return parameters;
    }

    private void appendFromList(StringBuilder query, List<Integer> filterParam, String columnName) {
        Iterator<Integer> iterator = filterParam.iterator();
        while (iterator.hasNext()) {
            query.append(columnName).append("?");
            parameters.add(iterator.next());
            if (iterator.hasNext()) {
                query.append(OR);
            }
        }
    }
}
