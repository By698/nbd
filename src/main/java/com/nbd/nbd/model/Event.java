package com.nbd.nbd.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Table
public class Event {
    @PrimaryKey
    private @NonNull String eventid;
    private @NonNull int iyear;
    private @NonNull int imonth;
    private @NonNull int iday;
    private @NonNull String country_txt;
    private @NonNull String region_txt;
    private String provstate;
    private String city;
    private String summary;
    private @NonNull boolean crit1;
    private @NonNull boolean crit2;
    private @NonNull boolean crit3;
    private @NonNull boolean success;
    private @NonNull boolean suicide;
    private @NonNull String attacktype1_txt;
    private @NonNull String targtype1_txt;
    private String targsubtype1_txt;
    private String corp1;
    private String target1;
    private String natlty1_txt;
    private @NonNull String gname;
    private @NonNull String weaptype1_txt;
    private int nkill;
    private int nwound;
}