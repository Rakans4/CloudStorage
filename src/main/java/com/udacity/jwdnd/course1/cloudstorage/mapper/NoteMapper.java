package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
    List<Note> getAllNotes(Integer userid);

    @Select("SELECT * FROM NOTES WHERE noteid = #{noteid}")
    Note getNote(Integer noteid);

    @Select("SELECT * FROM NOTES WHERE notetitle = #{title}")
    Note getOneNote(String title);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{notetitle}, #{notedescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int insert(Note note);

    @Update("UPDATE NOTES SET notetitle = #{notetitle}, notedescription = #{notedescription} WHERE noteid = #{noteid}")
    void update(Note note);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteid}")
    void delete(Integer noteid);
}
