package springboot.ebay.exam.converter;

import springboot.ebay.exam.model.SchemaEntity;
import springboot.ebay.exam.model.UrlDto;

public class UrlConverter{

    public static SchemaEntity dtoToEntity(UrlDto urlDto){
        SchemaEntity entity = new SchemaEntity();
        entity.setId(urlDto.getid());
        entity.setLongUrl(urlDto.getUrl());
        return entity;
    }

    public static UrlDto entityToDto(SchemaEntity entity){
        UrlDto dto = new UrlDto(entity.getId(), null);
        return dto;
    }
}