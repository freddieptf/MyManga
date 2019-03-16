package com.freddieptf.malry.data.dao

import android.net.Uri
import androidx.room.*
import com.freddieptf.malry.data.models.Chapter
import com.freddieptf.malry.data.utils.DBTypeConverters

/**
 * Created by freddieptf on 11/13/18.
 */
@Dao
@TypeConverters(DBTypeConverters::class)
internal interface ChapterDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveChapters(data: List<Chapter>)

    @Query("SELECT * FROM chapters WHERE parentUri=:uri")
    fun getChaptersLiveData(uri: Uri): List<Chapter>

    @Query("SELECT * FROM chapters WHERE parentUri=:uri")
    fun getChapters(uri: Uri): List<Chapter>

    @Query("SELECT * FROM chapters WHERE docID=:chapterID")
    fun getChapter(chapterID: String): Chapter

    @Query("SELECT * FROM chapters WHERE parentURI=:libraryItemID ORDER BY lastRead DESC LIMIT 1")
    fun getLastReadChapter(libraryItemID: String): Chapter?

    @Query("UPDATE chapters SET lastReadPage=:page, " +
            "totalPages=:totalPages, " +
            "lastRead=:lastRead " +
            "WHERE docID=:chapterID")
    fun setLastReadPage(chapterID: String, page: Int, totalPages: Int, lastRead: Long)

}