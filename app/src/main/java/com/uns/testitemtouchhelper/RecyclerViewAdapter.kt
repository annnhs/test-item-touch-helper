package com.uns.testitemtouchhelper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Listener 인터페이스를 구현
class RecyclerViewAdapter(val nameList: MutableList<String>) : RecyclerView.Adapter<ViewHolder>(), ItemTouchHelperListener {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.sample_item, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.onBind(nameList[position])
  }

  override fun getItemCount(): Int = nameList.size

  // 아이템 드래그되면 호출되는 메소드
  override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {


    val name = nameList[fromPosition]
    //리스트 갱신
    nameList.removeAt(fromPosition)
    nameList.add(toPosition, name)

    // fromPosition에서 toPosition으로 아이템 이동 공지
    notifyItemMoved(fromPosition, toPosition)
    return true
  }

  // 아이템 스와이프되면 호출되는 메소드
  override fun onItemSwipe(position: Int) {
    // 리스트 아이템 삭제
    nameList.removeAt(position)
    // 아이템 삭제되었다고 공지
    notifyItemRemoved(position)
  }

}

class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
  val nameView = view.findViewById<TextView>(R.id.name_view)

  // 뷰에 값 세팅
  fun onBind(name: String) {
    nameView.text = name
  }
}