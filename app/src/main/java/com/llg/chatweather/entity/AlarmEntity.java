package com.llg.chatweather.entity;


import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class AlarmEntity {

    @Id
    public long id;

    /**
     * alarm_type :
     * alarm_content :
     * alarm_level :
     */
    public String alarm_type;
    public String alarm_content;
    public String alarm_level;

}