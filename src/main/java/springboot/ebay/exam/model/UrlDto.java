package springboot.ebay.exam.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UrlDto {
    
    String url;
    String id;


    @JsonCreator
    public UrlDto(@JsonProperty("url") String url,@JsonProperty("id") String id){
        this.url = url;
        this.id = id;
    }

    public UrlDto(){

    }


    public String getUrl(){
        return this.url;
    }

    public String getid(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setUrl(String url){
        this.url = url;
    }
}