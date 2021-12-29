package com.example.appcenter3.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcenter3.*
import com.google.android.material.tabs.TabLayout


class BeforeFragment : Fragment() {

    private val sharedViewModel:AllList by activityViewModels()

    lateinit var beforeadapter:BeforeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("BeforeFragment","객체 생성됨")

        val rootView = inflater.inflate(R.layout.fragment_before, container, false)
        val beforeList:RecyclerView = rootView.findViewById(R.id.beforeList)
        beforeList.layoutManager = LinearLayoutManager(rootView.context)
        sharedViewModel.BeforeItems.add(ItemData("추가 버튼",0,true))
        beforeadapter = BeforeAdapter(sharedViewModel.BeforeItems)
        beforeList.adapter = beforeadapter


        //스크롤 내릴 때 탭 레이아웃 그림자 주기.
        val viewTab : TabLayout = (activity as MainActivity).findViewById(R.id.viewTab)
        beforeList.setOnScrollListener(ScrollListener(viewTab))

        return rootView
    }


    inner class BeforeAdapter(val list: MutableList<ItemData>) : RecyclerView.Adapter<MyViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.list_item,parent,false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val Item = list[position]
            holder.bind(Item)
            holder.itemView.setOnClickListener {
                if(Item.isLastItme){ //사용자로부터 텍스트를 입력받아 새로운 할 일 추가.
                    var newText:String=""
                    val newItemDialog = NewItemDialog(R.layout.dialog,list,Item,beforeadapter)
                    fragmentManager?.let { newItemDialog.show(it, "CustomDialog") }
                    //val newItemData = ItemData(newText,0,false)
                    //list.add(list.indexOf(Item),newItemData)
                }
                else{
                    list.removeAt(position)
                    sharedViewModel.addToIng(Item)
                    Log.d("시작 전 ","아이템 클릭")
                    Log.d("allList.IngItems에","state1로 변경된 아이템 추가됨")
                }
                notifyDataSetChanged()
            }
        }
        override fun getItemCount(): Int = list.size
    }





}