package com.edgencio.android.mjournal.model.DAO.util;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by edgencio on 6/30/18.
 */

public class DateTypeConverter {
    @TypeConverter
    public long convertDateToLong(Date date) {
        return date.getTime();
    }

    @TypeConverter
    public Date convertLongToDate(long time) {
        return new Date(time);
    }
}