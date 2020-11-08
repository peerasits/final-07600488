package th.ac.su.speedrecords.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import th.ac.su.speedrecords.model.User;


@Dao
public interface UserDao {

    @Query("SELECT * FROM users")
    User[] getAllUsers();

    @Query("SELECT * FROM users WHERE id = :id")
    User getUserById(int id);

    @Query("SELECT COUNT(*) FROM users")
    int count();

    @Query("SELECT COUNT(*) FROM users WHERE over = :over")
    int countOver(boolean over);

    @Insert
    void addUser(User... users);

    @Delete
    void deleteUser(User user);
}