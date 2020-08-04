package com.acs.admin.utils;

import org.modelmapper.ModelMapper;

public class DataConverter {
    private static final ModelMapper mapper = new ModelMapper();

    public DataConverter() {
    }

    public static <D> D map(Object source, Class<D> destinationType) {
        return source == null ? null : mapper.map(source, destinationType);
    }
}
