package com.lamadridblandongoyes.pronotes.notes

interface ItemTapListener {
    fun onItemTapped(index: Int)
    fun onItemLongTapped(index: Int): Boolean
}