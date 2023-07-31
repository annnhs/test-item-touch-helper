package com.uns.testitemtouchhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.uns.testitemtouchhelper.bottombar.BottomNavBarListAdapter
import com.uns.testitemtouchhelper.bottombar.BottomNavItem
import com.uns.testitemtouchhelper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  companion object {
    private const val TAG = "MainActivity"
  }

  private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

  private lateinit var adapter: BottomNavBarListAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)

    initRecyclerView()

    initButtonAction()

  }

  private fun initRecyclerView() {
    // 데이터 셋팅
    val list = ArrayList<String>()
    for(i in 1..30) {
      list.add("Item $i")
    }

    // 어댑터
    val adapter = RecyclerViewAdapter(list)
    binding.recyclerView.adapter = adapter

    // 리스너를 구현한 Adapter 클래스를 Callback 클래스의 생성자로 지정
    val itemTouchHelperCallback = ItemTouchHelperCallback(adapter)

    // ItemTouchHelper의 생성자로 ItemTouchHelper.Callback 객체 셋팅
    val helper = ItemTouchHelper(itemTouchHelperCallback)
    // RecyclerView에 ItemTouchHelper 연결
    helper.attachToRecyclerView(binding.recyclerView)

    initBottomNavSettingRecyclerView()
  }

  private fun initBottomNavSettingRecyclerView() {
    val setList = MyApp.prefs.getBottomNav("InPatientBottomNav")

    adapter = BottomNavBarListAdapter(setList)

    binding.bottomNavSettingRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    binding.bottomNavSettingRecyclerView.adapter = adapter

    val itemTouchHelperCallback = ItemTouchHelperCallback(adapter)
    val helper = ItemTouchHelper(itemTouchHelperCallback)
    helper.attachToRecyclerView(binding.bottomNavSettingRecyclerView)

  }

  private fun initButtonAction() = with(binding) {

    submitListBtn.setOnClickListener {
      // 변경된 리스트 저장

    }

  }

}