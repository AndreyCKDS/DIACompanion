package com.almazov.diacompanion.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ketone_table")
data class KetoneEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    val ketone: Int?,
    )