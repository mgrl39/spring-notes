package net.xeill.elpuig.springbootrestfulconsumingapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*

categories	[]
created_at	"2020-01-05 13:42:25.099703"
icon_url	"https://api.chucknorris.io/img/avatar/chuck-norris.png"
id	"5uHPfGrnSiqjgYUwz-DAog"
updated_at	"2020-01-05 13:42:25.099703"
url	"https://api.chucknorris.io/jokes/5uHPfGrnSiqjgYUwz-DAog"
value	'People who enjoy being green are known as "tree huggers". People who enjoy living are known as Chuck Norris huggers.'
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {
    private String url;
    private String value;
    private String id;
    private String icon_url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "url='" + url + '\'' +
                ", value='" + value + '\'' +
                ", id='" + id + '\'' +
                ", icon_url='" + icon_url + '\'' +
                '}';
    }
}
