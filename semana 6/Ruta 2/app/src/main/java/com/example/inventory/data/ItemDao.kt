package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * from items WHERE id = :id")
    fun getItem(id: Int): Flow<Item?>

    @Query("SELECT * from items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>

    @Query(
        "SELECT * FROM items " +
                "WHERE name LIKE '%' || :query || '%' OR category LIKE '%' || :query || '%' " +
                "ORDER BY name ASC"
    )
    fun searchItems(query: String): Flow<List<Item>>

    @Query("SELECT SUM(price * quantity) FROM items")
    fun getTotalValue(): Flow<Double?>
}