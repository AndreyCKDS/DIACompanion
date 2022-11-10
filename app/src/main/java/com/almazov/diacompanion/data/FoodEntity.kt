package com.almazov.diacompanion.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "food_table")
data class FoodEntity (
    @PrimaryKey(autoGenerate = true)
    val idFood: Int?,
    val name: String?,
    val category: String?,
    val carbo: Double?,
    val prot: Double?,
    val fat: Double?,
    val ec: Double?,
    val gi: Double?,
    val water: Double?,
    val nzhk: Double?,
    val hol: Double?,
    val pv: Double?,
    val zola: Double?,
    val na: Double?,
    val k: Double?,
    val ca: Double?,
    val mg: Double?,
    val p: Double?,
    val fe: Double?,
    val a: Double?,
    val b1: Double?,
    val b2: Double?,
    val rr: Double?,
    val c: Double?,
    val re: Double?,
    val kar: Double?,
    val mds: Double?,
    val kr: Double?,
    val te: Double?,
    val ok: Double?,
    val ne: Double?,
    val zn: Double?,
    val cu: Double?,
    val mn: Double?,
    val se: Double?,
    val b5: Double?,
    val b6: Double?,
    val fol: Double?,
    val b9: Double?,
    val dfe: Double?,
    val holin: Double?,
    val b12: Double?,
    val ear: Double?,
    val a_kar: Double?,
    val b_kript: Double?,
    val likopin: Double?,
    val lut_z: Double?,
    val vit_e: Double?,
    val vit_d: Double?,
    val d_mezd: Double?,
    val vit_k: Double?,
    val mzhk: Double?,
    val pzhk: Double?,
    val w_1ed: Double?,
    val op_1ed: Double?,
    val w_2ed: Double?,
    val op_2ed: Double?,
    val proc_pot: Int?,
    val additional: Int?,
    val favourite: Int?
): Parcelable