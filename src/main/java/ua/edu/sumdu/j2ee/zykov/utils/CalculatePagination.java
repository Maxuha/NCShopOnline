package ua.edu.sumdu.j2ee.zykov.utils;

import ua.edu.sumdu.j2ee.zykov.model.EntityList;

import java.util.List;

public class CalculatePagination {
    public static List<Object> getEntityListFromRange(List<Object> objects, int page, int size) {
        int from = page * size;
        int to = from + size;
        to = Math.min(to, objects.size());
        return objects.subList(from, to);
    }

    public static int calculateTotalPagesForEntityList(EntityList entityList, int size) {
        return (int) Math.ceil(entityList.getTotalElements() / (double)size);
    }
}
