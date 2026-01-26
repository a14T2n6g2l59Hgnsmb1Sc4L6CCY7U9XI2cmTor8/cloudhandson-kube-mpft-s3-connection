package dev.cloudhandson.mpft.s3.connection.common.util;

import org.modelmapper.ModelMapper;

import java.util.List;

public class MapperUtil {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static <S, T> T map(S source, Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }

    public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
            .stream()
            .map(element -> modelMapper.map(element, targetClass))
            .toList();
    }
}

