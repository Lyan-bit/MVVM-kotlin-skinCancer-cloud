package com.example.skincancer

import com.example.skincancer.model.SkinCancer
import java.util.ArrayList

class SkinCancerVO  {

    private var id: String = ""
    private var dates: String = ""
    private var images: String = ""
    private var outcome: String = ""

    constructor() {}

    constructor(idx: String, 
        datesx: String, 
        imagesx: String, 
        outcomex: String
        ) {
        this.id = idx
        this.dates = datesx
        this.images = imagesx
        this.outcome = outcomex
    }

    constructor (_x: SkinCancer) {
        id = _x.id
        dates = _x.dates
        images = _x.images
        outcome = _x.outcome
    }

    override fun toString(): String {
        return "id = $id,dates = $dates,images = $images,outcome = $outcome"
    }

    fun toStringList(list: List<SkinCancerVO>): List<String> {
        val res: MutableList<String> = ArrayList()
        for (i in list.indices) {
            res.add(list[i].toString())
        }
        return res
    }
    
    fun getId(): String {
        return id
    }
    
    fun getDates(): String {
        return dates
    }
    
    fun getImages(): String {
        return images
    }
    
    fun getOutcome(): String {
        return outcome
    }
    

    fun setId(_x: String) {
    	id = _x
    }
    
    fun setDates(_x: String) {
    	dates = _x
    }
    
    fun setImages(_x: String) {
    	images = _x
    }
    
    fun setOutcome(_x: String) {
    	outcome = _x
    }
    
}
