package com.almazov.diacompanion.settings

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.almazov.diacompanion.R
import com.almazov.diacompanion.meal.AppTypeAdapter
import com.almazov.diacompanion.meal.FoodInMealListAdapter
import kotlinx.android.synthetic.main.fragment_settings_account.view.*
import kotlinx.android.synthetic.main.fragment_settings_app_type.*
import kotlinx.android.synthetic.main.fragment_settings_app_type.view.*
import kotlinx.android.synthetic.main.fragment_settings_app_type.view.btn_save

class SettingsAppType : Fragment(), AppTypeAdapter.InterfaceAppType {

    private lateinit var sharedPreferences: SharedPreferences
    private var appType: String? = "PCOS"
    lateinit var adapter: AppTypeAdapter

    private val appTypes = listOf(
        AppType(R.string.GDMRCT,"GDMRCT", R.string.GDMRCTDescription),
        AppType(R.string.GDM,"GDM", R.string.GDMDescription),
        AppType(R.string.MS,"MS", R.string.MSDescription),
        AppType(R.string.PCOS,"PCOS", R.string.PCOSDescription))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val view = inflater.inflate(R.layout.fragment_settings_app_type, container, false)
        appType = sharedPreferences.getString("APP_TYPE","PCOS")

        val finished: Boolean = sharedPreferences.getBoolean("ON_BOARDING_FINISHED", false)
        if (finished) {
            view.btn_save.setOnClickListener {
                saveChanges()
                findNavController().popBackStack()
            }
        } else {
            view.btn_save.setOnClickListener {
                saveChanges()
                findNavController().navigate(R.id.action_settingsAppType_to_settingsAccount)
            }
        }

        adapter = AppTypeAdapter(this, appTypes)
        view.recycler_view_app_type.adapter = adapter
        view.recycler_view_app_type.layoutManager = LinearLayoutManager(requireContext())
        adapter.updateItems(appType!!)

        return view
    }

    private fun saveChanges() {
        sharedPreferences.edit().apply{
            putString("APP_TYPE",appType)
        }?.apply()
    }

    override fun selectAppType(position: Int) {
        appType = appTypes[position].name
        adapter.updateItems(appType!!)
    }

    override fun getCurrentAppTypePosition(position: Int) {
        tv_description.setText(appTypes[position].description)
    }

}