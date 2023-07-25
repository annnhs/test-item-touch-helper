package com.uns.testitemtouchhelper

// RecyclerView의 Adapter와 ItemTouchHelper.Callback을 연결시켜 주는 리스너
interface ItemTouchHelperListener {
  fun onItemMove(fromPosition: Int, toPosition: Int): Boolean
  fun onItemSwipe(position: Int)
}