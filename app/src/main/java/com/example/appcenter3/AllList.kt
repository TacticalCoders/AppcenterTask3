package com.example.appcenter3

import androidx.lifecycle.ViewModel

class AllList : ViewModel() {

    var BeforeItems = mutableListOf<ItemData>(
        ItemData("살 빼기",0,false),
        ItemData("앱센터 과제하기",0,false)
 )

    var IngItems = mutableListOf<ItemData>(
        ItemData("앱센터 과제하기2",1,false),
        ItemData("앱센터 과제하기3",1,false),
        ItemData("앱센터 과제하기4",1,false),
        )

    var DoneItems = mutableListOf<ItemData>(
        ItemData("앱센터 과제하기5",2,false),
        ItemData("앱센터 과제하기6",2,false),
        ItemData("앱센터 과제하기7",2,false),
       )

    fun addToIng(item:ItemData){
        item.state = 1
        IngItems.add(item)
    }

    fun addToDone(item:ItemData){
        item.state = 2
        DoneItems.add(item)
    }

}