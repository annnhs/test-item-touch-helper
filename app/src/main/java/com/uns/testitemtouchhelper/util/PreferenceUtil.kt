package com.uns.testitemtouchhelper.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.GsonBuilder
import com.uns.testitemtouchhelper.R
import com.uns.testitemtouchhelper.bottombar.BottomNavItem
import org.json.JSONArray
import org.json.JSONException

/* SharedPreferences 싱글톤 설정 */
class PreferenceUtil(context: Context) {

  companion object {
    private const val TAG = "PreferenceUtil"
  }

  val prefs: SharedPreferences =
    context.getSharedPreferences("prefs_name", Context.MODE_PRIVATE)
  private val editor by lazy { prefs.edit() }

  /* DEFAULT SAVE LOAD FUNCTIONS START */
  fun save(key: String, value: String)
  = editor.putString(key, value).apply()

  fun save(key: String, value: Boolean)
  = editor.putBoolean(key, value).apply()

  fun save(key: String, value: Float)
  = editor.putFloat(key, value).apply()

  fun save(key: String, value: Int)
  = editor.putInt(key, value).apply()

  fun save(key: String, value: Long)
  = editor.putLong(key, value).apply()

  fun save(key: String, value: Set<String>)
  = editor.putStringSet(key, value).apply()

  fun load(key: String, defValue: String)
  = prefs.getString(key, defValue).toString()

  fun load(key: String, defValue: Boolean)
  = prefs.getBoolean(key, defValue)

  fun load(key: String, defValue: Float)
  = try { prefs.getFloat(key, defValue) }
  catch (ex: ClassCastException) { prefs.getString(key, defValue.toString())!!.toFloat() }

  fun load(key: String, defValue: Int)
  = try { prefs.getInt(key, defValue) }
  catch (ex: ClassCastException) { prefs.getString(key, defValue.toString())!!.toInt() }

  fun load(key: String, defValue: Long)
  = try { prefs.getLong(key, defValue) }
  catch (ex: ClassCastException) { prefs.getString(key, defValue.toString())!!.toLong() }

  fun load(key: String, defValue: Set<String>) : Set<String>? = prefs.getStringSet(key, defValue)

  fun remove(key: String) = run { editor.remove(key).commit() }


  fun getAll(): MutableMap<String, *>? = prefs.all

  fun clear() = editor.clear().apply()
  /* DEFAULT SAVE LOAD FUNCTIONS END */

  /* CUSTOM OBJECT ARRAYLIST START */

  // BottomNav
  fun setBottomNav(key: String, values: ArrayList<BottomNavItem>) {
    val a = JSONArray()
    val gson = GsonBuilder().create()
    for (i in 0 until values.size) a.put(gson.toJson(values[i], BottomNavItem::class.java))
    val value = if (values.isEmpty()) null else a.toString()
    editor.putString(key, value).apply()
  }

  fun getBottomNav(key: String): ArrayList<BottomNavItem> {
    val json = prefs.getString(key, null)
    val data = ArrayList<BottomNavItem>()
    val initlist = mutableListOf(
      BottomNavItem(id = 1, icon = R.drawable.ic_summary, text = R.string.center_pane_tab_text1),
      BottomNavItem(id = 2, icon = R.drawable.ic_summary, text = R.string.center_pane_tab_text2),
      BottomNavItem(id = 3, icon = R.drawable.ic_summary, text = R.string.center_pane_tab_text3),
      BottomNavItem(id = 4, icon = R.drawable.ic_summary, text = R.string.center_pane_tab_text4),
      BottomNavItem(id = 5, icon = R.drawable.ic_order_new, text = R.string.center_pane_tab_text5),
      BottomNavItem(id = 6, icon = R.drawable.ic_summary, text = R.string.center_pane_tab_text6),
      BottomNavItem(id = 7, icon = R.drawable.ic_summary, text = R.string.center_pane_tab_text7),
      BottomNavItem(id = 8, icon = R.drawable.ic_summary, text = R.string.center_pane_tab_text8),
      BottomNavItem(id = 9, icon = R.drawable.ic_summary, text = R.string.center_pane_tab_text9),
    )
    val gson = GsonBuilder().create()
    if (!json.isNullOrEmpty()) try {
      val a = JSONArray(json)
      for (i in 0 until a.length()) data.add(gson.fromJson(a[i].toString(), BottomNavItem::class.java))
    } catch (e: JSONException) { Log.e(TAG, e.message ?: "") }
    if (data.isNullOrEmpty()) data.addAll(initlist)
    return data
  }
  /* CUSTOM OBJECT ARRAYLIST END */

}