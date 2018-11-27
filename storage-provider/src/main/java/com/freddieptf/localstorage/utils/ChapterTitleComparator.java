package com.freddieptf.localstorage.utils;

import com.freddieptf.malry.commons.AlphanumComparator;
import com.freddieptf.localstorage.data.models.Chapter;

import java.util.Comparator;

/**
 * Created by freddieptf on 9/10/18.
 */

public class ChapterTitleComparator implements Comparator<Chapter> {

    private AlphanumComparator alphanumComparator = new AlphanumComparator();

    @Override
    public int compare(Chapter chapter, Chapter t1) {
        return alphanumComparator.compare(chapter.getName(), t1.getName());
    }
}