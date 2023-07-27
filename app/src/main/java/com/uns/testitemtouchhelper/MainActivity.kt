package com.uns.testitemtouchhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    val list = mutableListOf(
      BottomNavItem(id = 1, icon = R.drawable.ic_summary, text = R.string.center_pane_tab_text1),
      BottomNavItem(id = 2, icon = R.drawable.ic_summary, text = R.string.center_pane_tab_text2),
      BottomNavItem(id = 3, icon = R.drawable.ic_summary, text = R.string.center_pane_tab_text3),
      BottomNavItem(id = 4, icon = R.drawable.ic_summary, text = R.string.center_pane_tab_text4),
      BottomNavItem(id = 5, icon = R.drawable.ic_order_new, text = R.string.center_pane_tab_text5),
      BottomNavItem(id = 6, icon = R.drawable.ic_summary, text = R.string.center_pane_tab_text6),
      BottomNavItem(id = 7, icon = R.drawable.ic_summary, text = R.string.center_pane_tab_text7),
      BottomNavItem(id = 8, icon = R.drawable.ic_summary, text = R.string.center_pane_tab_text8),
    )

    val adapter = BottomNavBarListAdapter(list)

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