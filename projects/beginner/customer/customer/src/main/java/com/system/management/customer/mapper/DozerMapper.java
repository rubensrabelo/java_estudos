package com.system.management.customer.mapper;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerMapper {
	
	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	
	public static <O, D> D parseObject(O origin, Class<D> destin) {
		return mapper.map(origin, destin);
	}
	
	public static <O, D> List<D> parseListObjects(List<O> originList, Class<D> destin) {
		List<D> destinList = new ArrayList<D>();
		
		for (O origin : originList) {
			destinList.add(mapper.map(origin, destin));
		}
		
		return destinList;
	}
}
