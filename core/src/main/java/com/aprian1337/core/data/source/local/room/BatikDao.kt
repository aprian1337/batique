package com.aprian1337.core.data.source.local.room

import androidx.room.*
import com.aprian1337.core.data.source.local.entity.BatikEntity
import com.aprian1337.core.utils.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface BatikDao {
//    QUERY FOR LIST
    @Query("SELECT * FROM ${Constants.TABLE_NAME_BATIK} ORDER BY namaBatik ASC")
    fun getAllBatik(): Flow<List<BatikEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBatik(batik: List<BatikEntity>)

//    QUERY FOR DETAIL

    @Query("SELECT * FROM ${Constants.TABLE_NAME_BATIK} WHERE namaBatik = :namaBatik")
    fun getDetailBatik(namaBatik: String): Flow<BatikEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailBatik(batik: BatikEntity)


//    QUERY FOR FAVORITE
    @Query("SELECT* FROM ${Constants.TABLE_NAME_BATIK} WHERE isFavorite = 1")
    fun getFavBatik(): Flow<List<BatikEntity>>

    @Update
    fun updateFavBatik(batik: BatikEntity)
}