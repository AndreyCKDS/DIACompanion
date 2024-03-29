package com.almazov.diacompanion.meal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.almazov.diacompanion.R
import com.almazov.diacompanion.data.FoodEntity
import kotlinx.android.synthetic.main.food_row.view.*
import kotlinx.coroutines.DelicateCoroutinesApi


open class FoodListAdapter() : PagingDataAdapter<FoodEntity, FoodListAdapter.FoodViewHolder>(DIFF_CALLBACK) {
    var context: Context? = null
    val favouriteChanges = mutableListOf<Pair<Int?, Boolean>>()
    private val favouriteChangesIdFood = mutableListOf<Int?>()


    companion object {
        private val DIFF_CALLBACK = object :
        DiffUtil.ItemCallback<FoodEntity>() {

            override fun areItemsTheSame(oldItem: FoodEntity, newItem: FoodEntity): Boolean {
                return oldItem.idFood == newItem.idFood
            }

            override fun areContentsTheSame(oldItem: FoodEntity, newItem: FoodEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    class FoodViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        context=parent.context

        return FoodViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.food_row,
                parent, false
            )
        )
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food: FoodEntity? = getItem(position)
        if ((food?.recipe != null) and (food?.recipe == true) ) holder.itemView.image_recipe.visibility = View.VISIBLE
        else holder.itemView.image_recipe.visibility = View.GONE
        holder.itemView.tv_food_name.text = food?.name
        holder.itemView.tv_carbs.text = food?.carbo.toString()
        holder.itemView.tv_protein.text = food?.prot.toString()
        holder.itemView.tv_fats.text = food?.fat.toString()
        holder.itemView.tv_kkal.text = food?.ec.toString()
        val giString = if (food?.gi == null) "0" else food.gi.toString()
        holder.itemView.tv_gi.text = giString

        setItemViewClickListener(holder, food)

    }

    open fun setItemViewClickListener(holder: FoodViewHolder, food: FoodEntity?) {
        holder.itemView.setOnClickListener{ view ->
            view.findNavController().previousBackStackEntry?.savedStateHandle?.set("foodKey", food)
            view.findNavController().popBackStack()
        }

        var second: Boolean? = false
        if (food?.favourite != null) {
            if (favouriteChangesIdFood.contains(food.idFood))
            {
                var i = favouriteChanges.count()
                while (i>0){
                    if (favouriteChanges[i-1].first == food.idFood) {
                        second = favouriteChanges[i-1].second
                        break
                    }
                    i -= 1
                }
            } else second = food.favourite
        }
        holder.itemView.checkbox_favourite.isChecked = second!!

        holder.itemView.checkbox_favourite.setOnClickListener {
            favouriteChanges.add(Pair(food?.idFood,
                holder.itemView.checkbox_favourite.isChecked))
            favouriteChangesIdFood.add(food?.idFood)
        }
    }

}

