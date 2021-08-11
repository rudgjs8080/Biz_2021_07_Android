package com.rudgjs8080.topnews.model;

import java.util.List;

import lombok.ToString;

@ToString
public class NaverParent {

    public String rss;
    public String channel;
    public String lastBuildDate;
    public String total;
    public String start;
    public String display;
    public List<NaverNewsDTO> items;


}
